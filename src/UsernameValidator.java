/**
 * Validates a user's username input.
 */
public class UsernameValidator implements Validator {
    private Validator nextValidator;

    /**
     * Set the phone number's next validator
     * @param validator next validator
     */
    @Override
    public void setNextValidator(Validator validator) {
        this.nextValidator = validator;
    }

    /**
     * Validate a user's username input by ensuring it is not null and at least 5 characters.
     * @param user to validate the username of
     * @throws ValidationException thrown if username is invalid
     */
    @Override
    public void validate(UserRegistration user) throws ValidationException {
        if (user.getUsername() != null && user.getUsername().length() >= 5){
            System.out.println("Valid username!");
            if (nextValidator != null){
                nextValidator.validate(user);
            }
        } else {
            throw new ValidationException("#INVALID USERNAME#");
        }
    }
}
