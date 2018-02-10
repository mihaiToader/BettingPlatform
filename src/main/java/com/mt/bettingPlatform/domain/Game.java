package com.mt.bettingPlatform.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String teamA;

    @NotNull
    private String teamB;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    private Integer endingScoreTeamA;

    private Integer endingScoreTeamB;

    private boolean finished = false;

    public Game() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
