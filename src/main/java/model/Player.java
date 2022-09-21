package model;

public class Player {
    private long id;
    private int gold;
    private String username;
    private long clanId;

    public Player(long id, int gold, String username, long clanId) {
        this.id = id;
        this.gold = gold;
        this.username = username;
        this.clanId = clanId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
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
