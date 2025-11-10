/**
 * Validates a user's email address input.
 */
public class EmailValidator implements Validator {
    // source for email regex: https://stackoverflow.com/questions/8204680/java-regex-email
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?``{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private Validator nextValidator;

    /**
     * Set the next validator of the email validator.
     * @param validator next validator
     */
    @Override
    public void setNextValidator(Validator validator) {
        this.nextValidator = validator;
    }

    /**
     * Validate the user's email address by ensuring it is not null & follows the email regex.
     * @param user, the user we want to validate the email address of
     * @throws ValidationException, the exception to throw if the user's email address is invalid
     */
    @Override
    public void validate(UserRegistration user) throws ValidationException {
        if (user.getEmail() != null && user.getEmail().matches(EMAIL_REGEX)){
            System.out.println("Valid email address!");
            if (nextValidator != null){
                nextValidator.validate(user);
            }
        } else {
            throw new ValidationException("#INVALID EMAIL#");
        }
    }
}
