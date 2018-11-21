package com.example.jake.backgroundthreadslooper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class LooperThread extends Thread{
    public static Handler handler;

    public void run() {
        Looper.prepare();
        handler = new Handler() {
            public void handleMessage(Message msg) {
            }
        };
        Looper.loop();
    }
}
