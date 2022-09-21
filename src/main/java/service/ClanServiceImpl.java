package service;

import dao.ClanDAO;
import model.Clan;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClanServiceImpl implements ClanService {

    private final ClanDAO clanDAO;

    public ClanServiceImpl() {
        this.clanDAO = new ClanDAO();
    }

    @Override
    public Clan get(long id) {
        return clanDAO.getClan(id);
    }

    @Override
    public boolean update(Clan clan) {
        Lock lock = new ReentrantLock(true);
        lock.lock();
        try {
            return clanDAO.updateClanGold(clan);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void changeClanGold(int goldDifference, long clanId, long userId) {
        Lock lock = new ReentrantLock(true);
        lock.lock();
        try {
            var clan = get(clanId);
            clan.setGold(clan.getGold() + goldDifference);
            update(clan);
        } finally {
            lock.unlock();
        }
    }
}
