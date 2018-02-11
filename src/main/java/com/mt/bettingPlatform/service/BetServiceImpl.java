package com.mt.bettingPlatform.service;

import com.mt.bettingPlatform.domain.Bet;
import com.mt.bettingPlatform.domain.Game;
import com.mt.bettingPlatform.domain.User;
import com.mt.bettingPlatform.repository.BetRepository;
import com.mt.bettingPlatform.service.iService.BetService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    @Override
    public void calculateBetsForGame(Game game) {
        Bet.Type winn = getWinn(game);
        ArrayList<Bet> wonBets = new ArrayList<>();
        ArrayList<Bet> lostBets = new ArrayList<>();
        betRepository.findAllBetsByGame(game)
                .forEach(bet -> {
                    if (bet.getType().equals(winn)){
                        bet.setWin(true);
                        wonBets.add(bet);
                    } else {
                        bet.setWin(false);
                        lostBets.add(bet);
                    }
                });
        Integer totalWon = getTotalAmountFromBets(wonBets);
        Integer totalLost = getTotalAmountFromBets(lostBets);

        for (Bet bet: wonBets) {
            Integer won = getWinForBet(totalWon, totalLost, bet);
            bet.setAmountWin(won);
            bet.setTotal(bet.getAmount() + won);
        }

        betRepository.save(wonBets);
        betRepository.save(lostBets);
    }

    @Override
    public ArrayList<Bet> findAllWonBetsByGame(Game g) {
        return betRepository.findAllWonBetsByGame(g);
    }

    @Override
    public ArrayList<Bet> findAllLostBetsByGame(Game g) {
        return betRepository.findAllLostBetsByGame(g);
    }

    private Bet.Type getWinn(Game game) {
        if (game.getEndingScoreTeamA() > game.getEndingScoreTeamB()) {
            return Bet.Type.TeamA;
        } else if (game.getEndingScoreTeamA() < game.getEndingScoreTeamB()) {
            return Bet.Type.TeamB;
        }
        return Bet.Type.Equal;
    }

    private Integer getTotalAmountFromBets(ArrayList<Bet> bets) {
        Integer sum = 0;
        for (Bet bet: bets){
            sum += bet.getAmount();
        }
        return sum;
    }

    private Integer getWinForBet(Integer totalWon, Integer totalLost, Bet bet) {
        Double percentFromTotalWon = (bet.getAmount() * 100) / (double)totalWon;
        Double won = Math.ceil((percentFromTotalWon * totalLost) / 100.0);
        return won.intValue();

    }
}
