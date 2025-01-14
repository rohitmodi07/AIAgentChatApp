package com.example.application;

import com.example.application.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/** Java class to interact with Open AI API.
 *
 */
@Component
@Scope("session")
public class OpenAI {

    @Autowired
    private String apiUrl;
    @Autowired
    private String openAiModel;
    @Autowired
    private String apiKey;
    private String latestUserInput;
    private List<Message> chatMessages;
    private List<Message> messages;

    public OpenAI() {
        this.latestUserInput = "";
        this.chatMessages = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public CompletableFuture<List<Message>> sendAsync(String userInput) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return send(userInput);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public List<Message> send(String userInput) {
        latestUserInput = userInput;
        this.messages.add(new Message("user", userInput, Instant.now()));

        try {
            ChatRequest request = new ChatRequest(openAiModel, this.messages);
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper.writeValueAsString(request);

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setDoOutput(true);


            conn.getOutputStream().write(requestBody.getBytes());

            if (conn.getResponseCode() >= 400) {
                Scanner scanner = new Scanner(conn.getErrorStream());
                StringBuilder responseBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    responseBuilder.append(scanner.nextLine());
                }
                String errorBody = responseBuilder.toString();
                APIError error = objectMapper.readValue(errorBody, APIError.class);
                messages.add(new Message("system", error.getError().getMessage(), Instant.now()));
            } else {
                Scanner scanner = new Scanner(conn.getInputStream());
                StringBuilder responseBuilder = new StringBuilder();
                while (scanner.hasNextLine()) {
                    responseBuilder.append(scanner.nextLine());
                }
                String responseBody = responseBuilder.toString();
                System.out.println("response body is ---------"+responseBody);
                ChatResponse response = objectMapper.readValue(responseBody, ChatResponse.class);
                this.messages.addAll(response.getChoices().stream().map(Choice::getMessage).toList());
            }
        }catch (Exception e) {
            messages.add(new Message("system", e.getMessage(), Instant.now()));
        }
        return messages;
    }

}