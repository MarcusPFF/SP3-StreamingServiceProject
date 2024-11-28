package StreamingProg;

import java.util.List;
import java.util.Scanner;

public class Netflix {
    private final UserManager um;
    private final TextUI ui;
    private final FileIO io;

    public Netflix() {
        this.um = new UserManager();
        this.ui = new TextUI();
        this.io = new FileIO();
    }

    public void runApplication() {
        ui.displayMsg("Velkommen til netflix-backend streamingtjeneste systemet");
        List<User> users = io.loadUserData("data/userData/userData.csv");
        um.setUserData(users);
        runUserManager();
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


            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ui.displayMsg("Forkert input. Indtast et tal mellem 1-5.");
                continue;
            }

            switch (choice) {
                case 1 -> createUser();
                case 2 -> loginUser();
                case 3 -> deleteUser();
                case 4 -> viewUsers();
                case 5 -> {
                    ui.displayMsg("Lukker programmet ned...");
                    running = false;
                }
                default -> ui.displayMsg("Forkert input. Indtast et tal mellem 1-5.");
            }
        }
    }

    private void createUser() {
        ui.displayMsg("Lav ny bruger");
        String username = ui.promptText("Skriv dit brugernavn:");
        String password = ui.promptText("Skriv password:");
        boolean isAdmin = ui.promptBinary("Skal brugeren være admin?");

        User newUser = new User(username, password, isAdmin);
        um.createUser(newUser);
        io.saveUserData("data/userData/userData.csv", um.getUserData());
        ui.displayMsg("Ny bruger oprettet: " + newUser);
    }

    private void loginUser() {
        ui.displayMsg("Login");
        String username = ui.promptText("Skriv brugernavn:");
        String password = ui.promptText("Skriv password:");

        if (um.validateUser(username, password)) {
            Menu menu = new Menu(username);
            menu.displayMenu();
        } else {
            ui.displayMsg("Login mislykkedes. Prøv igen.");
        }
    }


    private void deleteUser() {
        ui.displayMsg("Slet bruger");
        String username = ui.promptText("Skriv brugernavn på brugeren, der skal slettes:");

        if (um.deleteUser(username)) {
            io.saveUserData("data/userData/userData.csv", um.getUserData());
            ui.displayMsg("Brugeren blev slettet.");
        } else {
            ui.displayMsg("Brugeren blev ikke fundet.");
        }
    }

    private void viewUsers() {
        ui.displayMsg("Vis alle brugere:");
        List<User> users = um.getUserData();
        if (users.isEmpty()) {
            ui.displayMsg("Ingen brugere fundet.");
        } else {
            for (User user : users) {
                ui.displayMsg(user.toString());
            }
        }
    }
}
