package service;

import dao.ClanDAO;
import model.Clan;
import model.Tracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static service.TrackerService.sendTracker;
import static util.EntityLocker.getClanLock;

public class ClanServiceImpl implements ClanService {
    private static final Logger logger = LoggerFactory.getLogger(ClanServiceImpl.class);
    private final ClanDAO clanDAO;

    public ClanServiceImpl() {
        this.clanDAO = new ClanDAO();
    }

    @Override
    public Clan get(Long id) {
        var lock = getClanLock(id);
        lock.lock();
        try {
            logger.info("Getting clan with id {}", id);
            return clanDAO.getClan(id);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean updateClanGold(Long id, int diff, Tracker tracker) {
        var lock = getClanLock(id);
        lock.lock();
        try {
            var clan = get(id);
            tracker.setBeforeUpdateGold(clan.getGold().get());
            tracker.setAfterUpdateGold(clan.getGold().addAndGet(diff));
            logger.info("Received request to update clan {} gold to {}", clan.getId(), clan.getGold().get());
            sendTracker(tracker);
            logger.info("Send tracker: " + tracker);
            return clanDAO.updateClanGold(clan);
        } finally {
            lock.unlock();
        }
    }
}
