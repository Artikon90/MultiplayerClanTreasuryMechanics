package exception;

public class RuntimeWrapperException extends RuntimeException {
    public RuntimeWrapperException(String message) {
        super(message);
    }

    public RuntimeWrapperException(String message, Throwable cause) {
        super(message, cause);
    }
}
