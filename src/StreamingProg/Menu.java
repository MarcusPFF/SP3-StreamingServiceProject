package StreamingProg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final TextUI ui;
    private final List<Media> movies;
    private final List<Media> series;
    private final List<Media> watchedMedia;
    private final List<Media> savedMedia;
    private String username;

    // No argument constructor
    public Menu() {
        this.ui = new TextUI();
        this.movies = new ArrayList<>();
        this.series = new ArrayList<>();
        this.watchedMedia = new ArrayList<>();
        this.savedMedia = new ArrayList<>();
        this.username = "Guest"; // Default username
        loadMovies("data/entertainmentData/movies.txt");
        loadSeries("data/entertainmentData/series.txt");
    }

    // Constructor with username
    public Menu(String username) {
        this();
        this.username = username;
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ui.displayMsg("\n--- Main Menu for " + username + " ---");
            ui.displayMsg("1. Search for a movie or series");
            ui.displayMsg("2. Search by category");
            ui.displayMsg("3. View watched media");
            ui.displayMsg("4. View saved media");
            ui.displayMsg("5. Play media");
            ui.displayMsg("6. Log out");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ui.displayMsg("Invalid input. Please enter a number between 1-6.");
                continue;
            }

            switch (choice) {
                case 1 -> searchMedia(ui.promptText("Enter the title of the media:"));
                case 2 -> searchByCategory(ui.promptText("Enter the category:"));
                case 3 -> displayWatchedMedia();
                case 4 -> displaySavedMedia();
                case 5 -> playMedia(ui.promptText("Enter the title of the media to play:"));
                case 6 -> {
                    ui.displayMsg("Logging out...");
                    return;
                }
                default -> ui.displayMsg("Invalid choice. Please try again.");
            }
        }
    }

    private void loadMovies(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                if (data.length == 4) {
                    movies.add(new Movie(data[0], data[1], data[2], Float.parseFloat(data[3])));
                }
            }
            ui.displayMsg("Movies loaded successfully.");
        } catch (FileNotFoundException e) {
            ui.displayMsg("Movies file not found: " + filePath);
        } catch (Exception e) {
            ui.displayMsg("Error loading movies: " + e.getMessage());
        }
    }

    private void loadSeries(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                if (data.length == 4) {
                    series.add(new Series(data[0], data[1], data[2], Float.parseFloat(data[3])));
                }
            }
            ui.displayMsg("Series loaded successfully.");
        } catch (FileNotFoundException e) {
            ui.displayMsg("Series file not found: " + filePath);
        } catch (Exception e) {
            ui.displayMsg("Error loading series: " + e.getMessage());
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
            ui.displayMsg("No media found with the title: " + title);
        } else {
            ui.displayMsg("Media found:");
            for (Media media : foundMedia) {
                ui.displayMsg(media.toString());
            }
        }
    }

    private void searchByCategory(String category) {
        List<Media> foundMedia = new ArrayList<>();
        for (Media media : movies) {
            if (media.getGenre().equalsIgnoreCase(category)) {
                foundMedia.add(media);
            }
        }
        for (Media media : series) {
            if (media.getGenre().equalsIgnoreCase(category)) {
                foundMedia.add(media);
            }
        }

        if (foundMedia.isEmpty()) {
            ui.displayMsg("No media found in category: " + category);
        } else {
            ui.displayMsg("Media found in category " + category + ":");
            for (Media media : foundMedia) {
                ui.displayMsg(media.toString());
            }
        }
    }

    private void displayWatchedMedia() {
        if (watchedMedia.isEmpty()) {
            ui.displayMsg("No watched media.");
        } else {
            ui.displayMsg("Watched Media:");
            for (Media media : watchedMedia) {
                ui.displayMsg(media.toString());
            }
        }
    }

    private void displaySavedMedia() {
        if (savedMedia.isEmpty()) {
            ui.displayMsg("No saved media.");
        } else {
            ui.displayMsg("Saved Media:");
            for (Media media : savedMedia) {
                ui.displayMsg(media.toString());
            }
        }
    }

    private void playMedia(String title) {
        for (Media media : movies) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                media.play();
                watchedMedia.add(media);
                return;
            }
        }
        for (Media media : series) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                media.play();
                watchedMedia.add(media);
                return;
            }
        }
        ui.displayMsg("Media not found: " + title);
    }
}
