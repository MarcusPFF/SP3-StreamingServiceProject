package StreamingProg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    protected String username;
    protected List<Media> mediaList;
    private User user;
    TextUI textUI = new TextUI();

    public Menu(String username, List<Media> mediaList, User user) {
        this.mediaList = new ArrayList<>(mediaList);
        this.username = username;
        this.user = user;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Press 1. Search for a movie or series");
            System.out.println("Press 2. Search for movies or series in categories");
            System.out.println("Press 3. See list of watched movies and series");
            System.out.println("Press 4. See list of saved movies and series");
            System.out.println("Press 5. Play Movie");
            System.out.println("Press 6. Log out");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    textUI.displayMsg("Type the movie's name:");
                    String title = scanner.nextLine();
                    searchFilm(title);
                    break;
                case 2:
                    textUI.displayMsg("Type category:");
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
                    textUI.displayMsg("Logging Out...");
                    return;
                default:
                    textUI.displayMsg("Invalid choice");
            }
        }
    }

    public void searchFilm(String title) {
        for (Media media : mediaList) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Movie found: " + media.getTitle());
                return;
            }
        }
        System.out.println("Movie not found");
    }

    public void sortByCategory(String category) {
        boolean found = false;
        for (Media media : mediaList) {
            if (media.getGenre().equalsIgnoreCase(category)) {
                System.out.println(media);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No movies found in category: " + category);
        }
    }


    public void watchedFilms() {
        System.out.println("Watched movies and series:");
        for (Media media : user.getWatchedFilms()) {
            System.out.println(media);
        }
    }

    public void savedFilms() {
        System.out.println("Saved movies and series:");
        for (Media media : user.getSavedFilms()) {
            System.out.println(media);
        }
    }

    public void playMovie() {
        System.out.println("Playing movie...");
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
