package com.mt.bettingPlatform.controller;

import com.mt.bettingPlatform.domain.Bet;
import com.mt.bettingPlatform.domain.Game;
import com.mt.bettingPlatform.domain.User;
import com.mt.bettingPlatform.service.iService.BetService;
import com.mt.bettingPlatform.service.iService.GameService;
import com.mt.bettingPlatform.service.iService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class DefaultController {

    private final UserService userService;
    private final GameService gameService;
    private final BetService betService;

    @Autowired
    public DefaultController(UserService userService, GameService gameService, BetService betService) {
        this.userService = userService;
        this.gameService = gameService;
        this.betService = betService;
    }

    @GetMapping("/")
    public String home1() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(ModelMap model, Principal principal) {
        if (principal != null){
            User user = userService.findByName(principal.getName());
            Map<Game, Bet> bets = StreamSupport.stream(betService.findAllBetsByUserAndNotFinishedGames(user).spliterator(), false)
                    .collect(Collectors.toMap(Bet::getGame,
                            Function.identity()));
            model.addAttribute("user", user);
            model.addAttribute("bets", bets);
        }
        ArrayList<Game> finishedGames = new ArrayList<>();
        ArrayList<Game> progressGames = new ArrayList<>();
        gameService.findAll().forEach(game -> {
            if (game.isFinished()) {
                finishedGames.add(game);
            } else {
                progressGames.add(game);
            }
        });
        model.addAttribute("finishedGames", finishedGames);
        model.addAttribute("progressGames", progressGames);

        return "views/home";
    }

    @GetMapping("/admin")
    public String admin(ModelMap model, Principal principal) {
        model.addAttribute("user", userService.findByName(principal.getName()));
        return "views/admin";
    }

    @GetMapping("/about")
    public String about(ModelMap model, Principal principal) {
        model.addAttribute("user", userService.findByName(principal.getName()));
        model.addAttribute("welcome", "asd");
        return "views/about";
    }

    @GetMapping("/login")
    public String login() {
        return "views/login";
    }

    @GetMapping("/403")
    public String error403(ModelMap model, Principal principal) {
        if (principal != null){
            model.addAttribute("user", userService.findByName(principal.getName()));
        }
        return "/error/403";
    }

}
