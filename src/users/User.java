package users;

import java.io.*;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Users.txt.txt")))) {
            try {
                writer.write(username + ": " + password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
