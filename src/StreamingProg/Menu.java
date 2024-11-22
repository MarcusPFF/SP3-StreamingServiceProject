package StreamingProg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    protected String username;
    protected List<String> mediaList;
    TextUI textUI = new TextUI();
    Media media;
    Movie movie;

    public Menu(String username, List<String> mediaList) {
        this.mediaList = new ArrayList<>();
        this.username = username;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Press 1. Search for a movie or series");
            System.out.println("Press 2. Search for movies or series in categories");
            System.out.println("Press 3. See list over watched movies and series");
            System.out.println("Press 4. See list over saved movies and series");
            System.out.println("Press 5. Play Movie");
            System.out.println("Press 6. Log out");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    textUI.displayMsg("Type the movie's name:");
                    String title = scanner.nextLine();
                    searchFilm(title);
                    break;

                case 2:
                    textUI.displayMsg("Type category (Crime, Drama, History, Biography, Sport, Romance, Adventure, Family, Fantasy, Thriller, Horror, Film-Noir, Action, Sci-Fi, Comedy, Musical, War, Mystery, Western)");
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
                    textUI.displayMsg("Type the movie's name:");
                    title = scanner.nextLine();
                    movie.playMovie(title);  // Movies er defineret med 4 parametre og ikke kun navn
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
            if (media instanceof Movie && media.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Movie found: " + media.getTitle());
                return;
            }
        }
        System.out.println("movie not found");
    }

    public void sortByCategory(String category) {
        boolean found = false;
        for (Media media: mediaList){
            if(media.getGenre().equalsIgnoreCase(category)){
                System.out.println();


            }
        }
    } // Definer String category i anden klasse

    public void watchedFilms() {

    } // Definer String username i anden klasse

    public void savedFilms() {

    } // Definer String userName i anden klasse

    public List<String> searchMedia(String search) {
        return mediaList;
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
