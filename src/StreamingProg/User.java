package StreamingProg;

public class User {
    private String username;
    private String password;
    private boolean isAdmin;

    //ik rør det virker
    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return "Bruger{" +
                "Brugernavn='" + username + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
