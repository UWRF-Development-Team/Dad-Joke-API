package org.falcon.api.controller;

import org.falcon.jokegenerator.DadJokeGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DadJokeApiController {
    DadJokeGenerator dadJokeGenerator = new DadJokeGenerator();
    @GetMapping("/joke")
    public String getJoke() {
        this.dadJokeGenerator.fetchJoke();
        return this.dadJokeGenerator.getJoke().toString();
    }
}
