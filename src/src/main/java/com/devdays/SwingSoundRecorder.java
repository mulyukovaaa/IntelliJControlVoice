package com.devdays;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingSoundRecorder {

    private JFrame frame;
    private JLabel statusLabel;
    private SoundRecordingUtil soundRecordingUtil;
    private AudioPlayer audioPlayer;
    private RecordTimer recordTimer;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SwingSoundRecorder window = new SwingSoundRecorder();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public SwingSoundRecorder() {
        initialize();
    }

    private void initialize() {
        // Создание и инициализация JFrame
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel timeLabel = new JLabel("00:00:00");
        timeLabel.setBounds(10, 210, 100, 20);
        frame.getContentPane().add(timeLabel);

        recordTimer = new RecordTimer(timeLabel);

        JButton recordButton = new JButton("Record");
        recordButton.setBounds(10, 10, 89, 23);
        recordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soundRecordingUtil.start();
                recordTimer.start();
                statusLabel.setText("Recording...");
            }
        });
        frame.getContentPane().add(recordButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setBounds(109, 10, 89, 23);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soundRecordingUtil.stop();
                recordTimer.stop();
                statusLabel.setText("Stopped");
            }
        });
        frame.getContentPane().add(stopButton);

        JButton playButton = new JButton("Play");
        playButton.setBounds(208, 10, 89, 23);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                audioPlayer.play();
                statusLabel.setText("Playing...");
            }
        });
        frame.getContentPane().add(playButton);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(307, 10, 89, 23);

        statusLabel = new JLabel("Status: Idle");
        statusLabel.setBounds(10, 240, 414, 14);
        frame.getContentPane().add(statusLabel);

        soundRecordingUtil = new SoundRecordingUtil();
        audioPlayer = new AudioPlayer();
    }
}

