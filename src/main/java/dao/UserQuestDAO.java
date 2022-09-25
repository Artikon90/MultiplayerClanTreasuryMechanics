package dao;

import database.JdbcConnection;
import exception.RuntimeWrapperException;
import model.UserQuest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserQuestDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserQuestDAO.class);
    private static final Connection connection = JdbcConnection.getConnection();

    public UserQuest getQuest(long id) {
        try {
            var statement = connection.prepareStatement("SELECT * FROM quest WHERE quest_id = ?");
            statement.setLong(1, id);
            var result = statement.executeQuery();
            result.next();
            return new UserQuest(result.getLong("quest_id"),
                    result.getBoolean("is_complete"),
                    result.getInt("gold_reward"),
                    result.getLong("user_id"));
        } catch (SQLException e) {
            logger.atError().setCause(e).log("Failed getting quest with id {}", id);
            throw new RuntimeWrapperException(e.getMessage(), e);
        }
    }

    /**
     * Создание выполненного квеста для тестирования метода пополнения казны за счет выполнения квеста
     * @return возвращает экземпляр созданного квеста
     */
    public UserQuest createCompletedQuest(long userId) {
        try {
            var statement = connection.prepareStatement(
                    "INSERT INTO quest(is_complete, gold_reward, user_id) VALUES (true, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            int reward = 50;
            statement.setInt(1, reward);
            statement.setLong(2, userId);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating quest for user " + userId + " failed, no rows affected.");
            }
            var result = new UserQuest();
            result.setComplete(true);
            result.setGoldReward(reward);
            result.setUserId(userId);
            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                   result.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating quest failed, no ID obtained.");
                }
            }
            return result;
        } catch (SQLException e) {
            logger.atError().setCause(e).log("Failed to create quest for user {}", userId);
            throw new RuntimeWrapperException(e.getMessage(), e);
        }
    }
}
