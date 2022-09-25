package service;

import model.Clan;
import model.Tracker;

public interface ClanService {
    Clan get(long id);
    boolean updateClanGold(long clanId, int diff, Tracker tracker);
}
