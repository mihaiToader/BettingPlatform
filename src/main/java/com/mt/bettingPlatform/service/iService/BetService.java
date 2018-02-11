package com.mt.bettingPlatform.service.iService;

import com.mt.bettingPlatform.domain.Bet;
import com.mt.bettingPlatform.domain.User;

public interface BetService {
    Bet saveBet(Bet bet);

    Bet findById(long id);

    Iterable<Bet> getByGame(long gameId);

    Iterable<Bet> getByUser(User user);

    Iterable<Bet> findAllBetsByUserAndNotFinishedGames(User user);

    void deleteBet(long id);
}
