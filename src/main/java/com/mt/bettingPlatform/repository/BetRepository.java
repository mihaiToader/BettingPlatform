package com.mt.bettingPlatform.repository;

import com.mt.bettingPlatform.domain.Bet;
import com.mt.bettingPlatform.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface BetRepository extends CrudRepository<Bet, Long> {

    Iterable<Bet> findAllBetByUser(User u);

    @Query("select b from Bet b  where b.user = ?1 and b.game.finished = false")
    Iterable<Bet> findAllBetsByUserAndNotFinishedGames(User u);
}
