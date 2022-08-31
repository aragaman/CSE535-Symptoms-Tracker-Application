package com.example.DrSmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class SymptomsScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinnerSymptom;
    RatingBar ratingBar;
    Button btnUpload, btnSubmit;
    String spinnerSelection = "";
    float selectedRating = 0;
    String user_name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBModel DBModelGlobal = (DBModel) getIntent().getParcelableExtra("myDBObject");
        System.out.println(DBModelGlobal + "received");
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            user_name = "";
        } else {
            user_name = extras.getString("name");
        }
        setContentView(R.layout.activity_symptoms_screen);
        spinnerSymptom = findViewById(R.id.spinnerSymptom);
        ratingBar = findViewById(R.id.ratingSymptom);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.symptoms_list));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerSymptom.setAdapter(spinnerAdapter);
        spinnerSymptom.setOnItemSelectedListener(this);
        submitSymptoms(DBModelGlobal);
        uploadToDB(DBModelGlobal);
//        onChangeSymptom();
    }

    public void submitSymptoms(DBModel DBModelGlobal) {

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRating = ratingBar.getRating();

                if (spinnerSelection.equals("Nausea")) {
                    DBModelGlobal.setRATE_HEADACHE((int) selectedRating);
                    System.out.println(DBModelGlobal.getRATE_NAUSEA());
                }

                if (spinnerSelection.equals("Headache")) {
                    DBModelGlobal.setRATE_HEADACHE((int) selectedRating);
                    System.out.println(DBModelGlobal.getRATE_HEADACHE());
                }

                if (spinnerSelection.equals("Diarrhea")) {
                    DBModelGlobal.setRATE_DIARRHEA((int) selectedRating);
                }

                if (spinnerSelection.equals("Sore Throat")) {
                    DBModelGlobal.setRATE_SORE_THROAT((int) selectedRating);
                }

                if (spinnerSelection.equals("Fever")) {
                    DBModelGlobal.setRATE_FEVER((int) selectedRating);
                }

                if (spinnerSelection.equals("Muscle Pain")) {
                    DBModelGlobal.setRATE_MUSCLE_PAIN((int) selectedRating);
                }

                if (spinnerSelection.equals("Loss of Smell or Taste")) {
                    DBModelGlobal.setRATE_SMELL_TASTE((int) selectedRating);
                }

                if (spinnerSelection.equals("Cough")) {
                    DBModelGlobal.setRATE_COUGH((int) selectedRating);
                }

                if (spinnerSelection.equals("Shortness of Breath")) {
                    DBModelGlobal.setRATE_SHORTNESS_BREATH((int) selectedRating);
                }

                if (spinnerSelection.equals("Tiredness")) {
                    DBModelGlobal.setRATE_TIRED((int) selectedRating);
                }
                Toast.makeText(getApplicationContext(), spinnerSelection +": "+ selectedRating, Toast.LENGTH_SHORT).show();

//                spinnerSymptom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        ratingBar.setRating(0);
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> parent) {
//
//                    }
//                });
            }
        });
    }

    public void uploadToDB(DBModel DBModelGlobal) {
        btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Uploading to DB...", Toast.LENGTH_LONG).show();
                DBHelper databaseAction = new DBHelper(getApplicationContext());
                DBModel reading = null;
                reading = new DBModel(DBModelGlobal.getHEART_RATE(), DBModelGlobal.getRESPIRATORY_RATE(), DBModelGlobal.getRATE_NAUSEA(), DBModelGlobal.getRATE_HEADACHE(), DBModelGlobal.getRATE_DIARRHEA(), DBModelGlobal.getRATE_SORE_THROAT(), DBModelGlobal.getRATE_FEVER(), DBModelGlobal.getRATE_MUSCLE_PAIN(), DBModelGlobal.getRATE_SMELL_TASTE(), DBModelGlobal.getRATE_COUGH(), DBModelGlobal.getRATE_SHORTNESS_BREATH(), DBModelGlobal.getRATE_TIRED(), user_name);

                if (databaseAction.onInsert(reading) == true) {
                    Toast.makeText(getApplicationContext(), "Upload to DB Successful!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void onChangeSymptom() {
        spinnerSymptom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ratingBar.setRating(0);
                spinnerSelection = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String symptom = parent.getItemAtPosition(position).toString();
        spinnerSelection = symptom;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}