package StreamingProg;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Netflix {

    List<String> movieData;

    UserManager userManager;
    Menu menu;
    TextUI ui;

    public Netflix(UserManager userManager, Menu menu, TextUI ui) {
        this.userManager = userManager;
        this.menu = menu;
        this.ui = ui;
    }

    public Netflix() {}
    public void runApplication() {
        ArrayList<Media> Medias = new ArrayList<>();
        FileIO fileIO = new FileIO();
        movieData = fileIO.readMovieData("entertainmentData\\movies.txt");

        for (int i = 0; i < movieData.size(); i++) {
            String[] movieInfo = movieData.get(i).split(";");
            String title = movieInfo[0];
            String releaseyear = movieInfo[1];
            String genre = movieInfo[2];
            float rating = Float.parseFloat(movieInfo[3]);

            Movie movie = new Movie(title, releaseyear, genre, rating);

            movieData.add(movieInfo[4]);

        }
    }

    public List<String> getMovieData() {
        return movieData;
    }


    public void runUserManager() {
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            ui.displayMsg("\n Vælg en mulighed: ");
            ui.displayMsg("1. Lav ny bruger");
            ui.displayMsg("2. Login");
            ui.displayMsg("3. Slet bruger");
            ui.displayMsg("4. Vis alle brugere");
            ui.displayMsg("5. Exit");
            ui.displayMsg("Vælg en mulighed");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    ui.displayMsg("Skriv brugernavn");
                    String username = scanner.nextLine();
                    ui.displayMsg("Skriv password");
                    String password = scanner.nextLine();
                    ui.displayMsg("Skal den her bruger være en admin?");
                    boolean isAdmin = Boolean.parseBoolean(scanner.nextLine());
                    userManager.createUser(username, password, isAdmin);
                    break;

                case 2:
                    ui.displayMsg("Skriv brugernavn");
                    String validUsername = scanner.nextLine();
                    ui.displayMsg("Skriv password");
                    String validatePassword = scanner.nextLine();

                    userManager.validateUser(validUsername, validatePassword);
                    break;
                case 3:
                    ui.displayMsg("Skriv det brugernavn du vil slette");
                    String deleteUsername = scanner.nextLine();
                    userManager.deleteUser(deleteUsername);
                    break;
                case 4:
                    ui.displayMsg("Lukker ned...");
                    running = false;
                    break;

                default:
                    ui.displayMsg("Forkert input. Prøv igen");
            }
        }
        scanner.close();
    }
}
