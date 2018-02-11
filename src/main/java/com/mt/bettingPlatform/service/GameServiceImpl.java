package com.mt.bettingPlatform.service;

import com.mt.bettingPlatform.domain.Game;
import com.mt.bettingPlatform.repository.GameRepository;
import com.mt.bettingPlatform.service.iService.BetService;
import com.mt.bettingPlatform.service.iService.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final BetService betService;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, BetService betService) {
        this.gameRepository = gameRepository;
        this.betService = betService;
    }

    @Override
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Iterable<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game findById(long id) {
        return gameRepository.findOne(id);
    }

    @Override
    public Game updateGame(Game game) {
        Game g = saveGame(game);
        betService.calculateBetsForGame(game);
        return g;
    }
}
