import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Utils {
    public static boolean login(String username, String password) {
        return true;
    }

    public static boolean userExists(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(username.equals(line.substring(0, line.indexOf(':'))) && password.equals(line.substring(line.indexOf(':') + 2))) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createUser() {
        String username, password;
        boolean isCreated = false;
        Scanner input = new Scanner(System.in);
        while (!isCreated) {
            System.out.println("Enter Username: ");
            username = input.nextLine();
            System.out.println("Enter Password: ");
            password = input.nextLine();
            if (!userExists(username, password)) {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("Users.txt", true), StandardCharsets.UTF_8))) {
                    writer.newLine();
                    writer.append(username).append(": ").append(password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isCreated = true;
            } else {
                System.out.println("User already exists!!!");
            }
        }
    }
}
