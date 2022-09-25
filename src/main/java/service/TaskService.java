package service;

import dao.UserQuestDAO;
import exception.QuestCompleteException;
import model.ReasonUpdate;
import model.Tracker;
import model.UserQuest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    private final ClanService clanService;
    private final UserQuestDAO userQuestDAO;

    public TaskService() {
        this.clanService = new ClanServiceImpl();
        this.userQuestDAO = new UserQuestDAO();
    }

    public void completeTask(long clanId, long taskId) {
        var task = getQuest(taskId);
        if (task.isComplete()) {
            var tracker = new Tracker(ReasonUpdate.COMPETE_QUEST, clanId, task.getUserId(), task.getGoldReward(), taskId);
            boolean res = clanService.updateClanGold(clanId, task.getGoldReward(), tracker);
            if (!res) {
                logger.atDebug().log("Unsuccessful update clan gold. Tracker id: {}", tracker.getId());
                throw new QuestCompleteException("Unexpected result of update clan gold");
            }
        }
    }

    public UserQuest getQuest(long id) {
        return userQuestDAO.getQuest(id);
    }

    public synchronized UserQuest createCompletedTask(long userId) {
        return userQuestDAO.createCompletedQuest(userId);
    }
}
