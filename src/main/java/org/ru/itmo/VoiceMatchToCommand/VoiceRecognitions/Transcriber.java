package org.ru.itmo.VoiceMatchToCommand.VoiceRecognitions;

import java.util.concurrent.CompletableFuture;

public interface Transcriber {
    public CompletableFuture<String> transcribeAudio(String filePath);
}
