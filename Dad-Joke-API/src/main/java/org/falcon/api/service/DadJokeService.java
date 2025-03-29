package org.falcon.api.service;

import org.falcon.jokegenerator.DadJokeGenerator;
import org.springframework.stereotype.Service;

@Service
public class DadJokeService {
    private DadJokeGenerator dadJokeGenerator;
    public DadJokeService() {
        this.dadJokeGenerator = new DadJokeGenerator();
    }
    public String getJoke() {
        this.dadJokeGenerator.fetchJoke();
        return this.dadJokeGenerator.getJoke().toString();
    }
    public DadJokeGenerator getDadJokeGenerator() {
        return this.dadJokeGenerator;
    }
}
