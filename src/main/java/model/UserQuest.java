package model;

public class UserQuest {
    private long id;
    private boolean isComplete;
    private int goldReward;
    private long userId;

    public UserQuest() {
    }

    public UserQuest(long id, boolean isComplete, int goldReward, long userId) {
        this.id = id;
        this.isComplete = isComplete;
        this.goldReward = goldReward;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public void setGoldReward(int goldReward) {
        this.goldReward = goldReward;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
