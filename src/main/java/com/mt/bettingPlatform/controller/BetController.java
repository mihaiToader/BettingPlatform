package com.mt.bettingPlatform.controller;

import com.mt.bettingPlatform.domain.Bet;
import com.mt.bettingPlatform.domain.Game;
import com.mt.bettingPlatform.domain.User;
import com.mt.bettingPlatform.service.iService.BetService;
import com.mt.bettingPlatform.service.iService.GameService;
import com.mt.bettingPlatform.service.iService.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class BetController
{

    private final GameService gameService;
    private final UserService userService;
    private final BetService betService;

    public BetController(GameService gameService, UserService userService, BetService betService)
    {
        this.gameService = gameService;
        this.userService = userService;
        this.betService = betService;
    }

    @GetMapping("/user/addBet/{gameId}")
    public String addBet(Model model, Principal principal, @PathVariable long gameId)
    {
        Game game = gameService.findById(gameId);
        if (game.isFinished())
        {
            game = null;
        }
        model.addAttribute("gameDto", game);
        model.addAttribute("user", userService.findByName(principal.getName()));
        return "views/addBet";
    }

    @GetMapping("/user/updateBet/{betId}")
    public String updateBet(Model model, Principal principal, @PathVariable long betId)
    {
        Bet bet = betService.findById(betId);
        if (bet != null)
        {
            model.addAttribute("gameDto", bet.getGame());
        }
        model.addAttribute("betDto", bet);
        model.addAttribute("user", userService.findByName(principal.getName()));
        return "views/addBet";
    }

    @PostMapping("/user/addBetSubmit/{gameId}")
    public String addBetSubmit(Bet bet, Model model, Principal principal, @PathVariable long gameId)
    {
        Game game = gameService.findById(gameId);
        User user = userService.findByName(principal.getName());
        if (game == null || game.isFinished())
        {
            return "views/addBet";
        }
        if (bet.getId() != null)
        {
            Bet betToBeUpdated = betService.findById(bet.getId());
            betToBeUpdated.setAmount(bet.getAmount());
            betToBeUpdated.setType(bet.getType());
            if (bet.getAmount().equals(0))
            {
                betService.deleteBet(bet.getId());
            }
            else
            {
                betService.saveBet(betToBeUpdated);
            }
        }
        else if (!bet.getAmount().equals(0))
        {
            bet.setGame(game);
            bet.setUser(user);
            betService.saveBet(bet);
        }

        return "redirect:/home";
    }

    @GetMapping("/user/bets/{gameId}")
    public String getBetsForGame(Model model, Principal principal, @PathVariable long gameId)
    {
        Game game = gameService.findById(gameId);
        if (game != null)
        {
            model.addAttribute("winningBets", betService.findAllWonBetsByGame(game));
            model.addAttribute("losingBets", betService.findAllLostBetsByGame(game));
            model.addAttribute("allBets", betService.findAllLostBetsByGame(game));
            model.addAttribute("everythingWasPay", betService.allBetsWerePay(betService.getByGame(game)));
        }

        model.addAttribute("game", game);
        model.addAttribute("user", userService.findByName(principal.getName()));

        return "views/gameBets";
    }

    @GetMapping("/admin/bet/pay/{isPaying}/{betId}")
    public String payBetsForGame(Model model, Principal principal, @PathVariable boolean isPaying,
        @PathVariable long betId)
    {
        Bet bet = betService.findById(betId);
        bet.setPaid(isPaying);
        betService.saveBet(bet);
        return "redirect:/user/bets/" + bet.getGame().getId();
    }

    @GetMapping("/admin/bet/payWinning/{isPaying}/{betId}")
    public String payWinBetsForGame(Model model, Principal principal, @PathVariable boolean isPaying,
        @PathVariable long betId)
    {
        Bet bet = betService.findById(betId);
        bet.setPaidWinning(isPaying);
        betService.saveBet(bet);
        return "redirect:/user/bets/" + bet.getGame().getId();
    }
}
