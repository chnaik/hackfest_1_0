package com.flipkart.ekl.hackfest.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by chaitanya.naik on 26/02/16.
 */
@Slf4j
public class ApacheHttpClient {

    public HttpResponse get(final String url, final Map<String, String> headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        if(headers != null && !headers.isEmpty()){
            Set<String> headerNames = headers.keySet();
            for(String name : headerNames){
                httpGet.addHeader(name, headers.get(name));
            }
        }
        return execute(httpGet);
    }

    public HttpResponse post(final String url, final HttpEntity entity, final Map<String, String> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        if(headers != null && !headers.isEmpty()){
            Set<String> headerNames = headers.keySet();
            for(String name : headerNames){
                httpPost.addHeader(name, headers.get(name));
            }
        }
        httpPost.setEntity(entity);
        return execute(httpPost);
    }

    public HttpResponse postJson(final String url, final HttpEntity entity) throws IOException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(HTTP.CONTENT_TYPE, "application/json");
        return post(url, entity, headers);
    }

    public HttpResponse put(final String url, final HttpEntity entity, final Map<String, String> headers) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        if(headers != null && !headers.isEmpty()){
            Set<String> headerNames = headers.keySet();
            for(String name : headerNames){
                httpPut.addHeader(name, headers.get(name));
            }
        }
        httpPut.setEntity(entity);
        return execute(httpPut);
    }

    public HttpResponse delete(final String url, final Map<String, String> headers) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        if(headers != null && !headers.isEmpty()){
            Set<String> headerNames = headers.keySet();
            for(String name : headerNames){
                httpDelete.addHeader(name, headers.get(name));
            }
        }
        return execute(httpDelete);
    }

    /**
     * Executes http base request.
     *
     * @param httpRequestBase
     * @return {@link HttpResponse}
     * @throws IOException
     */
    private HttpResponse execute(final HttpRequestBase httpRequestBase) throws IOException{
        HttpResponse response = null;
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            response = client.execute(httpRequestBase);
        } catch (ClientProtocolException e) {
            log.error("Error while executing http request.. {}", e);
            throw e;
        } catch (IOException e) {
            log.error("Error while executing http request.. {}", e);
            throw e;
        }
        return response;
    }
}
