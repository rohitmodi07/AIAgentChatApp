package com.example.application.model;

public class CompletionTokenDetails {
    private int reasoning_tokens;
    private int audio_tokens;
    private int accepted_prediction_tokens;
    private int rejected_prediction_tokens;

    public int getReasoning_tokens() {
        return reasoning_tokens;
    }

    public void setReasoning_tokens(int reasoning_tokens) {
        this.reasoning_tokens = reasoning_tokens;
    }

    public int getAudio_tokens() {
        return audio_tokens;
    }

    public void setAudio_tokens(int audio_tokens) {
        this.audio_tokens = audio_tokens;
    }

    public int getAccepted_prediction_tokens() {
        return accepted_prediction_tokens;
    }

    public void setAccepted_prediction_tokens(int accepted_prediction_tokens) {
        this.accepted_prediction_tokens = accepted_prediction_tokens;
    }

    public int getRejected_prediction_tokens() {
        return rejected_prediction_tokens;
    }

    public void setRejected_prediction_tokens(int rejected_prediction_tokens) {
        this.rejected_prediction_tokens = rejected_prediction_tokens;
    }
}
