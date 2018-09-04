package com.example.jake.burgercaloriecounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private RadioGroup pattyRG;
    private CheckBox prosciuttoCBX;
    private RadioGroup cheeseRG;
    private SeekBar sauceSBR;
    private TextView calorieTV;
    private Burger burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize(){
        burger = new Burger();
        pattyRG = findViewById(R.id.meatRadio);
        prosciuttoCBX = findViewById(R.id.prosciuttoCheck);
        cheeseRG = findViewById(R.id.cheeseRadio);
        sauceSBR = findViewById(R.id.seekBar);
        calorieTV = findViewById(R.id.outputText);
        registerChangeListener();
        displayCalories();
    }

    private void registerChangeListener(){
        pattyRG.setOnCheckedChangeListener(foodListener);
        prosciuttoCBX.setOnClickListener(baconListener);
        cheeseRG.setOnCheckedChangeListener(foodListener);
        sauceSBR.setOnSeekBarChangeListener(sauceListener);
    }

    private OnCheckedChangeListener foodListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.beefBtn:
                    burger.setmPattyCal(Burger.BEEF);
                    break;
                case R.id.lambBtn:
                    burger.setmPattyCal(Burger.LAMB);
                    break;
                case R.id.ostrichBtn:
                    burger.setmPattyCal(Burger.OSTRICH);
                    break;
                case R.id.asiagoBtn:
                    burger.setmCheeseCal(Burger.ASIAGO);
                    break;
                case R.id.cremeFraicheBtn:
                    burger.setmCheeseCal(Burger.CREME_FRAICHE);
                    break;
            }
            displayCalories();
        }
    };

    private OnSeekBarChangeListener sauceListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            burger.setmSauceCal(seekBar.getProgress());
            displayCalories();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private OnClickListener baconListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(((CheckBox ) v ).isChecked()){
                burger.setmProsciuttoCal(Burger.PROSCIUTTO);
            }else{
                burger.clearProsciuttoCalories();
            }
            displayCalories();
        }
    };

    private void displayCalories(){
        calorieTV.setText("Calories: "+burger.getTotalCalories());
    }
}
