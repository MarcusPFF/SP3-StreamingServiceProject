package StreamingProg;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Netflix {

    private List<String> movieData;
    private UserManager userManager;
    private Menu menu = new Menu();
    private TextUI ui = new TextUI();

    public Netflix() {
        this.userManager = new UserManager();
    }

    public void runApplication() {
        ui.displayMsg("Velkommen til netflix-backend streamingtjeneste systemet");
        runUserManager();
        //runMenu();
    }

    public void runQuestionMark() {
        ArrayList<Media> Medias = new ArrayList<>();
        FileIO fileIO = new FileIO();
        movieData = fileIO.readMovieData(fileIO.getMoviesDataPath());

        for (int i = 0; i > movieData.size(); i++) {
            String[] movieInfo = movieData.get(i).split(";");
            String title = movieInfo[0];
            String releaseyear = movieInfo[1];
            String genre = movieInfo[2];
            float rating = Float.parseFloat(movieInfo[3].replace(",", "."));

            Movie movie = new Movie(title, releaseyear, genre, rating);

            movieData.add(movieInfo[4]);

        }
    }

    public List<String> getMovieData() {
        return movieData;
    }


    public void runUserManager() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            ui.displayMsg(" --Vælg en mulighed-- ");
            ui.displayMsg("1. Lav ny bruger");
            ui.displayMsg("2. Login");
            ui.displayMsg("3. Slet bruger");
            ui.displayMsg("4. Vis alle brugere");
            ui.displayMsg("5. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1: {
                    ui.displayMsg("Lav ny bruger");
                    String username = ui.promptText("Skriv dit brugernavn");
                    String password = ui.promptText("Skriv password");
                    boolean isAdmin = ui.promptBinary("Skal brugeren være admin? Y/N");
                    userManager.createUser(username, password, isAdmin);
                    break;
                }

                case 2: {
                    ui.displayMsg("Login");
                    String username = ui.promptText("Skriv brugernavn");
                    String password = ui.promptText("Skriv password");
                    userManager.validateUser(username, password);
                    break;
                }

                case 3: {
                    ui.displayMsg("Slet bruger");
                    String username = ui.promptText("Skriv det brugernavn, du vil slette");
                    userManager.deleteUser(username);
                    break;
                }

                case 4: {
                    ui.displayMsg("Vis alle brugere:");
                    if (userManager.getUserData().isEmpty()) {
                        ui.displayMsg("Ingen brugere fundet.");
                    } else {
                        userManager.getUserData().forEach((username, user) -> {
                            ui.displayMsg("Brugernavn: " + username + ", Admin: " + (user.isAdmin() ? "Ja" : "Nej"));
                        });
                    }
                    break;
                }

                case 5: {
                    ui.displayMsg("Luk programmet ned");
                    ui.displayMsg("Lukker ned...");
                    running = false;
                    break;
                }

                default: {
                    ui.displayMsg("Forkert input. Prøv igen");
                }
            }

        }
        scanner.close();
    }

    public void runMenu() {
        Menu menu = new Menu();
        menu.displayMenu();
    }
}
