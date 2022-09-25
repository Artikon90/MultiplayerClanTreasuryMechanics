import controller.GameplayController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new GameplayController()).start();
        }
    }
}
