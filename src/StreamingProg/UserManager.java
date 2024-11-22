package StreamingProg;

import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> userData;

    public UserManager() {
        this.userData = new HashMap<>();
    }

    public void createUser(String username, String password, boolean isAdmin) {
    }

    public boolean validateUser(String username, String password) {
    return false;
    }

    public HashMap<String, User> getUserData() {
        return userData;
    }

    public void deleteUser(User user) {

    }
}
