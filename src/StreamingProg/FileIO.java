package StreamingProg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;

public class FileIO {
    private User user;
    private String path = "data/userData/userData.txt";

    public FileIO(User user, String path) {
        this.user = user;
        this.path = path;
    }

    public HashMap<String, Integer> loadUserData(String path) {
        HashMap<String, Integer> userData = new HashMap<>();
        String username;
        String password;
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 3) {
                    username = data[0].trim();
                    password = data[1].trim();
                    boolean isAdmin = Boolean.parseBoolean(data[2].trim());
                    userData.put(username, Integer.parseInt(password));
                    if (isAdmin) {
                        System.out.println("Bruger: " + username + " har admin rettigheder");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ingen fil fundet");
        }
        return userData;
    }

    public void saveUserData(String path, HashMap<String, Integer> userData) {
        String username = user.getUsername();
        File file = new File(path);
        try {
            FileWriter writer = new FileWriter(username + "watchedMovies.txt");
        } catch (IOException e) {
            System.out.println("Fejlede med skrive til fil");
        }
    }


    public List<String> readMovieData(String path) {
        List<String> moviesList = new ArrayList<>();
        return moviesList;
    }

    public List<String> readSeriesData(String path) {
        List<String> seriesList = new ArrayList<>();
        return seriesList;
    }

    public void addMedia() {

    }

    public void removeMedia() {

    }
}