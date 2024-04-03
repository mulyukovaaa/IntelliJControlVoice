package org.ru.itmo.audio;

public interface AudioInterface {
    void start();
    void stop();

    boolean isRunning();

    String getPath();
}

