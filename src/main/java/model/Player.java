package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Player {
    private Long id;
    private volatile AtomicInteger gold;
    private String username;
    private Long clanId;

    public Player(long id, int gold, String username, long clanId) {
        this.id = id;
        this.gold = new AtomicInteger(gold);
        this.username = username;
        this.clanId = clanId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClanId() {
        return clanId;
    }

    public void setClanId(Long clanId) {
        this.clanId = clanId;
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

}
