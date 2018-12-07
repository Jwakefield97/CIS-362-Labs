package com.example.jake.backgroundthreads;

import android.os.AsyncTask;
import android.os.Handler;

public class BackgroundCounter extends AsyncTask<Void, Void, Void> {

    Handler handler;

    public BackgroundCounter(Handler handler){
        this.handler = handler;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while(!isCancelled()){
            MainActivity.count++;
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.sendEmptyMessage(0);
        }
        return null;
    }
}
