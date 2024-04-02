package org.ru.itmo.logic.audio;

import org.ru.itmo.actions.ToolbarIconAction;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;



public class SoundRecordingUtil {
    private TargetDataLine line;
    private final AudioFormat format;
    private final File outputFile;

    public Logger logger = Logger.getLogger(ToolbarIconAction.class.getName());
    public SoundRecordingUtil(String path) {
        format = new AudioFormat(44100, 16, 2, true, false);
//        outputFile = new File("records\\recorded.wav");
        outputFile = new File(path);
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
        logger.info("Out file: " + outputFile.getAbsolutePath());
    }

    public void stop() {
        if (line != null) {
            line.stop();
            line.close();
            line = null;
        }
    }
}

