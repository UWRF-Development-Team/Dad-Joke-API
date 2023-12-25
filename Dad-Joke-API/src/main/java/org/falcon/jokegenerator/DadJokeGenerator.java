package org.falcon.jokegenerator;


import java.io.File;

public class DadJokeGenerator {
    AiApiModel model;
    Joke joke;
    public DadJokeGenerator() {
        this.buildModelFromJson();
        this.fetchJoke();
    }

    public void buildModelFromJson() {
        //--------------------------Get-Data-From-Config----------------------
        String configFilePath = "src/main/resources/ai_model_config.json";
        FileDataRetriever fileDataRetriever = new FileDataRetriever(configFilePath);
        String configFileContents = fileDataRetriever.getData();
        // Parse model
        String model = this.getValueFromKey(configFileContents, "model");
        // Parse url
        String url = this.getValueFromKey(configFileContents, "urlPath");
        // Parse tokens
        String tokens = this.getValueFromKey(configFileContents, "tokens");
        // Parse temperature
        String temperature = this.getValueFromKey(configFileContents, "temperature");
        // Parse prompt
        String prompt = this.getValueFromKey(configFileContents, "fullPrompt");
        //--------------------------Build-Model-------------------------------


    }
    public String getValueFromKey(String json, String key) {
        return null;
    }
    public void fetchJoke() {
        AiApiCall aiApiCall;
        String response = aiApiCall.getResponse();
        this.joke = new Joke(response);
    }
}
