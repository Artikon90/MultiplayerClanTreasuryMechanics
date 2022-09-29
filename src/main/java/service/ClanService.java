package service;

import model.Clan;
import model.Tracker;

public interface ClanService {
    Clan get(Long id);
    boolean updateClanGold(Long clanId, int diff, Tracker tracker);
}
