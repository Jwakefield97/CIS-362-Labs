package com.example.jake.backgroundthreads;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    public static Integer count;
    BackgroundCounter counter;

    public Handler threadHandler = new Handler(){
        public void handleMessage(Message message){
            TextView resultTB = findViewById(R.id.result);
            resultTB.setText(count.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        count = 0;
        threadHandler.sendEmptyMessage(0);
        if(counter != null){
            counter.cancel(false);
        }
        counter = new BackgroundCounter(threadHandler);
        counter.execute();
    }

    public void stop(View view) {
        counter.cancel(false);
    }


}
