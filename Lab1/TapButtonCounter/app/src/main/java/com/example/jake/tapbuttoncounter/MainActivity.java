package com.example.jake.tapbuttoncounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //model
    private Counter count;
    //view
    private TextView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = new Counter();
        countView = findViewById(R.id.textView);
    }

    public void countTap(View view){
        count.addCount();
        countView.setText(count.getCount().toString());
    }



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
