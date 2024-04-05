package org.ru.itmo.processing.recognition;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.ru.itmo.processing.recognition.ParserCommand.SimpleParseCommand;
import org.ru.itmo.processing.recognition.VoiceRecognitions.OfflineWhisperTranscriber;
import org.ru.itmo.processing.recognition.VoiceRecognitions.OpenAITranscriber;
import org.ru.itmo.processing.recognition.VoiceRecognitions.Transcriber;
import org.ru.itmo.processing.settings.AppSettingsState;
import org.ru.itmo.processing.settings.TranscriberType;

import java.util.concurrent.CompletableFuture;

public class VoiceMatchToCommand {

    public static String match(String path) {
        String transcription = loadTranscription(path);
        return SimpleParseCommand.parse(transcription);
    }

    private static String loadTranscription(String path) {
        Transcriber transcriber;
        AppSettingsState settings = AppSettingsState.getInstance();

        if (settings.transcriberType.equals(TranscriberType.ONLINE)) {
            transcriber = new OpenAITranscriber();
        } else {
            transcriber = new OfflineWhisperTranscriber();
        }

        CompletableFuture<String> transcriptionFuture = transcriber.transcribeAudio(path);
        String transcriptionResult = transcriptionFuture.join();
        return parseTranscriptionText(transcriptionResult);
    }

    private static String parseTranscriptionText(String transcriptionJson) {
        try {
            JsonObject jsonObj = JsonParser.parseString(transcriptionJson).getAsJsonObject();
            return jsonObj.get("text").getAsString();
        } catch (Exception e) {
            System.err.println("Error parsing transcription JSON: " + e.getMessage());
            return "";
        }
    }
}
