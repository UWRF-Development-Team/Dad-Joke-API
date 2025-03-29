package org.falcon.legacy;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.falcon.legacy.JokeRepository;
import org.falcon.legacy.Joke;
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

    Concepts
    - AI
    - Internet request \
                       - Json
    - Question         /
 */
public class DadJoke {
    //--------------------------Valid-User-Input------------------------------
    public static int validRequest() {
        Scanner userInput = new Scanner(System.in);
        int requestCounter = 0;
        do {
            try {
                System.out.println("Enter a number of jokes to generate:");
                requestCounter = userInput.nextInt();
                if (requestCounter < 1) {
                    System.out.println("Please enter a number greater than 0.");
                } else if (requestCounter > 100) {
                    System.out.println("Please enter a number less than 100.");
                } else {
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number.");
                userInput.nextLine();
            }
        } while (true);
        return requestCounter;
    }
    //--------------------------Main-Method-----------------------------------
    public static void main(String[] args) {
        int jokeCounter = 1;
        int requestCounter = validRequest();
        while (jokeCounter <= requestCounter) {
            //--------------------Create-Model--------------------------------
            String model = "gpt-3.5-turbo-instruct";
            float temperature = 0.9f;
            int maxTokens = 300;
            String systemPrompt = "You are a dad who makes jokes that are corny" +
                    " and out-of-date. ";
            String userPrompt = "Generate a dad joke which is started with " +
                    "\\\"Joke: \\\" and if there is a punchline" +
                    " \\\"Punchline: \\\".";
            String fullPrompt = systemPrompt + userPrompt;
            //--------------------Get-API-Key---------------------------------
            String relativePath = "src/main/java/org/falcon/key.txt";
            FileLineRetriever fileLineRetriever = new FileLineRetriever(0, relativePath);
            //-------------------Make-API-Call--------------------------------
            String key = fileLineRetriever.getData();
            String urlPath = "https://api.openai.com/v1/completions";
            ApiModel apiModel = new ApiModel(model, fullPrompt, maxTokens,
                    temperature);
            ApiCall apiCall = new ApiCall(key, urlPath, apiModel);
            //-------------------Print-Response-------------------------------
            String separator = ".".repeat(50);
            System.out.println(jokeCounter + separator);
            Joke responseJoke = new Joke(apiCall.getResponse());
            responseJoke.printJoke();
            //----------------Save-Joke-to-Repository-------------------------
            File jokeFile = new File("src/main/java/org/falcon/jokes.txt");
            JokeRepository jokeRepository = new JokeRepository(jokeFile);
            if (jokeRepository.addJoke(responseJoke)) {
                jokeCounter++;
            }
        }
    }
}