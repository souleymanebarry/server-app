package com.barry.server.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;


@Data @SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private LocalDateTime timeStamp;
    private int code;
    private HttpStatus status;
    private String reason;
    private String message;
    private String developerMessage;
    private Map<?, ?> data;


}
