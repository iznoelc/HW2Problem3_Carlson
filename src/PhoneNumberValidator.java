/**
 * Validates a user's phone number input.
 */
public class PhoneNumberValidator implements Validator {
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
     * Validate the user's phone number by ensuring it is not null, at least 10 digits long, and only
     * contains digits.
     * @param user to validate the phone number of
     * @throws ValidationException exception thrown if phone number is invalid
     */
    @Override
    public void validate(UserRegistration user) throws ValidationException {
        if (user.getPhoneNumber().length() == 10 && user.getPhoneNumber() != null){
            if (checkForDigit(user.getPhoneNumber())) {
                System.out.println("Valid phone number!");
                if (nextValidator != null) {
                    nextValidator.validate(user);
                }
            } else {
                throw new ValidationException("#INVALID PHONE NUMBER: CONTAINS INVALID CHARACTER#");
            }
        } else {
            throw new ValidationException("#INVALID PHONE NUMBER: INPUT TOO SHORT OR TOO LONG#");
        }
    }

    /**
     * Helper function to ensure all characters of phone number are digits.
     * @param phoneNumber to be checked
     * @return true if phone number consists of all digits, false otherwise
     */
    public boolean checkForDigit(String phoneNumber){
        // check all characters in the phone number
        // if you ever come across one that is not a digit, return false
        // because that will be an invalid phone number. otherwise
        // if you loop through the entire phone number and there are only digits, return true;
        for (char c : phoneNumber.toCharArray()){
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
