package StreamingProg;

import java.util.ArrayList;
import java.util.Scanner;


public class TextUI {
    Scanner scanner = new Scanner(System.in);


    public void displayMsg(String s) {
        System.out.println(s);
    }

    public String promptText(String msg) {
        System.out.println(msg); //Stille brugeren et spørgsmål
        String input = scanner.nextLine();
        return input;
    }

    public int promptNumeric(String msg) {
        System.out.println(msg);              // Stille brugeren et spørgsmål
        String input = scanner.nextLine();
        int number;
        // Give brugere et sted at placere sit svar og vente på svaret
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            displayMsg("Please type a number");
            number = promptNumeric(msg);
        }
        return number;
    }

    public ArrayList<String> promptChoice(String msg, ArrayList<String> list) {
        ArrayList<String> choice = new ArrayList<String>();  //Lave en beholder til at gemme brugerens valg
        return list;
    }

    public boolean promptBinary(String msg) {
        String input = promptText(msg);
        if (input.equalsIgnoreCase("Y")) {
            return true;
        } else if (input.equalsIgnoreCase("N")) {
            return false;
        }
        return promptBinary(msg);
    }
}





