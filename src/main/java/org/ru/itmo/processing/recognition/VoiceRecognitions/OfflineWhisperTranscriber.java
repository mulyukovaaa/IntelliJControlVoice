package org.ru.itmo.processing.recognition.VoiceRecognitions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

public class OfflineWhisperTranscriber implements Transcriber {
    final String url = "http://localhost:5001/transcribe";

    @Override
    public CompletableFuture<String> transcribeAudio(String filePath) {
        HttpClient client = HttpClient.newHttpClient();
        Path path = Paths.get(filePath);
        String boundary = "Boundary" + System.currentTimeMillis();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(("--" + boundary + "\r\n").getBytes());
            baos.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + path.getFileName() + "\"\r\n").getBytes());
            baos.write(("Content-Type: application/octet-stream\r\n\r\n").getBytes());
            baos.write(Files.readAllBytes(path));
            baos.write(("\r\n--" + boundary + "--\r\n").getBytes());

            if (1 == 1) { //toDO: change
                String language = "en";
                baos.write(("Content-Disposition: form-data; name=\"language\"\r\n\r\n").getBytes());
                baos.write(language.getBytes());
                baos.write(("\r\n--" + boundary + "--\r\n").getBytes());
            }
        } catch (IOException e) {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                .POST(HttpRequest.BodyPublishers.ofByteArray(baos.toByteArray()))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .exceptionally(e -> "Error during transcription: " + e.getMessage());
    }
}
