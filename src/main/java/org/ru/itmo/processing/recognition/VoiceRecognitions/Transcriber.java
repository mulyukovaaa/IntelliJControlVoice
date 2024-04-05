package org.ru.itmo.processing.recognition.VoiceRecognitions;

import java.util.concurrent.CompletableFuture;

public interface Transcriber {
    public CompletableFuture<String> transcribeAudio(String filePath);
}
