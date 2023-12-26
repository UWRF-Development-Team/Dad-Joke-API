package org.falcon.jokegenerator;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.File;

public class DadJokeGenerator {
    AiApiModel model;
    Joke joke;
    //----------------------------Constructors--------------------------------
    public DadJokeGenerator() {
        this.buildModelFromJson();
        this.fetchJoke();
    }
    //-------------------------Build-Model-From-Json--------------------------
    public void buildModelFromJson() {
        //----------------------Get-Data-From-Config--------------------------
        String configFilePath = "config/ai_model_config.json";
        FileDataRetriever fileDataRetriever = new FileDataRetriever(configFilePath);
        String configFileContents = fileDataRetriever.getData();
        // Parse model
        String model = this.getValueFromKey(configFileContents, "model");
        // Parse url
        String url = this.getValueFromKey(configFileContents, "urlPath");
        // Parse tokens
        String tokens = this.getValueFromKey(configFileContents, "maxTokens");
        // Parse temperature
        String temperature = this.getValueFromKey(configFileContents, "temperature");
        // Parse prompt
        String prompt = this.getValueFromKey(configFileContents, "fullPrompt");
        //--------------------------Build-Model-------------------------------
        int tokensParsed = Integer.parseInt(tokens);
        float temperatureParsed = Float.parseFloat(temperature);
        this.model = new AiApiModel(model, prompt, tokensParsed, temperatureParsed);
    }
    //---------------------------Get-Value-From-Key---------------------------
    public String getValueFromKey(String json, String key) {
        // TODO: Implement regex parsing
        // See src/main/resources/config/ai_model_config.json
        return switch (key) {
            case "model" -> "gpt-3.5-turbo-instruct";
            case "urlPath" -> "https://api.openai.com/v1/completions";
            case "maxTokens" -> "300";
            case "temperature" -> "0.9";
            case "fullPrompt" -> "Generate a dad joke which is started with" +
                    " \\\"Joke: \\\" and if there is a punchline \\\"" +
                    "Punchline: \\\".";
            default -> throw new IllegalStateException("Unexpected value: " + key);
        };
    }
    //----------------------------Fetch-Joke----------------------------------
    public void fetchJoke() {
        boolean jokeIsUnique = false;
        String previousJoke = "";
        if (this.joke != null) {
            previousJoke = this.joke.getFullJoke();
        }
        String keyFilePath = "/key.txt";
        FileLineRetriever fileLineRetriever = new FileLineRetriever(0, keyFilePath);
        String key = fileLineRetriever.getData();
        String urlPath = this.getValueFromKey("", "urlPath");
        while (!jokeIsUnique) {
            AiApiCall aiApiCall = new AiApiCall(key, urlPath, this.model);
            String response = aiApiCall.getResponse();
            this.joke = new Joke(response);
            if (!this.joke.getFullJoke().equals(previousJoke)) {
                jokeIsUnique = true;
            }
        }
    }
    //------------------------------Getters-----------------------------------
    public Joke getJoke() {
        return this.joke;
    }
    //------------------------------Setters-----------------------------------
    public void setJoke(Joke joke) {
        this.joke = joke;
    }
}
