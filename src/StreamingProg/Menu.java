package StreamingProg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final TextUI ui;
    private final FileIO io;
    private User user;
    private final List<Media> movies;
    private final List<Media> series;
    private final List<Media> watchedMedia;
    private final List<Media> favoriteMedia;
    private final UserManager um;
    private String username;
    private Object addMedia;

    // No argument constructor
    public Menu() {
        this.ui = new TextUI();
        this.io = new FileIO();
        this.user = new User();
        this.movies = new ArrayList<>();
        this.series = new ArrayList<>();
        this.watchedMedia = new ArrayList<>();
        this.favoriteMedia = new ArrayList<>();
        this.um = new UserManager();
        this.username = "Gæst"; // Default username
        loadMovies("data/entertainmentData/movies.txt");
        loadSeries("data/entertainmentData/series.txt");
    }

    // Constructor with username
    public Menu(String username) {
        this(); //Fix
        this.username = username;
        this.user = new User(username);
    }

    /*
    The displayMenu method presents the main menu for the user, displaying different options based on user type.
    It continuously prompts the user for a choice, validates the input, and executes the corresponding action.
    Admin users have additional options for managing media (adding/removing). The loop continues until the user logs out.
    */
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (user.isAdmin()==false) {
                ui.displayMsg("\n--- Main Menu for " + username + " ---");
                ui.displayMsg("1. Vis medie");
                ui.displayMsg("2. Søg efter film eller serie");
                ui.displayMsg("3. Søg efter kategori");
                ui.displayMsg("4. Vis sete medier");
                ui.displayMsg("5. Vis favorit medier");
                ui.displayMsg("6. Spil media");
                ui.displayMsg("7. Log ud");
            }
            else if (user.isAdmin()) {
                ui.displayMsg("\n--- Main Menu for " + username + " ---");
                ui.displayMsg("1. Vis medie");
                ui.displayMsg("2. Søg efter film eller serie");
                ui.displayMsg("3. Søg efter kategori");
                ui.displayMsg("4. Vis sete medier");
                ui.displayMsg("5. Vis favorit medier");
                ui.displayMsg("6. Spil media");
                ui.displayMsg("7. Log ud");
                ui.displayMsg("\n--- Admin Menu ---");
                ui.displayMsg("8. Tilføj medie");
                ui.displayMsg("9. Fjern medie");
            }
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ui.displayMsg("Ugyldigt input. Skriv et nummer mellem 1-7");
                continue;
            }

            switch (choice) {
                case 1 -> showMedia();
                case 2 -> searchMedia(ui.promptText("Skriv titlen på filmen eller serien:"));
                case 3 -> searchByCategory(ui.promptText("Skriv kategori:"));
                case 4 -> displayWatchedMedia();
                case 5 -> displayFavoriteMedia();
                case 6 -> playMedia(ui.promptText("Skriv titlen på det media du vil afspille:"));
                case 7 -> {
                    ui.displayMsg("Logger ud...");
                    return;
                }
                case 8 -> {
                    if (user.isAdmin()==true) {
                        addNewMedia();
                    }
                }
                case 9 -> {
                    if (user.isAdmin()==true) {
                        removeMedia();
                    }
                }
                default -> ui.displayMsg("Ugyldigt input. Prøv igen");
            }
        }
    }
// Prompts the user to add a new movie or series.
// Based on the user's input, it writes the movie or series data to the corresponding file (movies.txt or series.txt).
// If an error occurs while writing the data, it catches the IOException and prints an error message.

    private void addNewMedia() {
        String answer = ui.promptText("Ønsker du at tilføje en film eller en serie? Y/N");
        if ("film" == answer) {
            String movieToAdd = ui.promptText("Skriv - (navn; årstal; genre; rating) - på filmen. I viste format.");
            String moviesPath = "entertainmentData/movies.txt";
            File moviefile = new File(moviesPath);
            try (FileWriter writer = new FileWriter(moviefile)) {
                writer.write(movieToAdd);
            } catch (IOException e) {
                System.out.println("Fejl ved skrivning af film data: " + e.getMessage());
            }
        } else if (answer == "serie") {
            String serieToAdd = ui.promptText("Skriv - (navn; årstal; genre; rating; sæson) - på serien. I viste format.");
            String seriesPath = "entertainmentData/series.txt";
            File seriefile = new File(seriesPath);
            try (FileWriter writer = new FileWriter(seriefile)) {
                writer.write(serieToAdd);
            } catch (IOException e) {
                System.out.println("Fejl ved skrivning af film data: " + e.getMessage());
            }
        }
    }

    private void removeMedia() {

    }


    /*
    The showMedia method displays the list of movies and series.
    It reads movie titles from a file and prints them, followed by reading and displaying series titles from another file.
    If either file is not found, an error message is shown indicating which file is missing.
    */
    private void showMedia() {
        ui.displayMsg("----Film----");
        try (Scanner scanner1 = new Scanner(new File("data/entertainmentData/movies.txt"))) {
            while (scanner1.hasNextLine()) {
                ui.displayMsg(scanner1.nextLine());
            }
        } catch (FileNotFoundException e) {
            ui.displayMsg("Film fil blevet ikke fundet på: ");
        }

        ui.displayMsg("----Serier----");
        try (Scanner scanner2 = new Scanner(new File("data/entertainmentData/series.txt"))) {
            while (scanner2.hasNextLine()) {
                ui.displayMsg(scanner2.nextLine());
            }
        } catch (FileNotFoundException e) {
            ui.displayMsg("Serier film blev ikke fundet på: ");
        }
    }

    /*
    The loadMovies method reads movie data from a specified file and populates a list of Movie objects.
    It processes each line of the file, splits it by semicolons, and creates a Movie object if the data is valid.
    If the file is not found or an error occurs during reading, an appropriate error message is displayed.
    */
    private List<Media> loadMovies(String filePath) {
        try (Scanner scanner3 = new Scanner(new File(filePath))) {
            while (scanner3.hasNextLine()) {
                String line = scanner3.nextLine();
                String[] data = line.split(";");
                if (data.length == 4) {
                    /*String titel = data[0] + ";";
                    String genre = data[1] + ";";
                    String realeaseYear = data[2] + ";";
                    String rating = data[3] + ";";*/
                    movies.add(new Movie(data[0], data[1], data[2], data[3]));
                }
            }
            ui.displayMsg("Film blev læst korrekt");
            return movies;
        } catch (FileNotFoundException e) {
            ui.displayMsg("Film fil blevet ikke fundet på " + filePath);
            return movies;
        } catch (Exception e) {
            ui.displayMsg("Fejl ved læsning af fil " + e.getMessage());
            return movies;
        }
    }

    /*
    The loadSeries method reads series data from a specified file and populates a list of Series objects.
    It processes each line of the file, splits it by semicolons, and creates a Series object if the data is valid.
    If the file is not found or an error occurs during reading, an appropriate error message is displayed.
    */
    private List<Media> loadSeries(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                if (data.length == 5) {
                    /*String titel = data[0] + ";";
                    String genre = data[1] + ";";
                    String realeaseYear = data[2] + ";";
                    String rating = data[3] + ";";
                    String seasons = data[4] + ";";*/
                    series.add(new Series(data[0], data[1], data[2], data[3], data[4]));
                }
            }
            ui.displayMsg("Serier blev læst korrekt");
            return series;
        } catch (FileNotFoundException e) {
            ui.displayMsg("Serie fil blev ikke fundet på " + filePath);
            return series;
        } catch (Exception e) {
            ui.displayMsg("Fejl ved læsning af fil " + e.getMessage());
            return series;
        }
    }

    /*
    The searchMedia method searches for media by title across both movies and series lists.
    It adds matching media items to a result list, then displays the results or a message if no match is found.
    The search is case-insensitive to ensure it finds titles regardless of letter casing.
    */
    private void searchMedia(String title) {
        List<Media> foundMedia = new ArrayList<>();
        for (Media media : movies) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                foundMedia.add(media);
            }
        }

        for (Media media : series) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                foundMedia.add(media);
            }
        }

        if (foundMedia.isEmpty()) {
            ui.displayMsg("Ingen medie fundet med titlen:  " + title);
        } else {
            ui.displayMsg("Medie fundet: ");
            for (Media media : foundMedia) {
                ui.displayMsg(media.toString());
            }
        }
    }

    /*
    The searchByCategory method searches for media by genre across both movies and series lists.
    It adds matching media items to a result list based on whether their genre contains the specified category.
    The results are displayed, or a message is shown if no media matches the given category.
    */
    private void searchByCategory(String category) {
        List<Media> foundMedia = new ArrayList<>();
        for (Media media : movies) {
            if (media.getGenre().contains(category)) {
                foundMedia.add(media);
            }
        }
        for (Media media : series) {
            if (media.getGenre().contains(category)) {
                foundMedia.add(media);
            }
        }
        if (foundMedia.isEmpty()) {
            ui.displayMsg("Ingen medie fundet i kategorien " + category);
        } else {
            ui.displayMsg("Disse film indeholder " + category + ":");
            for (Media media : foundMedia) {
                ui.displayMsg(media.toString());
            }
        }
    }

    /*
    The displayWatchedMedia method loads the user's watched media from a file and displays the list.
    If no watched media is found, it shows a message indicating there are no watched items.
    Otherwise, it iterates through and displays each media item the user has watched.
    */
    private void displayWatchedMedia() {
        io.loadWatchedMediaData("data/watchedMedia/" + user.getUsername() + "_watchedMedia.txt");
        System.out.println("WatchedMedia: " + watchedMedia);
        if (watchedMedia.isEmpty()) {
            ui.displayMsg("Ikke set medie: ");
        } else {
            ui.displayMsg("Sete film og serier: ");
            for (Media media : watchedMedia) {
                ui.displayMsg(media.toString());
            }
        }
    }

    //Same method here as displayWatchedMedia();
    private void displayFavoriteMedia() {
        io.loadFavoriteData("data/favorites/" + user.getUsername() + "_favoriteMedia.txt");
        if (favoriteMedia.isEmpty()) {
            ui.displayMsg("Ingen gemte medier: ");
        } else {
            ui.displayMsg("Gemte medier: ");
            for (Media media : favoriteMedia) {
                ui.displayMsg(media.toString());
            }
        }
    }

    private void playMedia(String title) {
        try {
            io.loadWatchedMediaData("data/watchedMedia/" + user.getUsername() + "_watchedMedia.txt");
            io.loadFavoriteData("data/favorites/" + user.getUsername() + "_favoriteMedia.txt");
            System.out.println(user.getUsername());
            for (Media media : movies) {
                if (media.getTitle().equalsIgnoreCase(title)) {
                    media.play();
                    watchedMedia.add(media);
                }
            }
            for (Media media : series) {
                if (media.getTitle().contains(title)) {
                    media.play();
                    watchedMedia.add(media);
                }
            }
            io.saveWatchedMediaData(user, watchedMedia);
            boolean answer = ui.promptBinary("Ønsker du at tilføje dette medie til dine favoritter: " + title);
            String type = "";
            if (answer == true) {
                for (Media media : movies) {
                    if (media.getTitle().equalsIgnoreCase(title)) {
                        favoriteMedia.add(media);
                        type = "Filmen";
                    }
                }
                for (Media media : series) {
                    if (media.getTitle().contains(title)) {
                        favoriteMedia.add(media);
                        type = "Serien";
                    }
                }
                io.saveFavoriteMovieData(user, favoriteMedia);
                ui.displayMsg(type + " er tilføjet til dine favoritter");
                displayMenu();
            } else {
                displayMenu();
            }
        } catch (Exception e) {
            ui.displayMsg("Medie findes ikke " + title);
        }
    }

    public List<Media> getWatchedMedia() {
        return watchedMedia;
    }

    public List<Media> getFavoriteMedia() {
        return favoriteMedia;
    }
}



