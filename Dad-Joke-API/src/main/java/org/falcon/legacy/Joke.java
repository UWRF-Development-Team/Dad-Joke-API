package org.falcon.legacy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Joke {
    String responseJson;
    String joke;
    String punchline; // TODO: make this an Optional<String>
    //----------------------------Constructors--------------------------------
    public Joke(String responseJson) {
        this.responseJson = responseJson;
        this.parseJson();
    }
    //-----------------------------Parse-JSON---------------------------------
    public void parseJson() {
        //-------------------------Match-Joke---------------------------------
        Pattern jokeRegex = Pattern.compile("(Joke: .*)(\\\\n)(Punchline: .*)");
        // Find joke until "\n" is found, then find punchline until end of line
        Matcher jokeMatcher = jokeRegex.matcher(this.responseJson);
        String jokeUncut = "";
        String punchlineUncut = "";
        while (jokeMatcher.find()) {
            jokeUncut = jokeMatcher.group(1);
            punchlineUncut = jokeMatcher.group(3);
        }
        //----------------------Strip-Artifacts-------------------------------
        String jokeDelimit = "Joke: ";
        String punchlineDelimit = "Punchline: ";
        String joke = jokeUncut.substring(jokeDelimit.length(),
                                          jokeUncut.length());
        joke = joke.replaceAll("\\\\n", "").replace("\\\\r", "")
                   .replace("\\", "")
                .trim();
        String punchline = punchlineUncut.substring(punchlineDelimit.length(),
                                                    punchlineUncut.length() - 2);
        punchline = punchline.replace("\\\"", "\"")
                             .replaceAll("\\\\n", "").trim();
        // Cut ending by 2 to remove unquote into comma
        //------------------------Set-Fields----------------------------------
        this.joke = joke;
        this.punchline = punchline;
    }
    //-----------------------------Print-Joke---------------------------------
    public void printJoke() {
        System.out.printf("%s\n%s\n", this.joke, this.punchline);
    }
    //------------------------------Getters-----------------------------------
    public String getJoke() {
        return this.joke;
    }
    public String getPunchline() {
        return this.punchline;
    }
    //------------------------------Setters-----------------------------------
    public void setJoke(String joke) {
        this.joke = joke;
    }
    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }
}
