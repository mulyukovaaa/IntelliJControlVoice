package org.ru.itmo.logic.audio;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class RecordTimer {
    private Timer timer;
    private final JLabel timeLabel;

    public RecordTimer(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            int seconds = 0;

            @Override
            public void run() {
                seconds++;
                timeLabel.setText(String.format("%02d:%02d:%02d", seconds / 3600, (seconds % 3600) / 60, seconds % 60));
            }
        }, 0, 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}

