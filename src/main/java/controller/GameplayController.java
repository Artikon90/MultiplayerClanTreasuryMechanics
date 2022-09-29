package controller;

import service.TaskService;
import service.UserService;

import static util.TestHelper.getRandomUserId;

public class GameplayController implements Runnable {
    private static final TaskService taskService;
    private static final UserService userService;

    static {
        taskService = new TaskService();
        userService = new UserService();
    }

    public GameplayController() {
    }

    @Override
    public void run() {
        fromUserWallet();
        completeTask();
    }

    private void completeTask() {
        var task = taskService.createCompletedTask(getRandomUserId());
        var user = userService.getUser(task.getUserId());
        taskService.completeTask(user.getClanId(), task.getId());
    }

    private void fromUserWallet() {
        userService.updateClanGoldUserWallet(getRandomUserId(), 55);
    }
}
