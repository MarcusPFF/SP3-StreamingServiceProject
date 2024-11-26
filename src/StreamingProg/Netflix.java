package StreamingProg;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Netflix {

    private UserManager um;
    private Menu menu;
    private TextUI ui;
    private FileIO io;
    private HashMap<String, User> userData;

    public Netflix() {
        this.um = new UserManager();
        this.menu = new Menu();
        this.ui = new TextUI();
        this.io = new FileIO();
        this.userData = new HashMap<>();
    }

    public void runApplication() {
        ui.displayMsg("Velkommen til netflix-backend streamingtjeneste systemet");
        // runMovieLoader();
         runUserManager();
        //runMenu();
    }

    public void runMovieLoader() {


        io.readMovieData(io.getMoviesDataPath());
        for (String movie : io.getMoviesList()) {
            System.out.println(movie);
        }
    }


    public void runUserManager() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            ui.displayMsg("--Vælg en mulighed--");
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
                    um.createUser(username, password, isAdmin);
                    io.saveUserToFile(userData);
                    break;
                }

                case 2: {
                    ui.displayMsg("Login");
                    String username = ui.promptText("Skriv brugernavn");
                    String password = ui.promptText("Skriv password");
                    um.validateUser(username, password);
                    break;
                }

                case 3: {
                    ui.displayMsg("Slet bruger");
                    String username = ui.promptText("Skriv det brugernavn, du vil slette");
                    um.deleteUser(username);
                    break;
                }

                case 4: {
                    ui.displayMsg("Vis alle brugere:");
                    if (um.getUserData().isEmpty()) {
                        ui.displayMsg("Ingen brugere fundet.");
                    } else {
                        um.getUserData().forEach((username, user) -> {
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
