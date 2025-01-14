package com.example.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused")
public class AiAgentConfiguration {
    @Value("${openai.apikey}")
    private String apiKey;
    @Value("${openai.apiurl}")
    private String apiUrl;
    @Value("${openai.model}")
    private String openAiModel;
    @Value("${USER_AVATAR}")
    private String userAvatar;
    @Value("${AI_AVATAR}")
    private String aiAvatar;
    @Value("${SYSTEM_AVATAR}")
    private String systemAvatar;
    @Bean
    public String apiUrl() {
        return this.apiUrl;
    }

    @Bean
    public String openAiModel() {
        return this.openAiModel;
    }
    @Bean
    public String apiKey() {
        return this.apiKey;
    }
    @Bean
    public String userAvatar() {
        return this.userAvatar;
    }

    @Bean
    public String aiAvatar() {
        return this.aiAvatar;
    }
    @Bean
    public String systemAvatar() {
        return this.systemAvatar;
    }
}
