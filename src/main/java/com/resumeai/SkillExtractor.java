package com.resumeai;

import com.google.gson.*;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SkillExtractor {
    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY");

    public static String extractSkills(String resumeText) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("model", "gpt-3.5-turbo");
        jsonMap.put("messages", new Object[]{Map.of("role", "user", "content", "Extract technical skills from this resume:
" + resumeText)});

        RequestBody body = RequestBody.create(gson.toJson(jsonMap), MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer " + OPENAI_API_KEY)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            JsonObject json = JsonParser.parseString(response.body().string()).getAsJsonObject();
            return json.getAsJsonArray("choices").get(0).getAsJsonObject().getAsJsonObject("message").get("content").getAsString();
        }
    }
}
