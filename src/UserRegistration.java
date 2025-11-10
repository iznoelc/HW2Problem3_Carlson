/**
 * The user class
 */
public class UserRegistration {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    /**
     * Constructor to initialize all properties of a user
     * @param username
     * @param password
     * @param email
     * @param phoneNumber
     */
    public UserRegistration(String username, String password, String email, String phoneNumber){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return User's username
     */
    public String getUsername(){ return this.username; }

    /**
     * @return User's password
     */
    public String getPassword(){ return this.password; }

    /**
     * @return User's email
     */
    public String getEmail(){ return this.email; }

    /**
     * @return User's phone number
     */
    public String getPhoneNumber() { return this.phoneNumber; }
}
