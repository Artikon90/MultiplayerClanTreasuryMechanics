import controller.GameplayController;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new GameplayController()).start();
        }
    }
}
