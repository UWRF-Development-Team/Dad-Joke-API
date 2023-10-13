package org.falcon;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/*
    Request
    - API Key
    - Body (Prompt)
    - Headers
    - URL
    Response
    - Body (Joke)
    Prompt
    - Tokens
    - Temperature
    - Model
    - Prompt request
 */
public class Main {
    public static void main(String[] args) {
        // Get API Key
//        String path = "C:\\Users\\olive\\GitHub\\Dad-Joke-API\\" +
//                "Dad-Joke-API\\src\\main\\java\\org\\falcon\\key.txt";
        String relativePath = "src/main/java/org/falcon/key.txt";
        FileLineRetriever fileLineRetriever = new FileLineRetriever(0, relativePath);
        String key = fileLineRetriever.getData();
        // Formulate Request
        HttpClient httpClient = HttpClient.newHttpClient();
        int maxTokens = 50;
        String systemPrompt = "You are a dad who makes jokes that are corny" +
                " and out-of-date. ";
        String userPrompt =  "Generate a dad joke which is started" +
                " with \\\"Joke: \\\" and if there is a punchline" +
                " \\\"Punchline: \\\".";
        String fullPrompt = systemPrompt + userPrompt;
        String promptJson = String.format("{\n" +
                "    \"model\": \"gpt-3.5-turbo-instruct\",\n" +
                "    \"prompt\": \"%s\",\n" +
                "    \"max_tokens\": 100,\n" +
                "    \"temperature\": 0.7\n" +
                "  }", fullPrompt);
        String urlPath ="https://api.openai.com/v1/completions";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                                  .uri(URI.create(urlPath))
                                  .setHeader("Authorization", "Bearer " + key)
                                  .setHeader("Content-Type", "application/json")
                                  .POST(HttpRequest.BodyPublishers.ofString(promptJson))
                                  .build();
        // Send Request
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        // Get and Print Response
        String response = httpResponse.body();
        System.out.println(response);
    }
}