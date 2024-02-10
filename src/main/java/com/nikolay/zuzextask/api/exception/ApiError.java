package com.nikolay.zuzextask.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ApiError {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Moscow")
    private Date timestamp;
    private int code;
    private String message;
    private String path;

    public ApiError(int code, String message, String path) {
        timestamp = new Date();
        this.code = code;
        this.message = message;
        this.path = path;
    }
}
