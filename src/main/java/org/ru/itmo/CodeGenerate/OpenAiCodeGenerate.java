package org.ru.itmo.CodeGenerate;

import okhttp3.*;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OpenAiCodeGenerate implements CodeGenerate {

    private final OkHttpClient client = new OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build();

    private final String apiURL = "https://api.openai.com/v1/chat/completions";
    private final String apiKey = System.getenv("OPENAI_API_KEY");

    @Override
    public String getResultGenerate(String prompt) {
        CompletableFuture<String> codeTask = generate(prompt);
        String codeJson = codeTask.join();
        try {
            JsonObject jsonObj = JsonParser.parseString(codeJson).getAsJsonObject();
            JsonArray array = jsonObj.get("choices").getAsJsonArray();
            jsonObj = array.get(0).getAsJsonObject().get("message").getAsJsonObject();
            return jsonObj.get("content").getAsString();
        } catch (Exception e) {
            System.err.println("Error parsing transcription JSON: " + e.getMessage());
            return null;
        }
    }

    @Override
    public CompletableFuture<String> generate(String prompt) {
        String json = "{"
                + "\"model\": \"gpt-3.5-turbo-0125\","
                + "\"messages\": ["
                + "{"
                + "\"role\": \"system\","
                + "\"content\": \"You are a helpful assistant. You work as an assistant for writing code. Return as a response only the code that the user requested\""
                + "},"
                + "{"
                + "\"role\": \"user\","
                + "\"content\": \"" + prompt + "\""
                + "}"
                + "]"
                + "}";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(json, JSON);

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
