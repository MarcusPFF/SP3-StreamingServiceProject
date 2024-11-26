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
        // Ensure userData is initialized
        if (userData == null || userData.isEmpty()) {
            ui.displayMsg("Ingen brugere tilgængelige for validering.");
            return false
        }

        // Check if the username exists
        for (User user : userData) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                // Check the password for the matching username
                if (user.getPassword().equals(password)) {
                    ui.displayMsg("Det lykkedes at logge dig ind " + username);
                    if (user.isAdmin()) {
                        // Her skal du bruge din displayMsg funktion
                        System.out.println("Bruger: " + username + " har admin rettigheder");
                    }
                    return true;
                } else {
                    ui.displayMsg("Forkert password.");
                    return false;
                }
            }
        }

        // If no matching username was found
        ui.displayMsg("Brugernavnet findes ikke.");
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