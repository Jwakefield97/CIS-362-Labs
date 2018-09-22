package com.example.jake.lifecycleexperiments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String createMsg;
    private String startMsg;
    private String resumeMsg;
    private String pauseMsg;
    private String stopMsg;
    private String restartMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initMessages();

        showToast(createMsg);
    }

    private void initMessages(){
        createMsg = getResources().getText(R.string.create_message).toString();
        startMsg = getResources().getText(R.string.start_message).toString();
        resumeMsg = getResources().getText(R.string.resume_message).toString();
        pauseMsg = getResources().getText(R.string.pause_message).toString();
        stopMsg = getResources().getText(R.string.stop_message).toString();
        restartMsg = getResources().getText(R.string.restart_message).toString();
    }

    //added this method to make my life easier
    private void showToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        Log.d("msg", msg);
    }

    @Override
    protected void onStart(){
        super.onStart();
        showToast(startMsg);
    }

    @Override
    protected void onResume(){
        super.onResume();
        showToast(resumeMsg);
    }

    @Override
    protected void onPause(){
        super.onPause();
        showToast(pauseMsg);
    }

    @Override
    protected void onStop(){
        super.onStop();
        showToast(stopMsg);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        showToast(restartMsg);
    }
}
