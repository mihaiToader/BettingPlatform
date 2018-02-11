package com.mt.bettingPlatform.repository;

import com.mt.bettingPlatform.domain.Game;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface GameRepository extends CrudRepository<Game, Long> {
}
