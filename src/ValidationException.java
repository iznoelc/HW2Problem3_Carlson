/**
 * Validation exception to throw when a user's input for a certain field is not valid.
 */
public class ValidationException extends Exception {
    public ValidationException(String message){
        super(message);
    }
}
