package dao;

import database.JdbcConnection;
import exception.RuntimeWrapperException;
import model.Player;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    public Player getPlayer(long id) {
        try {
            var statement = connection.prepareStatement("SELECT * FROM player WHERE player_id = ?");
            statement.setLong(1, id);
            var result = statement.executeQuery();
            result.next();
            return new Player(id, result.getInt("gold"), result.getString("username"), result.getLong("clan_id"));
        } catch (SQLException e) {
            throw new RuntimeWrapperException(e.getMessage());
        }
    }

    public boolean updateGold(long id, int goldDiff) {
        try {
            var statement = connection.prepareStatement(
                    "UPDATE player SET gold = gold + ? WHERE player_id = ?");
            statement.setInt(1, goldDiff);
            statement.setLong(2, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeWrapperException(e.getMessage());
        }
    }

    public void updateUser(Player player) {
        try {
            var statement = connection.prepareStatement(
                    "UPDATE player SET gold = ? WHERE player_id = ?");
            statement.setInt(1, player.getGold());
            statement.setLong(2, player.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeWrapperException(e.getMessage());
        }
    }
}
