package com.mt.bettingPlatform.service.iService;

import com.mt.bettingPlatform.domain.Game;

public interface GameService {

    Game saveGame(Game game);

    Iterable<Game> findAll();
}
