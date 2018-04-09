import java.lang.Exception;
public class incorrectArgumentsException extends Exception {
    public incorrectArgumentsException() { super(); }
    public incorrectArgumentsException(String message) { super(message); }
    public incorrectArgumentsException(String message, Throwable cause) { super(message, cause); }
    public incorrectArgumentsException(Throwable cause) { super(cause); }
}