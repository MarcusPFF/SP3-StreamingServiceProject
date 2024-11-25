package StreamingProg;

import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> userData;
    private TextUI ui;

    public UserManager() {
        this.userData = new HashMap<>();
        this.ui = new TextUI();
    }

    public void createUser(String username, String password, boolean isAdmin) {
        if (userData.containsKey(username)) {
            ui.displayMsg("Den her bruger findes allerede");
        } else {
            User user = new User(username, password, isAdmin);
            userData.put(username, user);
            ui.displayMsg("User created: " + username);
        }
    }

    public boolean validateUser(String username, String password) {
        User user = userData.get(username);
        if (user == null) {
            ui.displayMsg("Det lykkedes ikke at logge dig ind " + username);
            return false;
        }

        if (user.getPassword().equals(password)) {
            ui.displayMsg("Det lykkedes at logge dig ind " + username);
            return true;
        } else {
            ui.displayMsg("Det lykkedes ikke at logge dig ind " + username);
            return false;
        }
    }

    public HashMap<String, User> getUserData() {
        return userData;
    }

    public void deleteUser(String username) {
        if (userData.containsKey(username)) {
            userData.remove(username);
            ui.displayMsg("Bruger fjernet: " + username);
        } else {
            ui.displayMsg("Fejl: Bruger findes ikke");
        }
    }
}