package dao;

import database.JdbcConnection;
import exception.RuntimeWrapperException;
import model.Clan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClanDAO {
    private static final Logger logger = LoggerFactory.getLogger(ClanDAO.class);
    private final Connection connection;

    public ClanDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    public Clan getClan(long id) {
        var clan = new Clan();
        try {
            var statement = connection.prepareStatement("SELECT * FROM clan WHERE clan_id = ?");
            statement.setLong(1, id);
            var result = statement.executeQuery();
            result.next();
            clan.setId(result.getLong("clan_id"));
            clan.setName(result.getString("name"));
            clan.setGold(result.getInt("gold"));
        } catch (SQLException e) {
            logger.atError().log("Error while getting clan with id: {}", id);
            throw new RuntimeWrapperException(e.getMessage());
        }
        return clan;
    }

    public void saveClan(Clan clan) {
        try {
            var statement = connection.prepareStatement(
                    "INSERT INTO clan (name, gold) VALUES (?, ?)");
            statement.setString(1, clan.getName());
            statement.setInt(2, clan.getGold());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateClanGold(Clan clan) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(
                    "UPDATE clan SET gold = ? WHERE clan_id = ?");
            statement.setInt(1, clan.getGold());
            statement.setLong(2, clan.getId());
            return statement.execute();
        } catch (SQLException e) {
            throw new RuntimeWrapperException(e.getMessage());
        }
    }
}
