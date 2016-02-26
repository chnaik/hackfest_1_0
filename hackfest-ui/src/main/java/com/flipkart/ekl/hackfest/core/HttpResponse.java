package com.flipkart.ekl.hackfest.core;

/**
 * Created by chaitanya.naik on 25/02/16.
 */
import lombok.Getter;

/**
 * Created by chaitanya.naik on 07/01/16.
 */
@Getter
public class HttpResponse {
    private int code = 0;
    private String message = "SUCCESS";

    public HttpResponse() {

    }

    public HttpResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
