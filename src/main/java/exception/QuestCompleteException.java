package exception;

public class QuestCompleteException extends RuntimeException {

    public QuestCompleteException(String message) {
        super(message);
    }

    public QuestCompleteException(String message, Throwable cause) {
        super(message, cause);
    }


}
