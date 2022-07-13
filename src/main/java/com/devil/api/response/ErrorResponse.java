package com.devil.api.response;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse {
    private final String code;

    private final String message;

    private Map<String, String> validation = new HashMap<>();

    public void addValidatioin(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
    @Builder
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
