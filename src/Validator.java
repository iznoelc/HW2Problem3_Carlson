/**
 * Interface all validators should follow.
 */
public interface Validator {
    /**
     * Sets the next validator of a validator.
     * @param validator
     */
    void setNextValidator(Validator validator);

    /**
     * All classes implementing Validator should define a method that determines how they will validate
     * a user's specific input.
     * @param user to be validated
     * @throws ValidationException exception thrown when input is not valid
     */
    void validate(UserRegistration user) throws ValidationException;
}
