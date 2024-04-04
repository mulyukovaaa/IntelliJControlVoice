package org.ru.itmo.audio;

import org.ru.itmo.audio.record.SoundRecordingUtil;

public class SimpleAudioInvoker implements AudioInterface{
    private final SoundRecordingUtil audioUtil;
    private String path = "C:\\Coding\\Hack\\IntelliJControlVoice\\records\\recorded.wav";
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
