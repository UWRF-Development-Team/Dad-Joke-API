package org.falcon.jokegenerator;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AiApiCall {
    private String apiKey;
    private String url;
    private AiApiModel aiApiModel;
    private String response;
    private String requestBody;
    //----------------------------Constructors--------------------------------
    public AiApiCall(String apiKey, String url, AiApiModel aiApiModel) {
        this.apiKey = apiKey;
        this.url = url;
        this.aiApiModel = aiApiModel;
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
                }""", this.aiApiModel.getModel(),
                this.aiApiModel.getPrompt(),
                this.aiApiModel.getMaxTokens(),
                this.aiApiModel.getTemperature());
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
    public AiApiModel getApiModel() {
        return this.aiApiModel;
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
    public void setAiApiModel(AiApiModel aiApiModel) {
        this.aiApiModel = aiApiModel;
    }
    public void setResponse(String response) {
        this.response = response;
    }
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
