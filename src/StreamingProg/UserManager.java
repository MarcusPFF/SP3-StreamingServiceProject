package StreamingProg;

import java.util.List;
import java.util.ArrayList;

public class UserManager {
    private TextUI ui;
    FileIO io;
    public List<User> userData = new ArrayList<>();

    public UserManager() {
        this.ui = new TextUI();
    }

    // Opretter en bruger og tilføjer den til listen
    public void createUser(User user) {
        userData.add(user);
        ui.displayMsg("User created: " + user.getUsername());
    }

    // Validerer brugerens login
    public boolean validateUser(String username, String password) {
        for (User user : userData) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                ui.displayMsg("Det lykkedes at logge dig ind " + username);
                return true;
            } else {
                ui.displayMsg("Forkert password");
                return false;
            }
        }
        return false;
    }


    // Sletter en bruger
    public List<User> deleteUser(String username) {
        for (User user : userData) {
            userData.remove(user);
            ui.displayMsg("User removed: " + user.getUsername());
        }
        return userData;
    }
}