package org.falcon.api.controller;

import org.falcon.api.service.DadJokeService;
import org.falcon.jokegenerator.DadJokeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DadJokeWebController {
    @Autowired
    private DadJokeService dadJokeService;
    @GetMapping("/")
    public String home(Model page) {
        String joke = this.dadJokeService.getJoke();
        page.addAttribute("jokeText", joke);
        return "index";
    }
    @GetMapping("/generate")
    public String generate(Model page) {
        this.dadJokeService.getDadJokeGenerator().fetchJoke();
        String joke = this.dadJokeService.getJoke();
        page.addAttribute("jokeText", joke);
        return "joke-patch :: joke-patch";
    }
}
