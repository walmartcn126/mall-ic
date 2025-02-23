package com.example.mall.ic.qwen;

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

public class QwenHttpCallMain {
    private static final String API_KEY = System.getenv("DASHSCOPE_API_KEY");
    private static List<Map<String, String>> conversationHistory = new ArrayList<>();
    
    private static final String API_ENDPOINT = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        // 已调通
        try {
            String response = chat("请介绍一下你自己");
            System.out.println("千问回复: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String chat(String userMessage) throws Exception {
        // 构建消息列表
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of(
            "role", "system",
            "content", "你是一个专业的Java开发工程师，请用专业且友善的语气回答问题。"
        ));
        messages.add(Map.of("role", "user", "content", userMessage));

        // 构建请求体
        Map<String, Object> input = new HashMap<>();
        input.put("messages", messages);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "qwen-turbo");
        requestBody.put("input", input);  // 将messages包装在input字段中

        // 转换为JSON
        String jsonBody = objectMapper.writeValueAsString(requestBody);

        // 构建HTTP请求
        Request request = new Request.Builder()
                .url(API_ENDPOINT)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(jsonBody, MediaType.parse("application/json")))
                .build();

        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println("原始响应内容：" + responseBody);  
                throw new RuntimeException("API调用失败: " + response.code());
            }

            String responseBody = response.body().string();
            @SuppressWarnings("unchecked")
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
            
            // 解析响应获取生成的文本
            @SuppressWarnings("unchecked")
            Map<String, Object> output = (Map<String, Object>) responseMap.get("output");
            return (String) output.get("text");
        }
    }
}