package org.ru.itmo.VoiceMatchToCommand.VoiceRecognitions;

import okhttp3.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class OpenAITranscriber implements Transcriber {

    private final OkHttpClient client = new OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build();

    private final String apiURL = "https://api.openai.com/v1/audio/transcriptions";
    private final String apiKey = System.getenv("OPENAI_API_KEY");

    @Override
    public CompletableFuture<String> transcribeAudio(String filePath) {
        File file = new File(filePath);

        RequestBody fileBody = RequestBody.create(file, MediaType.parse("audio/mp3"));

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("model", "whisper-1")
                .addFormDataPart("language", "en") // toDO: change
                .build();

        Request request = new Request.Builder()
                .url(apiURL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(requestBody)
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
