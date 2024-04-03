package org.ru.itmo.VoiceMatchToCommand;

import java.util.concurrent.CompletableFuture;

import org.ru.itmo.VoiceMatchToCommand.ParserCommand.SimpleParseCommand;
import org.ru.itmo.VoiceMatchToCommand.VoiceRecognitions.OpenAITranscriber;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class VoiceMatchToCommand {

    public static String math(String path) {
        String transcription = loadTranscription(path);

        return SimpleParseCommand.parse(transcription);
    }

    private static String loadTranscription(String path) {
        OpenAITranscriber transcriber = new OpenAITranscriber();
        CompletableFuture<String> transcriptionFuture = transcriber.transcribeAudio(path);

        String transcriptionResult = transcriptionFuture.join();
        String transcription = parseTranscriptionText(transcriptionResult);

        return transcription;
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
