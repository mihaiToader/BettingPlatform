package com.mt.bettingPlatform.domain.dto;

import javax.validation.constraints.NotNull;

public class GameDto {

    @NotNull
    private String teamA;

    @NotNull
    private String teamB;

    private int endingScoreTeamA = 0;

    private int endingScoreTeamB = 0;

    @NotNull
    private boolean finished;

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public int getEndingScoreTeamA() {
        return endingScoreTeamA;
    }

    public void setEndingScoreTeamA(int endingScoreTeamA) {
        this.endingScoreTeamA = endingScoreTeamA;
    }

    public int getEndingScoreTeamB() {
        return endingScoreTeamB;
    }

    public void setEndingScoreTeamB(int endingScoreTeamB) {
        this.endingScoreTeamB = endingScoreTeamB;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
