package com.example.jake.shades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ListFragment.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onShadeItemSelected(String link){
        Log.d("link","----------------------------------------------------------------- "+link+" ------------------------------------------------------------------");
        InformationFragment informationFragment = (InformationFragment) getFragmentManager().findFragmentById(R.id.fragment2);
        if(informationFragment != null && informationFragment.isInLayout()){ //two pane being displayed?
            informationFragment.setText(link);
        } else{ //single pane config exists
            Intent intent = new Intent(this, InformationActivity.class);
            intent.putExtra("Information",link);
            startActivity(intent);
        }
    }
}
