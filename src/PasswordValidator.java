/**
 * Validates a user's password input.
 */
public class PasswordValidator implements Validator {
    private Validator nextValidator;

    /**
     * Set the password's next validator
     * @param validator next validator
     */
    @Override
    public void setNextValidator(Validator validator) {
        this.nextValidator = validator;
    }

    /**
     * Validate the user's password by ensuring it is at least 8 digits long, includes an uppercase letter,
     * includes a lowercase letter, and includes a digit.
     * @param user, the user to validate the password of
     * @throws ValidationException exception to throw if password is invalid
     */
    @Override
    public void validate(UserRegistration user) throws ValidationException {
        String p = user.getPassword();
        if (p.length() >= 8 && user.getPassword() != null){
            if (checkForUpperAndLower(p)){
                if (checkForDigit(p)){
                    System.out.println("Valid password!");
                    if (nextValidator != null){
                        nextValidator.validate(user);
                    }
                } else {
                    throw new ValidationException("#INVALID PASSWORD: MISSING A DIGIT#");
                }
            } else {
                throw new ValidationException("#INVALID PASSWORD: MISSING UPPER OR LOWERCASE LETTER#");
            }
        } else {
            throw new ValidationException("#INVALID PASSWORD: NOT AT LEAST 8 CHARACTERS#");
        }
    }

    /**
     * Helper function to go over every character in the string and ensure it has a lower and uppercase letter.
     * @param password, password to check
     * @return true if password has an upper & lowercase letter, false otherwise
     */
    public boolean checkForUpperAndLower(String password){
        boolean hasCapital = false;
        boolean hasLower = false;

        // loop through the password. when/if an uppercase character is found, set hasCapital to true.
        // when/if a lowercase letter is found, set hasLower to true;
        for (char c : password.toCharArray()){
            if (Character.isLowerCase(c)){
                hasLower = true;
            } else if (Character.isUpperCase(c)){
                hasCapital = true;
            }
        }

        return hasLower && hasCapital;
    }

    /**
     * Helper function to go over each character in the password and ensure it contains a digit.
     * @param password, password to be checked for a digit
     * @return true if password contains a digit, false otherwise
     */
    public boolean checkForDigit(String password){
        for (char c : password.toCharArray()){
            if (Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }
}
