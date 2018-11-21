package com.example.jake.stopwatch;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timeDisplay;
    private Button startBtn;
    private Button stopBtn;
    private Button resetBtn;

    private WatchTime watchTime;
    private long timeInMilliseconds = 0L;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeDisplay = findViewById(R.id.textView);
        startBtn = findViewById(R.id.button1);
        stopBtn = findViewById(R.id.button2);
        resetBtn = findViewById(R.id.button3);

        //disable stop/reset buttons
        stopBtn.setEnabled(false);
        resetBtn.setEnabled(false);

        watchTime = new WatchTime();

        handler = new Handler();
    }

    public void startTimer(View view){
        stopBtn.setEnabled(true);
        startBtn.setEnabled(false);
        resetBtn.setEnabled(true);

        watchTime.setStartTime(SystemClock.uptimeMillis());
        handler.postDelayed(updateTimerRunnable,20);
    }

    private Runnable updateTimerRunnable = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - watchTime.getStartTime();
            watchTime.setTimeUpdate(watchTime.getStoredTime() + timeInMilliseconds);
            int time = (int) watchTime.getTimeUpdate() / 1000;
            int minutes = time/60;
            int seconds = time%60;
            int milliseconds = (int) watchTime.getTimeUpdate() % 1000;
            timeDisplay.setText(String.format("%02d",minutes)+":"+String.format("%02d",seconds)+":"+String.format("%02d",milliseconds));
            handler.postDelayed(this,0);
        }
    };

    public void stopTimer(View view){
        stopBtn.setEnabled(false);
        startBtn.setEnabled(true);
        resetBtn.setEnabled(true);

        watchTime.addStoredTime(timeInMilliseconds);

        //clear message queue
        handler.removeCallbacks(updateTimerRunnable);
    }

    public void resetTimer(View view){
        watchTime.resetWatchTime();
        timeInMilliseconds = 0L;

        timeDisplay.setText("00:00:00");
    }
}
