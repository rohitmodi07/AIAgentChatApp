package com.example.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

public class Message {

    @JsonIgnore
    private Instant time;

    @JsonIgnore
    private String refusal;
    private String role;
    private String content;

    public Message() {
        this.time = Instant.now();
        this.refusal = null;
    }

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
        this.time = Instant.now();
        this.refusal = null;
    }

    public Message(String role, String content, Instant time) {
        this.role = role;
        this.content = content;
        this.time = time;
    }

    public Message(String role, String content, Instant time, String refusal) {
        this.role = role;
        this.content = content;
        this.time = time;
        this.refusal = refusal;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getTime() {
        return time;
    }

    public String getRefusal() {
        return refusal;
    }
}
