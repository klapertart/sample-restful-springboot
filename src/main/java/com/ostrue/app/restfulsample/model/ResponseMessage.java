package com.ostrue.app.restfulsample.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage<T> {
    private int status;
    private String message;
    private T data;
}
