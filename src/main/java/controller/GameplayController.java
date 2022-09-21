package controller;

import service.TaskService;
import service.UserService;
import util.TestHelper;

public class GameplayController {
    private final TaskService taskService;
    private final UserService userService;

    public GameplayController() {
        this.taskService = new TaskService();
        this.userService = new UserService();
    }

    void gameplayImitator() {
        var rnd = (int)(Math.random() * 1);
        var user = userService.getUser(TestHelper.getRandomUser());
        if (rnd == 0) {
            var task = taskService.createCompleteTask(user.getId());
            taskService.completeTask(user.getClanId(), task.getId(), user.getId());
        } else if (rnd == 1) {
            var minusRnd = (int)(Math.random() * 1);
            var rndNumberGold = (int)(Math.random() * 50);
            var goldDiff = minusRnd == 0 ? rndNumberGold * -1 : rndNumberGold;
            userService.updateClanGoldUserWallet(user.getId(), goldDiff);
        }
    }
}
