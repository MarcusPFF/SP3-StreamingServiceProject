package StreamingProg;

import java.util.List;
import java.util.Scanner;

public class Netflix {
    //Initialising final attributes
    private final UserManager um;
    private final TextUI ui;
    private final FileIO io;
    private User user;

    //Constructor
    public Netflix() {
        this.um = new UserManager();
        this.ui = new TextUI();
        this.io = new FileIO();
        this.user = user;
    }

    /*The runApplication method is the single method called in main.java.
    It acts as the entry point for our streaming service program by invoking
    other methods to ensure the application runs properly.
     */
    public void runApplication() {
        ui.displayMsg("Velkommen til netflix-backend streamingtjeneste systemet");
        List<User> users = io.loadUserData("data/userData/userData.csv");
        um.setUserData(users);
        runUserManager();
    }

    /* The runUserManager method provides a menu-driven user interface for managing users.
    It runs a switch case for each action the user can do and calls a method that's under that case.
     */
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

    /*
    The createUser method allows creating a new user with a validated username and password.
    It includes an option to assign admin status if a correct admin code is entered within three attempts.
    The new user is saved to the system and persisted in a CSV file.
    */
    private void createUser() {
        ui.displayMsg("Lav ny bruger");
        String username = ui.promptText("Skriv dit nye brugernavn:");

        // Validating username
        while (username.length() < 3 || username.length() > 16 || isUsernameTaken(username)) {
            if (username.length() < 3 || username.length() > 16) {
                ui.displayMsg("Dit brugernavn skal være mellem 3 og 16 tegn. Prøv igen.");
            } else {
                ui.displayMsg("Dette brugernavn er optaget. Prøv et andet.");
            }
            username = ui.promptText("Skriv dit nye brugernavn:");
        }


        // Validating password
        String password = ui.promptText("Skriv dit nye password:");
        while (!isValidPassword(password)) {
            ui.displayMsg("Password skal være mindst 8 tegn langt, med mindst 1 stort bogstav og 1 tal. Prøv igen.");
            password = ui.promptText("Skriv dit nye password:");

        }
        makeAdmin();
        User newUser = new User(username, password, user.validate);
        um.createUser(newUser);

        io.saveUserData("data/userData/userData.csv", um.getUserData());
        ui.displayMsg("Ny bruger oprettet: " + newUser);
    }

    public boolean makeAdmin() {
        // Admin-check
        boolean isAdmin = ui.promptBinary("Skal brugeren være admin?");
        if (isAdmin == true) {
            for (int i = 0; i < 3; i++) {
                String adminCode = ui.promptText("Indtast admin-kode for at blive admin:");
                if (adminCode.equals("Admin12!")) {
                    ui.displayMsg("Du er nu admin.");
                    user.setValidate(true);
                    return user.validate = true;
                } else {
                    ui.displayMsg("Forkert kode, prøv igen. Du har nu " + (2 - i) + " forsøg tilbage");
                    return user.validate = false;
                }
            }
            if (isAdmin == false) {
                ui.displayMsg("Du har brugt alle forsøg. Du er ikke admin.");
                user.setValidate(false);


            }
            return user.validate = false;
        } else {
            ui.displayMsg("Brugeren er ikke admin");
            return user.validate = false;
        }
    }



    /*
    The loginUser method prompts for a username and password.
    If the credentials are valid, it grants access to the user's menu.
    Otherwise, it displays an error message for failed login attempts.
    */
    private void loginUser() {
        ui.displayMsg("Login");
        String username = ui.promptText("Skriv brugernavn:");
        String password = ui.promptText("Skriv password:");
        List<User> users = io.loadUserData("data/userData/userData.csv");
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password) && user.isValidate() == true) {
                user.setValidate(true);
            } else if (user.getUsername().equals(username) && user.getPassword().equals(password) && user.isValidate() == false) {
                user.setValidate(false);
            }

        }
        if (um.validateUser(username, password)) {
            Menu menu = new Menu(username);

            menu.displayMenu();
        } else {
            ui.displayMsg("Login mislykkedes. Prøv igen.");
        }
    }

    /*
    The deleteUser method allows deleting a user by username.
    If the user is found and deleted, the updated data is saved.
    Otherwise, it displays a message indicating the user was not found.
    */
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

    /*
    The viewUsers method displays a list of all users.
    If no users are found, it shows a corresponding message.
    Otherwise, it iterates through and displays each user's details.
    */
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

    /*
    The isValidPassword method checks if a password meets the required criteria.
    It ensures the password is at least 8 characters long, contains at least one uppercase letter,
    and includes at least one number. It returns true if all conditions are met, otherwise false.
    */
    private boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUppercase = false;
        boolean hasNumber = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (hasUppercase && hasNumber) {
                return true;
            }
        }
        return false;
    }

    /*
    The isUsernameTaken method checks if a given username is already in use.
    It iterates through the list of users and compares each user's username with the input.
    If a match is found, it returns true; otherwise, it returns false.
    */
    private boolean isUsernameTaken(String username) {
        for (User user : um.getUserData()) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
