package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements

        List<String> categories = new ArrayList<String>();
        categories.add("Category (required)");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinnerbasic.xml
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinnerbasic);

        // attaching data adapter to spinnerbasic.xml
        spinner.setAdapter(dataAdapter);

//Spinner
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);

        List<String> categories2 = new ArrayList<String>();
        categories2.add("Size (required)");
        categories2.add("Business Services");
        categories2.add("Computers");
        categories2.add("Education");
        categories2.add("Personal");
        categories2.add("Travel");

        // Creating adapter for spinnerbasic.xml
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(R.layout.spinnerbasic);

        // attaching data adapter to spinnerbasic.xml
        spinner2.setAdapter(dataAdapter2);



 //Third Spinner
        Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);

        List<String> categories3 = new ArrayList<String>();
        categories3.add("Brand  (optional)");
        categories3.add("Business Services");
        categories3.add("Computers");
        categories3.add("Education");
        categories3.add("Personal");
        categories3.add("Travel");

        // Creating adapter for spinnerbasic.xml
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories3);

        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(R.layout.spinnerbasic);

        // attaching data adapter to spinnerbasic.xml
        spinner3.setAdapter(dataAdapter3);


        //Forth Spinner
        Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner4.setOnItemSelectedListener(this);
        List<String> categories4 = new ArrayList<String>();
        categories4.add("Condition (required");
        categories4.add("Business Services");
        categories4.add("Computers");
        categories4.add("Education");
        categories4.add("Personal");
        categories4.add("Travel");

        // Creating adapter for spinnerbasic.xml
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories4);

        // Drop down layout style - list view with radio button
        dataAdapter4.setDropDownViewResource(R.layout.spinnerbasic);

        // attaching data adapter to spinnerbasic.xml
        spinner4.setAdapter(dataAdapter4);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinnerbasic.xml item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinnerbasic.xml item

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}

