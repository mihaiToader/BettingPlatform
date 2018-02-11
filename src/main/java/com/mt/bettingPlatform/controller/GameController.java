package com.mt.bettingPlatform.controller;

import com.mt.bettingPlatform.domain.Game;
import com.mt.bettingPlatform.domain.User;
import com.mt.bettingPlatform.service.iService.GameService;
import com.mt.bettingPlatform.service.iService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class GameController {
    private final GameService gameService;
    private final UserService userService;

    @Autowired
    public GameController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @GetMapping("/admin/addGame")
    public String addGame(Model model, Principal principal)
    {
        String name = principal.getName();
        model.addAttribute("user", userService.findByName(principal.getName()));
        model.addAttribute("gameDto", new Game());
        return "views/addGame";
    }

    @PostMapping("/admin/addGameSubmit")
    public String addGameSubmit(@Valid Game game, Model model, Principal principal)
    {
        //TODO use valid for validation
        String name = principal.getName();
        User user = userService.findByName(name);
        if (game.getTeamA().isEmpty()) {
            model.addAttribute("error1", true);
        }
        if (game.getTeamB().isEmpty()) {
            model.addAttribute("error2", true);
        }
        if (!game.getTeamA().isEmpty() && !game.getTeamB().isEmpty()) {
            gameService.saveGame(game);
            model.addAttribute("gameCreated", true);
        }

        model.addAttribute("user", user);
        model.addAttribute("gameDto", null);
        return "views/addGame";
    }

    @GetMapping("/admin/updateGame/{gameId}")
    public String updateGame(Model model, Principal principal, @PathVariable long gameId)
    {
        model.addAttribute("user", userService.findByName(principal.getName()));
        Game game = gameService.findById(gameId);
        if (game.isFinished()) {
            game = null;
        }
        model.addAttribute("gameDto", game);
        return "views/updateGame";
    }

    @PostMapping("/admin/updateGameSubmit/{gameId}")
    public String updateGameSubmit(Game game, Model model, Principal principal, @PathVariable long gameId)
    {
        Game toUpdateGame = gameService.findById(gameId);
        toUpdateGame.setFinished(true);
        toUpdateGame.setEndingScoreTeamA(game.getEndingScoreTeamA());
        toUpdateGame.setEndingScoreTeamB(game.getEndingScoreTeamB());
        gameService.updateGame(toUpdateGame);

        return "redirect:/home";
    }
}
