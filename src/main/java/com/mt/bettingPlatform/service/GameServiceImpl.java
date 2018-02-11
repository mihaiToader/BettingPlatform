package com.mt.bettingPlatform.service;

import com.mt.bettingPlatform.domain.Game;
import com.mt.bettingPlatform.repository.GameRepository;
import com.mt.bettingPlatform.service.iService.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
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
}
