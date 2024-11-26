package StreamingProg;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private TextUI ui;
    private List<User> userData = new ArrayList<>();

    public UserManager() {
        this.ui = new TextUI();
    }

    public void createUser (User user) {
        userData.add(user);
        ui.displayMsg("User  created: " + user.getUsername());
    }

    public boolean validateUser (String username, String password) {
        for (User  user : userData) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPassword().equals(password)) {
                    ui.displayMsg("Det lykkedes at logge dig ind " + username);
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

    public void deleteUser (String username) {
        User userToRemove = null;
        for (User  user : userData) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                userToRemove = user;
                break;
            }
        }
        if (userToRemove != null) {
            userData.remove(userToRemove);
            ui.displayMsg("User  removed: " + username);
        } else {
            ui.displayMsg("User  not found: " + username);
        }
    }

    public List<User> getUser Data() {
        return userData;
    }

    public void setUser Data(List<User> userData) {
        this.userData = userData;
    }
}