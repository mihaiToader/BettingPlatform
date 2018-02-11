package com.mt.bettingPlatform.repository;

import com.mt.bettingPlatform.domain.Bet;
import com.mt.bettingPlatform.domain.Game;
import com.mt.bettingPlatform.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Transactional
public interface BetRepository extends CrudRepository<Bet, Long> {

    Iterable<Bet> findAllBetByUser(User u);

    @Query("select b from Bet b  where b.user = ?1 and b.game.finished = false")
    Iterable<Bet> findAllBetsByUserAndNotFinishedGames(User u);

    @Query("select b from Bet b  where b.game = ?1")
    ArrayList<Bet> findAllBetsByGame(Game g);

    @Query("select b from Bet b  where b.game = ?1 and b.win = true")
    ArrayList<Bet> findAllWonBetsByGame(Game g);

    @Query("select b from Bet b  where b.game = ?1 and b.win = false")
    ArrayList<Bet> findAllLostBetsByGame(Game g);

}
