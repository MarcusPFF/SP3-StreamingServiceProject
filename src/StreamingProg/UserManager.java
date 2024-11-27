package StreamingProg;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final TextUI ui;
    private final List<User> userData;

    public UserManager() {
        this.ui = new TextUI();
        this.userData = new ArrayList<>();
    }

    public void setUserData(List<User> users) {
        userData.clear();
        userData.addAll(users);
    }

    public List<User> getUserData() {
        return userData;
    }

    public void createUser(User user) {
        userData.add(user);
        ui.displayMsg("Bruger oprettet: " + user.getUsername());
    }

    public boolean validateUser(String username, String password) {
        for (User user : userData) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPassword().equals(password)) {
                    ui.displayMsg("Login succesfuldt: " + username);
                    return true;
                } else {
                    ui.displayMsg("Forkert password.");
                    return false;
                }
            }
        }
        ui.displayMsg("Brugernavnet findes ikke.");
        return false;
    }

    public boolean deleteUser(String username) {
        return userData.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
    }
}
