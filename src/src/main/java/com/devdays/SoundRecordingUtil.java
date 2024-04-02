package com.devdays;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundRecordingUtil {
    private TargetDataLine line;
    private final AudioFormat format;
    private final File outputFile;

    public SoundRecordingUtil() {
        format = new AudioFormat(44100, 16, 2, true, false);
        outputFile = new File("recorded.wav");
    }

    public void start() {
        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            Thread thread = new Thread(() -> {
                try {
                    AudioSystem.write(new AudioInputStream(line), AudioFileFormat.Type.WAVE, outputFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (line != null) {
            line.stop();
            line.close();
            line = null;
        }
    }
}

