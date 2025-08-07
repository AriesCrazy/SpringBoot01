package com.by.service;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

@Service //组件
public class AiService {
    public String select(String request) {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("https://openrouter.ai/api/v1") // OpenRouter 的 API 地址
                .apiKey("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")        // 用你的 OpenRouter key 替换
                .modelName("deepseek/deepseek-chat-v3-0324:free")     // 选用 OpenRouter 支持的模型
                .build();

        String answer = model.generate(request);
        return answer;
    }
}
