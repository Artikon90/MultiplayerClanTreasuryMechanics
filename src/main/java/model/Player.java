package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Player {
    private long id;
    private volatile AtomicInteger gold;
    private String username;
    private long clanId;

    public Player(long id, int gold, String username, long clanId) {
        this.id = id;
        this.gold = new AtomicInteger(gold);
        this.username = username;
        this.clanId = clanId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AtomicInteger getGold() {
        return gold;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getClanId() {
        return clanId;
    }

    public void setClanId(long clanId) {
        this.clanId = clanId;
    }
}
