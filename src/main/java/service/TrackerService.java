package service;

import dao.TrackerDAO;
import model.Tracker;

public class TrackerService {

    private static TrackerDAO dao = new TrackerDAO();

    public static void sendTracker(Tracker tracker) {
        dao.saveTracker(tracker);
    }
}
