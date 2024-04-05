package org.ru.itmo.processing.recognition.VoiceRecognitions;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.ru.itmo.processing.settings.AppSettingsState;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class OpenAITranscriber implements Transcriber {
    private final OkHttpClient client = new OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build();
    private AppSettingsState settings = AppSettingsState.getInstance();

    private final String apiURL = "https://api.openai.com/v1/audio/transcriptions";
    private String apiKey =settings.getState().openAiKey;


    @Override
    public CompletableFuture<String> transcribeAudio(String filePath) {
        File file = new File(filePath);

        RequestBody fileBody = RequestBody.create(file, MediaType.parse("audio/mp3"));

        MultipartBody.Builder requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("model", "whisper-1");
        if (Objects.nonNull(settings.language.getLanguage())) {
            requestBody.addFormDataPart("language", settings.language.getLanguage());
        }

        Request request = new Request.Builder()
                .url(apiURL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(requestBody.build())
                .build();

        CompletableFuture<String> future = new CompletableFuture<>();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        future.completeExceptionally(new IOException("Unexpected code " + response));
                    } else {
                        String responseString = responseBody.string();
                        future.complete(responseString);
                    }
                }
            }
        });
        return future;
    }

}
