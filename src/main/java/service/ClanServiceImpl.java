package service;

import dao.ClanDAO;
import model.Clan;
import model.Tracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static service.TrackerService.sendTracker;

public class ClanServiceImpl implements ClanService {
    private static final Logger logger = LoggerFactory.getLogger(ClanServiceImpl.class);
    private final ClanDAO clanDAO;

    public ClanServiceImpl() {
        this.clanDAO = new ClanDAO();
    }

    @Override
    public Clan get(long id) {
        synchronized ((Long) id) {
            logger.info("Getting clan with id {}", id);
            return clanDAO.getClan(id);
        }
    }

    @Override
    public boolean updateClanGold(long id, int diff, Tracker tracker) {
        synchronized ((Long) id) {
                var clan = get(id);
                tracker.setBeforeUpdateGold(clan.getGold().get());
                tracker.setAfterUpdateGold(clan.getGold().addAndGet(diff));
                logger.info("Received request to update clan {} gold to {}", clan.getId(), clan.getGold().get());
                sendTracker(tracker);
                logger.info("Send tracker: " + tracker);
                return clanDAO.updateClanGold(clan);
        }
    }
}
