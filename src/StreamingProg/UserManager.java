package StreamingProg;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    //Initialising private and public attributes
    private final TextUI ui;
    private final List<User> userData;
    private User user;
    private final String favoritesDataPath = "data/favorites/";
    private final String watchedMediaDataPath = "data/watchedMedia/";
    public String userFavoritesDataPath;
    public String userWatchedMediaDataPath;

    public UserManager() {
        this.ui = new TextUI();
        this.userData = new ArrayList<>();
        this.user = new User();
    }

    // Getter og setter
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUserData(List<User> users) {
        userData.clear();
        userData.addAll(users);
    }

    public List<User> getUserData() {
        return userData;
    }

    // Adds the new user to the userData list and sets them as the active user.
// Displays a message confirming the user creation and initializes their data files (favorites and watched media).

    public void createUser(User newUser) {
        userData.add(newUser);
        this.user = newUser;
        ui.displayMsg("Bruger oprettet: " + newUser.getUsername());
        createFavoritesFile(newUser);
        createWatchedMediaFile(newUser);
    }

    // Loops through the list of users to find the matching username.
// If the username exists, it checks the password and sets the user as active if valid.
// If either the username or password is incorrect, it displays an appropriate message and returns false.

    public boolean validateUser(String username, String password) {
        for (User u : userData) {
            if (u.getUsername().equals(username)) {
                if (u.getPassword().equals(password)) {
                    this.user = u;
                    ui.displayMsg("Login succesfuldt: " + username);
                    getUserWatchedMediaDataPath();
                    getUserFavoritesDataPath();
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

    // Attempts to remove the user with the specified username from the userData list.
// Displays a success or failure message based on whether the user was found and deleted.

    public boolean deleteUser(String username) {
        if (userData.removeIf(u -> u.getUsername().equalsIgnoreCase(username))) {
            ui.displayMsg("Bruger slettet: " + username);
            return true;
        } else {
            ui.displayMsg("Bruger ikke fundet: " + username);
            return false;
        }
    }

// Creates a new favorites file for the specified user and returns the file path.
// If the file already exists, it notifies the user, otherwise it creates a new file.

    private String createFavoritesFile(User user) {
        try {
            String filePath = favoritesDataPath + user.getUsername() + "_favoriteMedia.txt";
            File file = new File(filePath);
            if (file.createNewFile()) {
                ui.displayMsg("Favorit-fil oprettet: " + file.getAbsolutePath());
            } else {
                ui.displayMsg("Favorit-fil eksisterer allerede: " + file.getAbsolutePath());
            }
            userFavoritesDataPath = filePath;
            return userFavoritesDataPath;
        } catch (IOException e) {
            ui.displayMsg("Fejl ved oprettelse af favoritfil: " + e.getMessage());
            return null;
        }
    }

    // Creates a new watched media file for the specified user and returns the file path.
// If the file already exists, it notifies the user, otherwise it creates a new file.

    private String createWatchedMediaFile(User user) {
        try {
            String filePath = watchedMediaDataPath + user.getUsername() + "_watchedMedia.txt";
            File file = new File(filePath);
            if (file.createNewFile()) {
                ui.displayMsg("Sete film fil oprettet: " + file.getAbsolutePath());
            } else {
                ui.displayMsg("Sete film fil eksisterer allerede: " + file.getAbsolutePath());
            }
            userWatchedMediaDataPath = filePath;
            return userWatchedMediaDataPath;
        } catch (IOException e) {
            ui.displayMsg("Fejl ved oprettelse af sete medier fil: " + e.getMessage());
            return null;
        }
    }

// Retrieves the file path for the user's watched media data based on their username.
// If the user or username is null, it displays an error message and returns null.

    public String getUserWatchedMediaDataPath() {
        if (user != null && user.getUsername() != null) {
            userWatchedMediaDataPath = watchedMediaDataPath + user.getUsername() + "_watchedMedia.txt";
        } else {
            ui.displayMsg("Brugerdata mangler! Kan ikke generere sti for sete medier.");
        }
        return userWatchedMediaDataPath;
    }

// Retrieves the file path for the user's favorite media data based on their username.
// If the user or username is null, it displays an error message and returns null.

    public String getUserFavoritesDataPath() {
        if (user != null && user.getUsername() != null) {
            userFavoritesDataPath = favoritesDataPath + user.getUsername() + "_favoriteMedia.txt";
        } else {
            ui.displayMsg("Brugerdata mangler! Kan ikke generere sti for favoritmedier.");
        }
        return userFavoritesDataPath;
    }


}
