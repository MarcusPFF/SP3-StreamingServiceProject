package StreamingProg;

import StreamingProg.FileIO;
import StreamingProg.TextUI;
import StreamingProg.UserManager;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Netflix {
    private UserManager um;
    private Menu menu;
    private TextUI ui;
    private FileIO io;
    private User user;
    private List userData;


    public Netflix() {
        this.um = new UserManager();
        this.menu = new Menu();
        this.ui = new TextUI();
        this.io = new FileIO();
    }

    public void runApplication() {
        ui.displayMsg("Velkommen til netflix-backend streamingtjeneste systemet");
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

            int choice = Integer.parseInt(scanner.nextLine());
            ArrayList<User> userData = new ArrayList<>();
            switch (choice) {
                case 1: { //Gør det her pænt ved at lave en metode i fileIO

                    ui.displayMsg("Lav ny bruger");
                    String username = ui.promptText("Skriv dit brugernavn");
                    String password = ui.promptText("Skriv password");
                    boolean isAdmin = ui.promptBinary("Skal brugeren være admin? Y/N");
                    User newUser = new User(username, password, isAdmin);
                    um.createUser(newUser);

                    if (userData == null) {
                        userData = new ArrayList<>();
                    }
                    userData.add(newUser);

                    try (FileWriter writer = new FileWriter("data/userData/userData.csv")) {
                        String header = "Username, Password, isAdmin";
                        writer.write(header + "\n");

                        for (User user : userData) {
                            String userInfo = user.getUsername() + ", " + user.getPassword() + ", " + user.isAdmin();
                            writer.write(userInfo + "\n");
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong when writing to the file.");
                    }

                    // Save users to file
                    System.out.println(newUser);
                    break;
                }

                case 2: {
                    ui.displayMsg("Login");
                    io.loadUserData("data/userData/userData.csv");
                    String username = ui.promptText("Skriv brugernavn");
                    String password = ui.promptText("Skriv password");
                    boolean answer = um.validateUser(username, password);
                    if (answer == true) {  // Use == for comparison, not =
                        menu.displayMenu();
                    } else if (answer == false) {
                        ui.displayMsg("Fejl ved login");
                        runUserManager();
                    }
                    break;
                }

                case 3: {
                    ui.displayMsg("Slet bruger");
                    String username = ui.promptText("Skriv det brugernavn, du vil slette");
                    um.deleteUser(username);  // Sletter bruger fra listen
                    try (FileWriter writer = new FileWriter("data/userData/userData.csv")) {
                        String header = "Username, Password, isAdmin";
                        writer.write(header + "\n");

                        for (User user : userData) {
                            String userInfo = user.getUsername() + ", " + user.getPassword() + ", " + user.isAdmin();
                            writer.write(userInfo + "\n");
                        }
                    } catch (IOException e) {
                        System.out.println("Something went wrong when writing to the file.");
                    }
                    break;
                }

                case 4: {
                    ui.displayMsg("Vis alle brugere:");
                    io.loadUserData("data/userData/userData.csv");
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
    }
}