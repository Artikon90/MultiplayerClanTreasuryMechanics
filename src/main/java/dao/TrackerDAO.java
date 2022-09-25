package dao;

import database.JdbcConnection;
import exception.RuntimeWrapperException;
import model.Tracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class TrackerDAO {
    private static final Logger logger = LoggerFactory.getLogger(TrackerDAO.class);
    private final Connection connection;

    public TrackerDAO() {
        this.connection = JdbcConnection.getConnection();
    }

    public boolean saveTracker(Tracker tracker) {
        try {
            var statement = connection.prepareStatement(
                    "INSERT INTO tracker(reason_update, updated_clan_id, updater_user_id, completed_task_id," +
                            " before_update_gold, after_update_gold, difference, update_time) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tracker.getReason().get());
            statement.setLong(2, tracker.getUpdatedClanId());
            statement.setLong(3, tracker.getUpdaterUserId());
            statement.setLong(4, tracker.getCompletedTaskId());
            statement.setInt(5, tracker.getBeforeUpdateGold());
            statement.setInt(6, tracker.getAfterUpdateGold());
            statement.setInt(7, tracker.getDifference());
            statement.setTimestamp(8, new Timestamp(tracker.getUpdateTime().getTime()));
            var updatedRow = statement.executeUpdate();
            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tracker.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating tracker failed, no ID obtained");
                }
            }
            return updatedRow > 0;
        } catch (SQLException e) {
            logger.atError().setCause(e).log("Failed to save tracker {}", tracker);
            throw new RuntimeWrapperException(e.getMessage());
        }
    }

}
