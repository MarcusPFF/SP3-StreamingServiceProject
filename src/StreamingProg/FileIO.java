package StreamingProg;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class FileIO {
    private Menu menu;
    private User user;

    public FileIO() {
        this.menu = menu;
        this.user = new User();
    }

// Loads user data from a CSV file at the specified file path.
// If the file doesn't exist, it prints an error message and returns an empty list.
// Reads each line of the file, splits it by commas, and creates User objects with the data.

    public List<User> loadUserData(String filePath) {
        List<User> users = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Brugerdatafil ikke fundet: " + filePath);
            return users;
        }

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine(); // Skip header
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 3) {
                    String username = data[0].trim();
                    String password = data[1].trim();
                    boolean validate = Boolean.parseBoolean(data[2].trim());
                    users.add(new User(username, password, validate));
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af brugerdata: " + e.getMessage());
        }
        return users;
    }
    // Saves user data to a CSV file at the specified file path.
    // If the parent directories of the file don't exist, they are created.
    // Writes the user details (username, password, and admin status) for each user in the list to the file.

    public void saveUserData(String filePath, List<User> users) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); //Fix

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Username,Password,Validate\n");
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.isValidate() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning af brugerdata: " + e.getMessage());
        }
    }
// Saves the user's favorite media to a text file in the "favorites" folder.
// Creates the parent directories if they don't exist and writes the list of favorites to the file.
// Each favorite media is written to the file, with a header indicating the start of favorites.

    public void saveFavoriteMovieData(User user, List<Media> favorites) {
        String filePathFavorites = "data/favorites/" + user.getUsername() + "_favoriteMedia.txt";
        File file = new File(filePathFavorites);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(filePathFavorites)) {
            writer.write("Favoritter: \n");
            for (Media media : favorites) {
                writer.write(media + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning af favorit data: " + e.getMessage());
        }
    }
// Saves the user's watched media to a text file in the "watchedMedia" folder.
// Creates the parent directories if they don't exist and writes the list of watched media to the file.
// Each watched media is written to the file, with a header indicating the start of watched media.

    public void saveWatchedMediaData(User user, List<Media> watched) {
        String filePathWatched = "data/watchedMedia/" + user.getUsername() + "_watchedMedia.txt";
        File file = new File(filePathWatched);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(filePathWatched)) {
            writer.write("Sete medier:\n");
            for (Media media : watched) {
                writer.write(media + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning af medie data: " + e.getMessage());
        }
    }
// Loads the favorite media data from a specified file path.
// If the file doesn't exist, it prints an error message and returns an empty list.
// Reads each line of the file, parses the data, and creates Media objects (Movie or Series) based on the number of columns in the file.

    public List<Media> loadFavoriteData(String filePath) {
        List<Media> favoriteMedia = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("FavoritDataFil ikke fundet: " + filePath);
            return favoriteMedia;
        }

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                if (data.length == 4) {
                    String title = data[0].trim();
                    String genre = data[1].trim();
                    String releaseYear = data[2].trim();
                    String rating = data[3].trim();
                    Media media = new Movie(title, genre, releaseYear, rating);
                    favoriteMedia.add(media);
                } else if (data.length == 5) {
                    String title = data[0].trim();
                    String genre = data[1].trim();
                    String releaseYear = data[2].trim();
                    String rating = data[3].trim();
                    String seasons = data[4].trim();
                    Media media = new Series(title, genre, releaseYear, rating, seasons);
                    favoriteMedia.add(media);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af brugerdata: " + e.getMessage());
        }
        return favoriteMedia;
    }

    // Loads the watched media data from a specified file path.
    // If the file doesn't exist, it prints an error message and returns an empty list.
    // Reads each line of the file, parses the data, and creates Media objects (Movie or Series) based on the number of columns in the file.

    public List<Media> loadWatchedMediaData(String filePath) {
        List<Media> watchedMedia = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Set-MedierDataFil ikke fundet: " + filePath);
            return watchedMedia;
        }

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(";");
                if (data.length == 4) {
                    String title = data[0].trim();
                    String genre = data[1].trim();
                    String releaseYear = data[2].trim();
                    String rating = data[3].trim();
                    Media media = new Movie(title, genre, releaseYear, rating);
                    watchedMedia.add(media);
                } else if (data.length == 5) {
                    String title = data[0].trim();
                    String genre = data[1].trim();
                    String releaseYear = data[2].trim();
                    String rating = data[3].trim();
                    String seasons = data[4].trim();
                    Media media = new Series(title, genre, releaseYear, rating, seasons);
                    watchedMedia.add(media);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af brugerdata: " + e.getMessage());
        }
        return watchedMedia;
    }
}
