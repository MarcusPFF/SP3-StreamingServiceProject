package StreamingProg;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {
    public List<User> loadUserData(String filePath) {
        List<User> users = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Brugerdatafil ikke fundet: " + filePath);
            return users;
        }

        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine(); // Skip header
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 3) {
                    String username = data[0].trim();
                    String password = data[1].trim();
                    boolean isAdmin = Boolean.parseBoolean(data[2].trim());
                    users.add(new User(username, password, isAdmin));
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning af brugerdata: " + e.getMessage());
        }
        return users;
    }

    public void saveUserData(String filePath, List<User> users) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Username,Password,isAdmin\n");
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.isAdmin() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning af brugerdata: " + e.getMessage());
        }
    }
}
