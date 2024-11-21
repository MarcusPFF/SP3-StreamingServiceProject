package StreamingProg;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String username;
    private List<Media> mediaList;

    Menu(String username, List<Media> mediaList) {
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

    public List<Media> searchMedia(String search) {
        return mediaList;
    }

    public void displayMenu() {

    }

    public void populateMediaLists(List<Media> mediaList) {

    }

    public void addMedia(Media media) {
        mediaList.add(media);
    }

    public void removeMedia(Media media) {
        mediaList.remove(media);
    }

    public boolean isAdmin() {
        return false;
    }
}
