package com.mt.bettingPlatform.repository;

import com.mt.bettingPlatform.domain.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
