import controller.GameplayController;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        var pool = Executors.newFixedThreadPool(2000);
        for (int i = 0; i < 1500; i++) {
            pool.submit(new GameplayController());
        }
        pool.shutdown();
    }
}
