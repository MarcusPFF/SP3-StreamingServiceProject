package StreamingProg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;

public class FileIO {
    //Ret String path til userDataPath, moviesDataPath, seriesDataPath inde i plantUML
    private User user;
    private String userDataPath = "data/userData/userData.csv";
    private String watchedMoviesDataPath = "data/watchedMovies/";
    private String favoritesDataPath = "data/favorites/";
    private String moviesDataPath = "data/entertainmentData/movies.txt";
    private String seriesDataPath = "data/entertainmentData/series.txt";
    private List<String> moviesList;
    private List<String> seriesList;
    private List<String> favoritesList;
    private TextUI ui;
    private UserManager um;

    public FileIO() {

        this.userDataPath = userDataPath;
        this.moviesDataPath = moviesDataPath;
        this.seriesDataPath = seriesDataPath;
        this.moviesList = new ArrayList<>();
        this.seriesList = new ArrayList<>();
        this.favoritesList = new ArrayList<>();
        this.ui = new TextUI();
        this.um = new UserManager();}


    public List<User> loadUserData(String userDataPath) {
        File file = new File(userDataPath);
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine(); // Spring header over
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");
                if (data.length == 3) {
                    String username = data[0].trim();
                    String password = data[1].trim();
                    boolean isAdmin = Boolean.parseBoolean(data[2].trim());

                    // Opretter en User og tilføjer den til listen
                    User user = new User(username, password, isAdmin);
                    userData.add(user);

                    // Hvis brugeren er admin, vis en meddelelse
                    if (isAdmin) {
                        // Her skal du bruge din displayMsg funktion
                        System.out.println("Bruger: " + username + " har admin rettigheder");
                    }
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            // Brug displayMsg funktion her
            System.out.println("Ingen fil fundet på stien: " + userDataPath);
        }
        return userData;
    }

    public void saveUserData(String watchedMoviesDataPath, HashMap<String, Integer> userData) {
        File file = new File(watchedMoviesDataPath);
        String username = user.getUsername();
        String fileName = watchedMoviesDataPath + username + "_watchedMovies.txt";

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
        moviesList = new ArrayList<>();
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
        List<String> favoritesList = new ArrayList<>();

        int choice = ui.promptNumeric("Skriv 1 for film, 2 for serier");
        switch (choice) {
            case 1:
                ui.displayMsg("Medier du kan tilføje til dine favoritter:");
                for (int i = 0; i < moviesList.size(); i++) {
                    ui.displayMsg((i + 1) + ". " + moviesList.get(i));
                }
                int movieChoice = ui.promptNumeric("Skriv nummeret på filmen du gerne vil tilføje til dine favoritter");
                if (movieChoice < 1 || movieChoice > moviesList.size()) {
                    ui.displayMsg("Fejl. Du skal vælge et tal som er gyldigt.");
                    return;
                }
                favoritesList.add(moviesList.get(movieChoice - 1));
                break;

            case 2:
                ui.displayMsg("Medier du kan tilføje til dine favoritter:");
                for (int i = 0; i < seriesList.size(); i++) {
                    ui.displayMsg((i + 1) + ". " + seriesList.get(i));
                }
                int seriesChoice = ui.promptNumeric("Skriv nummeret på serien du gerne vil tilføje til dine favoritter");
                if (seriesChoice < 1 || seriesChoice > seriesList.size()) {
                    ui.displayMsg("Fejl. Du skal vælge et tal som er gyldigt.");
                    return;
                }
                favoritesList.add(seriesList.get(seriesChoice - 1));
                break;

            default:
                ui.displayMsg("Ugyldigt valg. Prøv igen.");
                return;
        }

        saveFavoritesToFile(favoritesList);
    }

    public void removeMedia() {
        if (favoritesList.isEmpty()) {
            System.out.println("Du har ingen favoritter at fjerne.");
            return;
        }

        System.out.println("Dine favoritter:");
        for (int i = 0; i < favoritesList.size(); i++) {
            System.out.println((i + 1) + ". " + favoritesList.get(i));
        }

        int choice = ui.promptNumeric("Skriv nummeret på den favorit, du vil fjerne:");
        if (choice < 1 || choice > favoritesList.size()) {
            System.out.println("Fejl. Du skal vælge et tal som er gyldigt.");
            removeMedia();
        }

        String removedMedia = favoritesList.remove(choice - 1);
        System.out.println(removedMedia + " er blevet fjernet fra dine favoritter.");

        saveFavoritesToFile(favoritesList);
    }


    public void saveFavoritesToFile(List<String> favoritesList) {
        File file = new File(favoritesDataPath);
        String username = user.getUsername();
        String fileName = favoritesDataPath + username + "favoriteMedias.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String favorite : favoritesList) {
                writer.write(favorite + "\n");
            }
            ui.displayMsg("Favoritter gemt i " + fileName);
        } catch (IOException e) {
            ui.displayMsg("Der opstod en fejl under skrivning til filen: " + e.getMessage());
        }
    }

    // Ensure userData is initialized at the start
    List<User> userData = new ArrayList<>();




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserDataPath() {
        return userDataPath;
    }

    public void setUserDataPath(String userDataPath) {
        this.userDataPath = userDataPath;
    }

    public String getMoviesDataPath() {
        return moviesDataPath;
    }

    public void setMoviesDataPath(String moviesDataPath) {
        this.moviesDataPath = moviesDataPath;
    }

    public String getSeriesDataPath() {
        return seriesDataPath;
    }

    public void setSeriesDataPath(String seriesDataPath) {
        this.seriesDataPath = seriesDataPath;
    }

    public List<String> getMoviesList() {
        if (moviesList == null) {
            System.out.println("Filmenes liste er tom. Har du læst dataene?");
        }
        return moviesList;
    }

    public void setMoviesList(List<String> moviesList) {
        this.moviesList = moviesList;
    }

    public List<String> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<String> seriesList) {
        this.seriesList = seriesList;
    }

}