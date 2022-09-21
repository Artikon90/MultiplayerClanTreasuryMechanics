package service;

import dao.UserQuestDAO;
import model.UserQuest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskService {

    private final ClanService clanService;
    private final UserQuestDAO userQuestDAO;

    public TaskService() {
        this.clanService = new ClanServiceImpl();
        this.userQuestDAO = new UserQuestDAO();
    }

    public void completeTask(long clanId, long taskId, long userId) {
        var task = getQuest(taskId);
        if (task.isComplete()) {
            clanService.changeClanGold(task.getGoldReward(), clanId, userId);
        }
    }

    public UserQuest getQuest(long id) {
        return userQuestDAO.getQuest(id);
    }

    public UserQuest createCompleteTask(long userId) {
        Lock lock = new ReentrantLock(true);
        lock.lock();
        try {
            return userQuestDAO.createCompletedQuest(userId);
        } finally {
            lock.unlock();
        }
    }
}
