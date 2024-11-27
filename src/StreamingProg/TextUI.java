package StreamingProg;

import java.util.Scanner;

public class TextUI {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMsg(String message) {
        System.out.println(message);
    }

    public String promptText(String message) {
        displayMsg(message);
        return scanner.nextLine().trim();
    }

    public boolean promptBinary(String message) {
        while (true) {
            String input = promptText(message + " (Y/N):");
            if (input.equalsIgnoreCase("Y")) return true;
            if (input.equalsIgnoreCase("N")) return false;
            displayMsg("Ugyldigt input. Prøv igen.");
        }
    }
}
