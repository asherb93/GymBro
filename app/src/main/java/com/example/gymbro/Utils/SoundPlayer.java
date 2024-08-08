package com.example.gymbro.Utils;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SoundPlayer {

    private Context context;
    private Executor executor;
    private MediaPlayer mediaPlayer;

    public SoundPlayer(Context context) {
        this.context = context;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void playSoundOnce(int resID) {
        if (mediaPlayer == null)
            executor.execute(() -> {
                mediaPlayer = MediaPlayer.create(context, resID);
                mediaPlayer.setLooping(false);
                mediaPlayer.setVolume(1.0f, 1.0f);
                mediaPlayer.start();
            });
    }

}
