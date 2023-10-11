package org.example;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        // Get API Key
        String path = "C:\\Users\\olive\\GitHub\\Dad-Joke-API\\" +
                "Dad-Joke-API\\src\\main\\java\\org\\example\\key.txt";
        FileLineRetriever fileLineRetriever = new FileLineRetriever(0, path);
        String key = fileLineRetriever.getData();
        // Formulate Request
        HttpClient httpClient = HttpClient.newHttpClient();
        int maxTokens = 50;
        String prompt = "You are a dad who makes jokes that are corny and" +
                " out-of-date. Generate a dad joke which is started" +
                " with \"Joke: \" and if there is a punchline" +
                " \"Punchline: \".";
        String promptJson = String.format("""
                '{
                     "model": "gpt-3.5-turbo",
                     "messages": [{"role": "user", "content": "%s"}],
                     "temperature": 0.7
                   }'""", prompt);
        String urlPath ="https://api.openai.com/v1/chat/completions";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                                  .uri(URI.create(urlPath))
                                  .POST(HttpRequest.BodyPublishers.ofString(promptJson))
                                  .setHeader("Authorization", "Bearer " + key)
                                  .setHeader("Content-Type", "application/json")
                                    .setHeader("Accept", "application/json")

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