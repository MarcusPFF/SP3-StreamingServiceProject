package StreamingProg;

public class User {
    // Declaring the variables
    private String username;
    private String password;
    public boolean validate;


    // Constructor for user
    public User(String username, String password, boolean validate) {
        this.username = username;
        this.password = password;
        this.validate = validate;
    }

    public User() {  // Constructor for user
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public User(String username) { // Constructor for user
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValidate() {
        return validate;
    }

    // To string
    @Override
    public String toString() {
        return "Brugere{" +
                "Brugernavn='" + username + '\'' +
                ", validate=" + validate +
                '}';
    }
}
