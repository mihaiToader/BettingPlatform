package com.mt.bettingPlatform.service.iService;

import com.mt.bettingPlatform.domain.Bet;
import com.mt.bettingPlatform.domain.Game;
import com.mt.bettingPlatform.domain.User;

import java.util.ArrayList;

public interface BetService {
    Bet saveBet(Bet bet);

    Bet findById(long id);

    Iterable<Bet> getByGame(long gameId);

    Iterable<Bet> getByUser(User user);

    Iterable<Bet> findAllBetsByUserAndNotFinishedGames(User user);

    void deleteBet(long id);

    void calculateBetsForGame(Game game);

    ArrayList<Bet> findAllWonBetsByGame(Game g);

    ArrayList<Bet> findAllLostBetsByGame(Game g);
}
