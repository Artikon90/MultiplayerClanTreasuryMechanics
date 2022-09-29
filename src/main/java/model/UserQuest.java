package model;

public class UserQuest {
    private long id;
    private boolean isComplete;
    private int goldReward;
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
