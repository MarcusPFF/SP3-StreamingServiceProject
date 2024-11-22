package StreamingProg;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    protected String username;
    protected List<String> mediaList;

    public Menu(String username, List<String> mediaList) {
        this.mediaList = new ArrayList<>();
        this.username = username;
    }

    public void searchFilm(String title) {

    } // Definer String title i anden klasse

    public void sortByCategory(String category) {

    } // Definer String category i anden klasse

    public void watchedFilms(String username) {

    } // Definer String username i anden klasse

    public void savedFilms(String username) {

    } // Definer String userName i anden klasse

    public List<String> searchMedia(String search) {
        return mediaList;
    }

    public void displayMenu() {

    }

    public void populateMediaLists(List<String> mediaList) {

    }

    public void addMedia(Media media) {

    }

    public void removeMedia(Media media) {

    }

    public boolean isAdmin() {
        return false;
    }
}
