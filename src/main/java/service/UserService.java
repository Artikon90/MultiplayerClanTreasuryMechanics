package service;

import dao.UserDAO;
import model.Player;

public class UserService {
    private final UserDAO userDAO;
    private final ClanService clanService;

    public UserService() {
        this.userDAO = new UserDAO();
        this.clanService = new ClanServiceImpl();
    }

    public Player getUser(long id) {
        return userDAO.getPlayer(id);
    }

    public void updateUserGold(Player player) {
        userDAO.updateUser(player);
    }

    /**
     * Метод обновления казны с возможностью как положить в неё деньги, так и достать
     * @return возвращает успешность операции (true если да, false если нет)
     */
    public boolean updateClanGoldUserWallet(long id, int goldDiff) {
        var user = getUser(id);
        var clan = clanService.get(user.getClanId());
        if (clan.getGold() + goldDiff > 0) {
            clan.setGold(clan.getGold() + goldDiff);
            user.setGold(user.getGold() + (goldDiff * -1));
            updateUserGold(user);
            clanService.update(clan);
            return true;
        } else {
            return false;
        }
    }
}
