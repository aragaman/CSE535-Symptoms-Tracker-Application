package com.example.DrSmart;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redirectToScreen();
    }

    public void redirectToScreen(){
        Intent measureIntent = new Intent(getApplicationContext(), MeasureScreen.class);
        startActivity(measureIntent);
    }
}