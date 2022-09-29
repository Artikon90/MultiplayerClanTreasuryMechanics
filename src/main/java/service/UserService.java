package service;

import dao.UserDAO;
import model.Player;
import model.ReasonUpdate;
import model.Tracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static util.EntityLocker.getUserLock;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserDAO userDAO;
    private final ClanService clanService;

    public UserService() {
        this.userDAO = new UserDAO();
        this.clanService = new ClanServiceImpl();
    }

    public Player getUser(Long id) {
        var lock = getUserLock(id);
        lock.lock();
        try {
            return userDAO.getPlayer(id);
        } finally {
            lock.unlock();
        }
    }

    public boolean updateUserGold(Long id, int diff) {
        var lock = getUserLock(id);
        lock.lock();
        try {
            logger.info("Received request to update USER {}, with gold difference {}", id, diff);
            return userDAO.updateGold(id, diff);
        } finally {
            lock.unlock();
        }

    }

    /**
     * Метод обновления казны с возможностью как положить в неё деньги, так и достать
     * @return возвращает успешность операции (true если да, false если нет)
     */
    public boolean updateClanGoldUserWallet(Long id, int goldDiff) {
        var lock = getUserLock(id);
        lock.lock();
        try {
            var user = getUser(id);
            var clan = clanService.get(user.getClanId());
            var tracker = new Tracker(ReasonUpdate.FROM_WALLET, clan.getId(), id, goldDiff);
            tracker.setBeforeUpdateGold(clan.getGold().get());
            boolean resultUserUpdate = false;
            boolean resultClanUpdate = false;
            if (clan.getGold().get() + goldDiff > 0) {
                tracker.setAfterUpdateGold(user.getGold().addAndGet(goldDiff));
                resultUserUpdate = updateUserGold(id, goldDiff * -1);
                resultClanUpdate = clanService.updateClanGold(user.getClanId(), goldDiff, tracker);
            }
            return resultClanUpdate && resultUserUpdate;
        } finally {
            lock.unlock();
        }
    }
}
