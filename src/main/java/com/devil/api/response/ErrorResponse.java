package com.devil.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private final String code;

    private final String message;

    private Map<String, String> validation = new HashMap<>();

    public void addValidatioin(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
