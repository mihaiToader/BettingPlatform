package com.mt.bettingPlatform.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Bet
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    @ManyToOne(fetch = FetchType.EAGER,
        targetEntity = Game.class)
    @JoinColumn(name = "id_game")
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER,
        targetEntity = User.class)
    @JoinColumn(name = "id_user")
    private User user;

    private Integer amount;

    private Integer amountWin;

    private Integer total;

    private Type type;

    private boolean win = false;

    private boolean finished = false;

    private boolean paid = false;

    private boolean paidWinning = false;

    public enum Type
    {
        TeamA, TeamB, Equal
    }

    public Bet()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Calendar getCreated()
    {
        return created;
    }

    public void setCreated(Calendar created)
    {
        this.created = created;
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public boolean isWin()
    {
        return win;
    }

    public void setWin(boolean win)
    {
        this.win = win;
    }

    public boolean isFinished()
    {
        return finished;
    }

    public void setFinished(boolean finished)
    {
        this.finished = finished;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public static String getTeamNameFromType(Type type, Game game)
    {
        if (type == Type.TeamA)
        {
            return game.getTeamA();
        }
        if (type == Type.TeamB)
        {
            return game.getTeamB();
        }
        return "Equal";
    }

    public boolean isPaid()
    {
        return paid;
    }

    public void setPaid(boolean paid)
    {
        this.paid = paid;
    }

    public Integer getAmountWin()
    {
        return amountWin;
    }

    public void setAmountWin(Integer amountWin)
    {
        this.amountWin = amountWin;
    }

    public Integer getTotal()
    {
        return total;
    }

    public void setTotal(Integer total)
    {
        this.total = total;
    }

    public boolean isPaidWinning()
    {
        return paidWinning;
    }

    public void setPaidWinning(boolean paidWinning)
    {
        this.paidWinning = paidWinning;
    }

}
