package com.example.jake.backgroundthreadslooper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static Integer count;
    LooperThread thread;
    boolean isRunning = true;

    public Handler threadHandler = new Handler(){
        public void handleMessage(Message message){
            TextView resultTB = findViewById(R.id.result);
            resultTB.setText(count.toString());
        }
    };

    private Runnable countNumbers = new Runnable() {
        @Override
        public void run() {
            while(isRunning){
                count++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadHandler.sendEmptyMessage(0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread = new LooperThread();
        thread.start();
    }

    public void start(View view) {
        isRunning = true;
        count = 0;
        thread.handler.post(countNumbers);
    }

    public void stop(View view) {
        isRunning = false;
    }
}
