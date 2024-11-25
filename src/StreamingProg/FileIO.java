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
    //Ret String path til userDataPath, moviesDataPath, seriesDataPath inde i plantUML
    private User user;
    private String userDataPath = "data/userData/userData.txt";
    private String moviesDataPath = "data/moviesData/movies.txt";
    private String seriesDataPath = "data/seriesData/series.txt";
    private List<String> moviesList;
    private List<String> seriesList;

    public FileIO(User user, String userDataPath, String moviesDataPath, String seriesDataPath, List<String> moviesList, List<String> seriesList) {
        this.user = user;
        this.userDataPath = userDataPath;
        this.moviesDataPath = moviesDataPath;
        this.seriesDataPath = seriesDataPath;
        this.moviesList = moviesList;
        this.seriesList = seriesList;
    }

    public HashMap<String, Integer> loadUserData(String userDataPath) {
        HashMap<String, Integer> userData = new HashMap<>();
        String username;
        String password;
        File file = new File(userDataPath);
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
                        //Vi skal bruge displayMsg funktionen her
                        System.out.println("Bruger: " + username + " har admin rettigheder");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            //Vi skal bruge displayMsg funktionen her
            System.out.println("Ingen fil fundet");
        }
        return userData;
    }

    public void saveUserData(String userDataPath, HashMap<String, Integer> userData) {
        File file = new File(userDataPath);
        String username = user.getUsername();
        String fileName = userDataPath + username + "_watchedMovies.txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            for (String keys : userData.keySet()) {
                int rating = userData.get(keys);
                writer.write(keys + ": " + rating + System.lineSeparator());
            }
            //displayMsg funktion her
            System.out.println("Data blev gemt succesfuldt til: " + fileName);
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + e.getMessage());
        }
    }

    public List<String> readMovieData(String moviesDataPath) {

        List<String> moviesList = new ArrayList<>();
        File file = new File(moviesDataPath);

        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                if (!line.isEmpty()) {
                    moviesList.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ingen fil fundet på stien: " + moviesDataPath);
        }

        return moviesList;
    }

    public List<String> readSeriesData(String seriesDataPath) {
        List<String> seriesList = new ArrayList<>();
        File file = new File(seriesDataPath);

        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                if (!line.isEmpty()) {
                    seriesList.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ingen fil fundet på stien: " + seriesDataPath);
        }

        return seriesList;
    }

    public void addMedia() {

        Scanner scanner = new Scanner(System.in);

        //displayMsg funktion her
        System.out.println("Skriv 1 for film, 2 for serier");
        int choice = Integer.parseInt(scanner.nextLine());
        scanner.close();
        switch (choice) {
            case 1:
                //displayMsg funktion her
                System.out.println("Media du kan tilføje til dine favoritter");
                for (int i = 0; i < moviesList.size(); i++) {
                    System.out.println((i + 1) + ". " + moviesList.get(i));
                }
                //displayMsg funktion her
                System.out.println("Skriv nummeret på filmen du gerne vil tilføje til dine favoritter");
                int movieChoice = Integer.parseInt(scanner.nextLine());
                if (movieChoice < 1 || movieChoice > moviesList.size()) {
                    System.out.println("Fejl. Du skal vælge et tal som er gyldigt.");
                    return;
                }

            case 2:
                // samme kode som case1, men bare med seriesList


        }
    }

    public void removeMedia() {
    }
}