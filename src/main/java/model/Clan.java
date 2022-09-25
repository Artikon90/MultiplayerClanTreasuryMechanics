package model;

import java.util.concurrent.atomic.AtomicInteger;

public class Clan {
    private Long id;
    private String name;
    private volatile AtomicInteger gold;

    public Clan(int gold) {
        this.gold = new AtomicInteger(gold);
    }

    public Clan() {
        this.gold = new AtomicInteger();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AtomicInteger getGold() {
        return gold;
    }
}
