package com.example.jake.hellogoodbye;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView greetingTextView;

    private boolean isHello = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greetingTextView =  findViewById(R.id.textView);


        Button exclaimBtn = findViewById(R.id.button);
        exclaimBtn.setOnClickListener(toggleGreeting);


    }

    private final View.OnClickListener toggleGreeting = new View.OnClickListener(){

        public void onClick(View btn){
            if(isHello){
                greetingTextView.setText(R.string.goodbye);
            }else{
                greetingTextView.setText(R.string.hello);
            }
            isHello = ! isHello;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflates the menu
        //this adds items to the action bar if it is present

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle action bar item clicks here. the action bar will
        //automatically handle clicks on the Home/up button, so long
        //as you specify a parent activity in AndroidManifest.xml
        int id = item.getItemId();

        if(id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
