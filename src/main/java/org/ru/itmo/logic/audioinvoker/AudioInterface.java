package org.ru.itmo.logic.audioinvoker;

public interface AudioInterface {
    void start();
    void stop();

    boolean isRunning();

    String getPath();
}

