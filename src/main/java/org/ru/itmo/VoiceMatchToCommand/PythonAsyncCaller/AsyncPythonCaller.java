package org.ru.itmo.VoiceMatchToCommand.PythonAsyncCaller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import ai.grazie.utils.mpp.StringBuilder;

public class AsyncPythonCaller {

    public static String main(String pathToVoice, String source) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<?> future = executor.submit(() -> {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(source, pathToVoice, "--model", "base", "--verbose", "True");
                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder results = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    results.append(line);
                }

                System.out.println(results.toString());
                return results.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        });

        try {
            return (String) future.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            executor.shutdown();
        }
    }
}
