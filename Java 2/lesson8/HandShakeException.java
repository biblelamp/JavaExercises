/**
 * Java. Level 2. Lesson 8. Homework
 * Simple network chat
 *
 * @author Sergey Iryupin
 * @version 02 Aug 2016
 */
public class HandShakeException extends RuntimeException {
    public HandShakeException(String message) {
        super(message);
    }

    public HandShakeException(String message, Throwable cause) {
        super(message, cause);
    }
}