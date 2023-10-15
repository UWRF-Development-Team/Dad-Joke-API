package org.falcon;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiCall {
    private String apiKey;
    private String url;
    private ApiModel apiModel;
    private String response;
    private String requestBody;
    //----------------------------Constructors--------------------------------
    public ApiCall(String apiKey, String url, ApiModel apiModel) {
        this.apiKey = apiKey;
        this.url = url;
        this.apiModel = apiModel;
        this.buildRequestBody();
        this.fetchResponse();
    }
    //-------------------------Build-Request-Body-----------------------------
    public void buildRequestBody() {
        this.requestBody = String.format("""
                {
                    "model": "%s",
                    "prompt": "%s",
                    "max_tokens": %d,
                    "temperature": %1.1f
                }""", this.apiModel.getModel(),
                      this.apiModel.getPrompt(),
                      this.apiModel.getMaxTokens(),
                      this.apiModel.getTemperature());
    }
    //---------------------------Fetch-Response-------------------------------
    public void fetchResponse() {
        HttpClient httpClient = HttpClient.newHttpClient();
        //------------------------Make-Request--------------------------------
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(this.url))
                .setHeader("Authorization", "Bearer " + this.apiKey)
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(this.requestBody))
                .build();
        //-------------------------Get-Response-------------------------------
        HttpResponse<String> responseHttp = null;
        try {
            responseHttp = httpClient.send(httpRequest,
                                           HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        if (responseHttp != null) {
            this.response = responseHttp.body();
        } else {
            this.response = "Failed to execute request.";
        }
    }
    //------------------------------Getters-----------------------------------
    public String getApiKey() {
        return this.apiKey;
    }
    public String getUrl() {
        return this.url;
    }
    public ApiModel getApiModel() {
        return this.apiModel;
    }
    public String getResponse() {
        return this.response;
    }
    public String getRequestBody() {
        return this.requestBody;
    }
    //------------------------------Setters-----------------------------------
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setApiModel(ApiModel apiModel) {
        this.apiModel = apiModel;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
