package com.example.DrSmart;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class MeasureScreen extends AppCompatActivity {
    Button btnNxt, btnResp, btnHeart;
    String user_name ="";
    int threshold = 12;
    int frameIndex = 0;
    int totalframes = 0;
    int heartRateCount =0;
    VideoView videoView;
    TextView viewResp;
    ArrayList<Float> colourRed = new ArrayList<Float>();
    private static int VIDEO_REQUEST =101;
    private static int REQUEST_PERMISSION =1;
    private static int READ_PERMISSION = 102;
    private static int WRITE_PERMISSION = 103;
    private Uri videoUri;
    Intent videoIntent;
    DBModel myDB = new DBModel();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == VIDEO_REQUEST && resultCode == RESULT_OK){
            Uri VideoUri = data.getData();
            String sampleUri = VideoUri.toString();
            videoUri = Uri.parse(sampleUri);
            videoPlayback();
            HRCalc myHRCalc = new HRCalc();
            myHRCalc.execute(videoUri);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_measure_screen);
        super.onCreate(savedInstanceState);

        viewResp = (TextView) findViewById(R.id.viewResp);
        videoView = findViewById(R.id.videoView);

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            user_name ="";
        }else{
            user_name = extras.getString("name");
        }
        btnNxt = findViewById(R.id.btnNxt);
        btnResp = findViewById(R.id.BtnResp);
        btnHeart = findViewById(R.id.btnHeart);
        buttonClick();
        measureResp();
        measureHR();
    }
    private BroadcastReceiver bReceiver = new BroadcastReceiver(){

        @Override
        public void onReceive(Context context, Intent intent) {
            String str = (String) intent.getExtras().get("success").toString();
            myDB.setRESPIRATORY_RATE(Integer.parseInt(str));
            viewResp.setText("Measured Respiratory Rate: " +str);
        }
    };

    protected void onResume(){
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(bReceiver, new IntentFilter("message"));
    }

    protected void onPause (){
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bReceiver);
    }
    public void buttonClick(){
        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(myDB);
                Intent symptomsIntent = new Intent(getApplicationContext(), SymptomsScreen.class);
                symptomsIntent.putExtra("myDBObject",myDB);
                System.out.println(myDB + "sent");
                startActivity(symptomsIntent);
            }
        });
    }
    public void measureResp(){
        btnResp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent respiratoryIntentService = new Intent(getApplicationContext(),RespiratoryService.class);
                startService(respiratoryIntentService);
                viewResp.setText("Measured Respiratory Rate: Calculating....");
            }
        });
    }
    public void measureHR(){
        btnHeart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 45);
                videoIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                videoIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                            if (videoIntent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(videoIntent, VIDEO_REQUEST);
                            }
                        } else {
                            requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION);
                            if (videoIntent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(videoIntent, VIDEO_REQUEST);
                            }
                        }
                    }else{
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PERMISSION);
                    }
                }else{
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION);
                }

            }
        });
    }
    public void videoPlayback(){
        videoView.setVideoURI(videoUri);
        videoView.setMediaController(new MediaController(this));
        videoView.requestFocus();
        videoView.start();
        Toast.makeText(getApplicationContext(), "Calculating Heart Rate...", Toast.LENGTH_LONG).show();
        TextView viewHeart = findViewById(R.id.viewHeart);
        viewHeart.setText("Measured Heart Rate: Calculating....");
    }
    class HRCalc extends AsyncTask<Uri, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @RequiresApi(api = Build.VERSION_CODES.R)
        @Override
        protected Void doInBackground(Uri... uris) {
            MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
            try {
                metaRetriever.setDataSource(getApplicationContext(),videoUri);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            MediaPlayer forTime = MediaPlayer.create(getBaseContext(),videoUri);
            int videoDuration = forTime.getDuration();
            int processFramesPerSec = 12;
            int processtime = 100000;
            totalframes = (int) Math.floor(videoDuration/1000) * processFramesPerSec;
            heartRateCount = 1;
            frameIndex = 1;
            while(frameIndex < totalframes){
                float currentColor = 0f;
                Bitmap currentFrameBitmap = metaRetriever.getFrameAtTime(processtime,MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                processtime = processtime + 100000;
                Log.d("HeartRateCalculatorTask", "Processing frame number"+processtime);

                int i = 450;
                while(i <= 550){
                    int j = 900;
                    while(j < 1200){
                        currentColor = currentColor + Color.red(currentFrameBitmap.getPixel(i,j));
                        j++;
                    }
                    i++;
                }

                float previousColor = 1f;
                boolean isArrayListEmpty = (colourRed.size()!=0);
                if(isArrayListEmpty!=false){
                    int currentSize = colourRed.size();
                    previousColor = colourRed.get(currentSize - 1);
                }

                boolean isCountable = Math.abs(previousColor - currentColor) > threshold;
                if(isCountable == true){
                    heartRateCount++;
                }

                colourRed.add(currentColor);

                Log.d("HeartRateCalculatorTask", "Processing frame number"+processtime);
                frameIndex++;

            }

            metaRetriever.release();
            Log.d("HeartRateCalculatorTask", "Sending heart rate"+ heartRateCount);

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView heartRateValue = findViewById(R.id.viewHeart);
            heartRateValue.setText("Measured Heart Rate: " + String.valueOf(heartRateCount));
            myDB.setHEART_RATE(heartRateCount);
            //uploadData(outputHeartRate);
        }
    }

}