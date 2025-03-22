package com.project.javacrm.utils;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestResponse<T> {
    private int status;
    private String message;
    private String returnState;
    private T data;
    private Instant timestamp;

    private RestResponse(int status, String message, String returnState, T data) {
        this.status = status;
        this.message = message;
        this.returnState = returnState;
        this.data = data;
        this.timestamp = Instant.now();
    }

    public static <T> RestResponse<T> buildSuccessResponse(HttpStatus status, String message, T data) {
        String OK = "OK";
        return new RestResponse<>(status.value(), message, OK, data);
    }

    public static <T> RestResponse<T> buildErrorResponse(HttpStatus status, String message, T data) {
        String KO = "Error";
        return new RestResponse<>(status.value(), message, KO, data);
    }
}
