package com.example.application.model;

import com.example.application.OpenAI;

public class APIError {
    private ErrorDetail error;

    public ErrorDetail getError() {
        return error;
    }

    public void setError(ErrorDetail error) {
        this.error = error;
    }
}
