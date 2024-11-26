package StreamingProg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final String username;
    private final List<String> watchedMedia; // Placeholder for watched media
    private final List<String> savedMedia;  // Placeholder for saved media
    private final TextUI ui;

    public Menu(String username) {
        this.username = username;
        this.watchedMedia = new ArrayList<>();
        this.savedMedia = new ArrayList<>();
        this.ui = new TextUI();
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            ui.displayMsg("\n--- Main Menu ---");
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
                    return; // Exit menu
                }
                default -> ui.displayMsg("Invalid choice. Please try again.");
            }
        }
    }

    private void searchMedia(String title) {
        // Placeholder logic
        ui.displayMsg("Searching for media titled: " + title);
        ui.displayMsg("Media not found (placeholder logic).");
    }

    private void searchByCategory(String category) {
        // Placeholder logic
        ui.displayMsg("Searching for media in category: " + category);
        ui.displayMsg("No media found in this category (placeholder logic).");
    }

    private void displayWatchedMedia() {
        if (watchedMedia.isEmpty()) {
            ui.displayMsg("No watched media to display.");
        } else {
            ui.displayMsg("Watched Media:");
            for (String media : watchedMedia) {
                ui.displayMsg("- " + media);
            }
        }
    }

    private void displaySavedMedia() {
        if (savedMedia.isEmpty()) {
            ui.displayMsg("No saved media to display.");
        } else {
            ui.displayMsg("Saved Media:");
            for (String media : savedMedia) {
                ui.displayMsg("- " + media);
            }
        }
    }

    private void playMedia(String title) {
        // Placeholder logic
        ui.displayMsg("Playing media: " + title);
    }

    public void addWatchedMedia(String title) {
        watchedMedia.add(title);
    }

    public void addSavedMedia(String title) {
        savedMedia.add(title);
    }

    public void removeSavedMedia(String title) {
        if (savedMedia.remove(title)) {
            ui.displayMsg("Removed saved media: " + title);
        } else {
            ui.displayMsg("Media not found in saved list: " + title);
        }
    }
}
