package com.example.jake.shades;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //verify config was switched to landscape mode
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }
        setContentView(R.layout.fragment_information); //set layout
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //get intent that started the activity
        Intent intent = getIntent();
        String shadeInformation = intent.getStringExtra("Information");

        //set text view to the info passed from the intent
        TextView information = findViewById(R.id.textView1);
        information.setText(shadeInformation);

    }
}
