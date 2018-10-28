package com.example.jake.soundjukebox;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity {

    private SoundPool soundPool;
    private SparseIntArray soundMap;
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configSounds();
    }

    public void configSounds(){
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundMap = new SparseIntArray();
        soundMap.put(1, soundPool.load(this,R.raw.bell_clang,1));
        soundMap.put(2, soundPool.load(this,R.raw.funky_gong,1));
        soundMap.put(3, soundPool.load(this,R.raw.spooky_cry,1));
        soundMap.put(4, soundPool.load(this,R.raw.random_ha,1));

        mediaPlayer = MediaPlayer.create(this, R.raw.thuuud);
        mediaController = new MediaController(this);
        mediaController.setEnabled(true);

    }

    public void playSound(View view) {
        String buttonContent = ((Button) view).getContentDescription().toString();
        switch (buttonContent){
            case "Bell Clang":
                soundPool.play(1,1,1,1,0,1.0f);
                break;
            case "Funky Gong":
                soundPool.play(2,1,1,1,0,1.0f);
                break;
            case "Random Ha":
                soundPool.play(3,1,1,1,0,1.0f);
                break;
            case "Spooky Cry":
                soundPool.play(4,1,1,1,0,1.0f);
                break;
            case "Drum Solo":
                mediaController.show();
                mediaPlayer.start();
                break;
        }

    }
}
