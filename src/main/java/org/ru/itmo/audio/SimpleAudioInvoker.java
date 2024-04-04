package org.ru.itmo.audio;

import org.ru.itmo.audio.record.SoundRecordingUtil;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleAudioInvoker implements AudioInterface{
    private final SoundRecordingUtil audioUtil;
    private String path;

    {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            path = System.getProperty("java.io.tmpdir") + "JControl\\recordings\\";
            try {
                Files.createDirectories(Paths.get(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (os.contains("linux") || os.contains("unix")) {
            path = System.getProperty("java.io.tmpdir") + "/JControl/recordings/";
            try {
                Files.createDirectories(Paths.get(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Mac!
            path = System.getProperty("java.io.tmpdir") + "JControl/recordings/";
        }

        path += "record.wav";
    }
    boolean running;

    public SimpleAudioInvoker() {
        audioUtil = new SoundRecordingUtil(path); // TODO: Change
    }
    @Override
    public void start() {
        if (!running) {
            running = true;
            audioUtil.start();
        }
    }

    @Override
    public void stop() {
        if (running) {
            running = false;
            audioUtil.stop();
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public String getPath() {
        return path;
    }
}
