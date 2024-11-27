package StreamingProg;

import org.w3c.dom.stylesheets.MediaList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class FileIO {
    private Menu menu;
    private User user;

    public FileIO() {
        this.menu = menu;
        this.user = new User();
    }

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
                    boolean isAdmin = Boolean.parseBoolean(data[2].trim());
                    users.add(new User(username, password, isAdmin));
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af brugerdata: " + e.getMessage());
        }
        return users;
    }

    public void saveUserData(String filePath, List<User> users) {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); //Fix

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Username,Password,isAdmin\n");
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.isAdmin() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning af brugerdata: " + e.getMessage());
        }
    }

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
}
