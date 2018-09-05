package com.example.jake.renaissancepaintings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linearLayout);
        fillPaintingGallery();
    }

    private void fillPaintingGallery(){
        ImageButton buttonItem;
        for(int i = 0; i < RenaissanceDatabase.description.length; i++){
            buttonItem = new ImageButton(this);
            //create painting object
            Painting painting = new Painting(RenaissanceDatabase.description[i],RenaissanceDatabase.id[i]);
            //set description of painting
            buttonItem.setContentDescription(painting.getmDescription());
            //load painting
            buttonItem.setImageDrawable(getResources().getDrawable(painting.getmId()));

            //set click listener for image button
            buttonItem.setOnClickListener(displayPaintingInformation);
            //add image button to scrollable linear list
            linearLayout.addView(buttonItem);
        }
    }

    private View.OnClickListener displayPaintingInformation = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            displayToast((String) v.getContentDescription());
        }
    };

    private void displayToast(String desc){
        Toast.makeText(this,desc, Toast.LENGTH_SHORT).show();
    }
}
