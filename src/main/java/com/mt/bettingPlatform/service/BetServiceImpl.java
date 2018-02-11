package com.mt.bettingPlatform.service;

import com.mt.bettingPlatform.domain.Bet;
import com.mt.bettingPlatform.domain.User;
import com.mt.bettingPlatform.repository.BetRepository;
import com.mt.bettingPlatform.service.iService.BetService;
import org.springframework.stereotype.Service;

@Service
public class BetServiceImpl implements BetService {

    private final BetRepository betRepository;

    public BetServiceImpl(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @Override
    public Bet saveBet(Bet bet) {
        return betRepository.save(bet);
    }

    @Override
    public Bet findById(long id) {
        return betRepository.findOne(id);
    }

    @Override
    public Iterable<Bet> getByGame(long gameId) {
        return null;
    }

    @Override
    public Iterable<Bet> getByUser(User user) {
        return betRepository.findAllBetByUser(user);
    }

    @Override
    public Iterable<Bet> findAllBetsByUserAndNotFinishedGames(User user) {
        return betRepository.findAllBetsByUserAndNotFinishedGames(user);
    }

    @Override
    public void deleteBet(long id) {
        betRepository.delete(id);
    }
}
