package com.mt.bettingPlatform.domain;

import org.hibernate.annotations.CreationTimestamp;

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

    private int endingScoreTeamA = 0;

    private int endingScoreTeamB = 0;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Game) {
            if (this.id == ((Game) obj).getId()) {
                return true;
            }
        }
        return false;
    }
}
