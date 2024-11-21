package StreamingProg;

import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> userData;
    private String username;
    private boolean isAdmin;

    public UserManager(HashMap<String, User> userData, String username, boolean isAdmin) {
        this.userData = userData;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public HashMap<String, User> getUserData() {
        return userData;
    }

    public void setUserData(HashMap<String, User> userData) {
        this.userData = userData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
