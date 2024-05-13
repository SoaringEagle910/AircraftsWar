package edu.hitsz.Thread;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AlwaysMusicThread extends BaseMusicThread {



    public AlwaysMusicThread(String filename) {
        super(filename);
    }

    public void alwaysPlay() {
        while (true) {
            InputStream stream = new ByteArrayInputStream(samples);
            play(stream);
        }
    }


    @Override
    public void run() {
        this.setStop(false);
        alwaysPlay();
    }
}
