// sources
// https://www.tutorialspoint.com/how-to-center-a-jlabel-in-a-jpanel-with-gridbaglayout-in-java

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationUI extends JFrame {

    public UserRegistrationUI(){
        // set the basis of the window
        setTitle("USER REGISTRATION SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); // center window on the screen

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // TITLE
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel title = new JLabel("User Registration");
        Font bigFont = new Font("Dialog", Font.BOLD, 20);
        title.setFont(bigFont);
        add(title);

        // USERNAME
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);
        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        // PASSWORD - use password field to hide what user is typing
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // EMAIL
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Email:"), gbc);
        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        // PHONE NUMBER
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Phone number:"), gbc);
        JTextField phoneNumberField = new JTextField(20);
        gbc.gridx = 1;
        add(phoneNumberField, gbc);

        // REGISTER BUTTON
        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton registerButton = new JButton("★ REGISTER ★");
        gbc.gridx = 1;
        add(registerButton, gbc);
        registerButton.addActionListener(new ActionListener() {
            // if the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                // call the registerUser method
                registerUser(usernameField, passwordField,emailField,phoneNumberField);

            }
        });
    }

    public void registerUser(JTextField username, JTextField password, JTextField email, JTextField phoneNumber){
        // create a new JFrame to hold where validation output will be presented
            JFrame valid = new JFrame("CONFIRMATION");
        valid.setSize(400, 300);
        valid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        valid.setLocationRelativeTo(null);

        // create user based on credentials entered in text fields
        // create a new user and get their credentials based on the fields they typed in
        UserRegistration user = new UserRegistration(
                username.getText(),
                password.getText(),
                email.getText(),
                phoneNumber.getText()
        );

        // set up the validation chain
        Validator validator = setUpChain();

        try{
            // start validating the credentials
            validator.validate(user);

            // if everything is fine
            JLabel passed = new JLabel("REGISTRATION SUCCESSFUL ^_^");
            Font bigFont = new Font("Dialog", Font.BOLD, 20);
            passed.setHorizontalAlignment(SwingConstants.CENTER);
            passed.setFont(bigFont);
            valid.add(passed);
            valid.setVisible(true);

        } catch (ValidationException e) {
            // one or more of the credentials were incorrect so we instead show an error message, depending on which
            // validation failed
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Validator setUpChain(){
        Validator usernameValidator = new UsernameValidator();
        Validator passwordValidator = new PasswordValidator();
        Validator emailValidator = new EmailValidator();
        Validator phoneNumberValidator = new PhoneNumberValidator();

        usernameValidator.setNextValidator(passwordValidator);
        passwordValidator.setNextValidator(emailValidator);
        emailValidator.setNextValidator(phoneNumberValidator);
        phoneNumberValidator.setNextValidator(null);

        return usernameValidator;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            // set look and feel to match operating system's native style
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            UserRegistrationUI demo = new UserRegistrationUI();
            demo.setVisible(true);
        });
    }
}
