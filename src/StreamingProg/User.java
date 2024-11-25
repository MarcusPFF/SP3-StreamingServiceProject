package StreamingProg;

import java.util.List;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private boolean isAdmin;
    private List<Media> watchedFilms = new ArrayList<>();
    private List<Media> savedFilms = new ArrayList<>();


    public User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<Media> getWatchedFilms() {
        return watchedFilms;
    }

    public void setWatchedFilms(List<Media> watchedFilms) {
        this.watchedFilms = watchedFilms;
    }

    public List<Media> getSavedFilms() {
        return savedFilms;
    }

    public void setSavedFilms(List<Media> savedFilms) {
        this.savedFilms = savedFilms;
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", isAdmin=" + isAdmin + '}';
    }
}