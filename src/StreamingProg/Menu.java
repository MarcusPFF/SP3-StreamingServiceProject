package StreamingProg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    protected String username;
    protected List<Media> mediaList;
    private User user;
    TextUI ui = new TextUI();

    public Menu(String username, List<Media> mediaList, User user) {
        this.mediaList = new ArrayList<>(mediaList);
        this.username = username;
        this.user = user;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ui.displayMsg("Press 1. Search for a movie or series");
            ui.displayMsg("Press 2. Search for movies or series in categories");
            ui.displayMsg("Press 3. See list of watched movies and series");
            ui.displayMsg("Press 4. See list of saved movies and series");
            ui.displayMsg("Press 5. Play Movie");
            ui.displayMsg("Press 6. Log out");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    ui.displayMsg("Type the movie's name:");
                    String title = scanner.nextLine();
                    searchFilm(title);
                    break;
                case 2:
                    ui.displayMsg("Type category:");
                    String category = scanner.nextLine();
                    sortByCategory(category);
                    break;
                case 3:
                    watchedFilms();
                    break;
                case 4:
                    savedFilms();
                    break;
                case 5:
                    playMovie();
                    break;
                case 6:
                    ui.displayMsg("Logging Out...");
                    return;
                default:
                    ui.displayMsg("Invalid choice");
            }
        }
    }

    public void searchFilm(String title) {
        for (Media media : mediaList) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                ui.displayMsg("Movie found: " + media.getTitle());
                return;
            }
        }
        ui.displayMsg("Movie not found");
    }

    public void sortByCategory(String category) {
        boolean found = false;
        for (Media media : mediaList) {
            if (media.getGenre().equalsIgnoreCase(category)) {
                ui.displayMsg(media);
                found = true;
            }
        }
        if (!found) {
            ui.displayMsg("No movies found in category: " + category);
        }
    }


    public void watchedFilms() {
        ui.displayMsg("Watched movies and series:");
        for (Media media : user.getWatchedFilms()) {
            ui.displayMsg(media);
        }
    }

    public void savedFilms() {
        ui.displayMsg("Saved movies and series:");
        for (Media media : user.getSavedFilms()) {
            ui.displayMsg(media);
        }
    }

    public void playMovie() {
        ui.displayMsg("Playing movie...");
    }

    public void addMedia(Media media) {
        mediaList.add(media);
    }

    public void removeMedia(Media media) {
        mediaList.remove(media);
    }

    public boolean isAdmin() {
        return user.isAdmin();
    }
}
