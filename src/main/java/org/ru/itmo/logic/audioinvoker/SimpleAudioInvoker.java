package org.ru.itmo.logic.audioinvoker;

import org.ru.itmo.logic.audio.SoundRecordingUtil;

public class SimpleAudioInvoker implements AudioInterface{
    SoundRecordingUtil audioUtil;
    boolean running;

    public SimpleAudioInvoker() {
        audioUtil = new SoundRecordingUtil("C:\\Coding\\Hack\\IntelliJControlVoice\\records\\test.wav"); // TODO: Change
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
}
