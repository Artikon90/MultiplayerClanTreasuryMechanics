package service;

import dao.UserDAO;
import model.Player;
import model.ReasonUpdate;
import model.Tracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserDAO userDAO;
    private final ClanService clanService;

    public UserService() {
        this.userDAO = new UserDAO();
        this.clanService = new ClanServiceImpl();
    }

    public Player getUser(long id) {
        synchronized ((Long) id) {
            return userDAO.getPlayer(id);
        }
    }

    public boolean updateUserGold(long id, int diff) {
        synchronized ((Long) id) {
            logger.info("Received request to update USER {}, with gold difference {}", id, diff);
            return userDAO.updateGold(id, diff);
        }
    }

    /**
     * Метод обновления казны с возможностью как положить в неё деньги, так и достать
     * @return возвращает успешность операции (true если да, false если нет)
     */
    public boolean updateClanGoldUserWallet(long id, int goldDiff) {
        synchronized ((Long) id) {
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
        }
    }
}
