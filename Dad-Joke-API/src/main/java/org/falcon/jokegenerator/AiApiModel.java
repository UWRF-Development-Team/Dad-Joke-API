package org.falcon.jokegenerator;

public class AiApiModel {
    private String model;
    private String prompt;
    private int maxTokens;
    private float temperature;
    //----------------------------Constructors--------------------------------
    AiApiModel(String model, String prompt, int maxTokens, float temperature) {
        this.model = model;
        this.prompt = prompt;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
    }
    //------------------------------Getters-----------------------------------
    public String getModel() {
        return this.model;
    }
    public String getPrompt() {
        return this.prompt;
    }
    public int getMaxTokens() {
        return this.maxTokens;
    }
    public float getTemperature() {
        return this.temperature;
    }
    //------------------------------Setters-----------------------------------
    public void setModel(String model) {
        this.model = model;
    }
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
