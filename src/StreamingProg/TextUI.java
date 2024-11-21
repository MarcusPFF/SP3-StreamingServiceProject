package StreamingProg;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    Scanner scanner;

    TextUI() {
        scanner = new Scanner(System.in);
    }

    public void displayMsg(String s) {

    }

    public String promptText(String msg) {
        return msg;
    }

    public int promptNumeric(String msg) {
        return Integer.parseInt(msg);
    }

    public int promptChoice(String msg, ArrayList<String> List) {
        return 0;
    }

    public boolean promptBinary(String msg) {
        return false;
    }
}
