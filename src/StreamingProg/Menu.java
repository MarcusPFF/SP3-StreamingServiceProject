package StreamingProg;

import java.io.File;
import java.io.FileNotFoundException;
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

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ui.displayMsg("\n--- Main Menu for " + username + " ---");
            ui.displayMsg("1. Vis medie");
            ui.displayMsg("2. Søg efter film eller serie");
            ui.displayMsg("3. Søg efter kategori");
            ui.displayMsg("4. Vis sete medie");
            ui.displayMsg("5. Vis gemte medie");
            ui.displayMsg("6. Spil media");
            ui.displayMsg("7. Log ud");

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
                default -> ui.displayMsg("Ugyldigt input. Prøv igen");
            }
        }
    }

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

    private List<Media> loadMovies(String filePath) {
        try (Scanner scanner3 = new Scanner(new File(filePath))) {
            while (scanner3.hasNextLine()) {
                String line = scanner3.nextLine();
                String[] data = line.split(";");
                if (data.length == 4) {
                    movies.add(new Movie(data[0], data[1], data[2], (data[3])));
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

    private List<Media> loadSeries(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                if (data.length == 5) {
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

    private void displayWatchedMedia() {
        if (watchedMedia.isEmpty()) {
            ui.displayMsg("Ikke set medie: ");
        } else {
            ui.displayMsg("Sete film og serier: ");
            for (Media media : watchedMedia) {
                ui.displayMsg(media.toString());
            }
        }
    }

    private void displayFavoriteMedia() {
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
            if (answer == true) {
                favoriteMedia.add(media);
                io.saveFavoriteMovieData(user, favoriteMedia);

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



