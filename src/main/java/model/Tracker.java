package model;

import java.util.Date;

public class Tracker {
    private long id;
    private ReasonUpdate reason;
    private long updatedClanId;
    private long updaterUserId;
    private long completedTaskId;
    private Date updateTime;
    private int beforeUpdateGold;
    private int afterUpdateGold;
    private int difference;

    public Tracker(ReasonUpdate reason, long updatedClanId, long updaterUserId, int difference) {
        this.completedTaskId = 0;
        this.reason = reason;
        this.updatedClanId = updatedClanId;
        this.updaterUserId = updaterUserId;
        this.updateTime = new Date();
        this.difference = difference;
    }
    public Tracker(ReasonUpdate reason, long updatedClanId, long updaterUserId, int difference, long completedTaskId) {
        this.completedTaskId = completedTaskId;
        this.reason = reason;
        this.updatedClanId = updatedClanId;
        this.updaterUserId = updaterUserId;
        this.updateTime = new Date();
        this.difference = difference;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setReason(ReasonUpdate reason) {
        this.reason = reason;
    }

    public long getCompletedTaskId() {
        return completedTaskId;
    }

    public void setCompletedTaskId(long completedTaskId) {
        this.completedTaskId = completedTaskId;
    }

    public long getUpdatedClanId() {
        return updatedClanId;
    }

    public void setUpdatedClanId(long updatedClanId) {
        this.updatedClanId = updatedClanId;
    }

    public long getUpdaterUserId() {
        return updaterUserId;
    }

    public void setUpdaterUserId(long updaterUserId) {
        this.updaterUserId = updaterUserId;
    }

    public ReasonUpdate getReason() {
        return reason;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getBeforeUpdateGold() {
        return beforeUpdateGold;
    }

    public void setBeforeUpdateGold(int beforeUpdateGold) {
        this.beforeUpdateGold = beforeUpdateGold;
    }

    public int getAfterUpdateGold() {
        return afterUpdateGold;
    }

    public void setAfterUpdateGold(int afterUpdateGold) {
        this.afterUpdateGold = afterUpdateGold;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    @Override
    public String toString() {
        return "Tracker{" +
                "id=" + id +
                ", reason=" + reason.get() +
                ", updatedClanId=" + updatedClanId +
                ", updaterUserId=" + updaterUserId +
                ", completedTaskId=" + completedTaskId +
                ", updateTime=" + updateTime +
                ", beforeUpdateGold=" + beforeUpdateGold +
                ", afterUpdateGold=" + afterUpdateGold +
                ", difference=" + difference +
                '}';
    }
}
