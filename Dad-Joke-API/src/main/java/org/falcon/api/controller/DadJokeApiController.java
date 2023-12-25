package org.falcon.api.controller;

import org.falcon.api.service.DadJokeService;
import org.falcon.jokegenerator.DadJokeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DadJokeApiController {
    @Autowired
    DadJokeService dadJokeGenerator;
    @GetMapping("/joke")
    public String getJoke() {
        this.dadJokeGenerator.getDadJokeGenerator().fetchJoke();
        return this.dadJokeGenerator.getJoke();
    }
}
