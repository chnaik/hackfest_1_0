package com.flipkart.ekl.hackfest.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
@Slf4j
public class HttpClientUtils {
    /**
     * This method validates and returns response as string.
     *
     * @param response
     * @param errorMessage
     * @return response as string
     * @throws Exception
     */
    public static String processResponse(HttpResponse response, final String errorMessage) throws Exception {
        if(response == null){
            log.error("Response is null." + errorMessage);
            throw new Exception(errorMessage);
        }
        int responseCode = response.getStatusLine().getStatusCode();
        if(responseCode != HttpStatus.SC_OK && responseCode != HttpStatus.SC_CREATED){
            log.error("Response Code: {}, {}", responseCode, errorMessage);
            log.error(errorMessage);
            ByteArrayOutputStream os = new ByteArrayOutputStream() ;
            response.getEntity().writeTo(os);
            System.out.println("Error response: " + os.toString());
            os.close();
            throw new Exception("Bad Status:" + responseCode + " " + errorMessage);
        }

        try {
            return new BasicResponseHandler().handleResponse(response);
        } catch (HttpResponseException e) {
            log.error(errorMessage + e);
            throw e;
        } catch (IOException e) {
            log.error(errorMessage + e);
            throw e;
        }
    }


}

