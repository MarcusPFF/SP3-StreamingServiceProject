package StreamingProg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final TextUI ui;
    private final List<User> userData;
    private User user; // Dynamisk bruger, der opdateres ved login/oprettelse
    private final String favoritesDataPath = "data/favorites/";
    private final String watchedMediaDataPath = "data/watchedMedia/";
    public String userFavoritesDataPath;
    public String userWatchedMediaDataPath;

    public UserManager() {
        this.ui = new TextUI();
        this.userData = new ArrayList<>();
        this.user = new User();
    }

    // Getter og setter for at arbejde med den aktuelle bruger
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

    // Opretter en ny bruger og initialiserer brugerfiler
    public void createUser(User newUser) {
        userData.add(newUser);
        this.user = newUser; // Sæt den nyoprettede bruger som aktiv bruger
        ui.displayMsg("Bruger oprettet: " + newUser.getUsername());
        createFavoritesFile(newUser);
        createWatchedMediaFile(newUser);
    }

    // Validerer brugerlogin
    public boolean validateUser(String username, String password) {
        for (User u : userData) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                if (u.getPassword().equals(password)) {
                    this.user = u; // Sæt den fundne bruger som aktiv bruger
                    ui.displayMsg("Login succesfuldt: " + username);
                    getUserWatchedMediaDataPath(); // Initialiser bruger-specifikke stier
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

    // Sletter en bruger
    public boolean deleteUser(String username) {
        if (userData.removeIf(u -> u.getUsername().equalsIgnoreCase(username))) {
            ui.displayMsg("Bruger slettet: " + username);
            return true;
        } else {
            ui.displayMsg("Bruger ikke fundet: " + username);
            return false;
        }
    }

    // Opretter en fil til favoritmedier for brugeren
    private String createFavoritesFile(User user) {
        try {
            String filePath = favoritesDataPath + user.getUsername() + "_favoriteMedias.txt";
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

    // Opretter en fil til sete medier for brugeren
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

    // Returnerer stien til den aktuelle brugers sete medier-fil
    public String getUserWatchedMediaDataPath() {
        if (user != null && user.getUsername() != null) {
            userWatchedMediaDataPath = watchedMediaDataPath + user.getUsername() + "_watchedMedia.txt";
        } else {
            ui.displayMsg("Brugerdata mangler! Kan ikke generere sti for sete medier.");
        }
        return userWatchedMediaDataPath;
    }

    // Returnerer stien til den aktuelle brugers favoritmedier-fil
    public String getUserFavoritesDataPath() {
        if (user != null && user.getUsername() != null) {
            userFavoritesDataPath = favoritesDataPath + user.getUsername() + "_favoriteMedias.txt";
        } else {
            ui.displayMsg("Brugerdata mangler! Kan ikke generere sti for favoritmedier.");
        }
        return userFavoritesDataPath;
    }



}
