package service;

import model.Clan;

public interface ClanService {
    Clan get(long id);
    boolean update(Clan clan);
    void changeClanGold(int goldDifference, long clanId, long userId);
}
