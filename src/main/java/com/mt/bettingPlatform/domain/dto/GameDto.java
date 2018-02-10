package com.mt.bettingPlatform.domain.dto;

import javax.validation.constraints.NotNull;

public class GameDto {

    @NotNull
    private String teamA;

    @NotNull
    private String teamB;

    private Integer endingScoreTeamA;

    private Integer endingScoreTeamB;

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

    public Integer getEndingScoreTeamA() {
        return endingScoreTeamA;
    }

    public void setEndingScoreTeamA(Integer endingScoreTeamA) {
        this.endingScoreTeamA = endingScoreTeamA;
    }

    public Integer getEndingScoreTeamB() {
        return endingScoreTeamB;
    }

    public void setEndingScoreTeamB(Integer endingScoreTeamB) {
        this.endingScoreTeamB = endingScoreTeamB;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
