package Controller;

import application.App;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.util.Duration;
import model.Game;
import model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class SignupController {

    public static User isExistUser(String username) {
        ArrayList<User> users = Game.getInstance().getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static void addUser(String username, String password) {
        Random random = new Random(new Date().getTime() % 1000);
        User user = new User(username, password, random.nextInt(3) + 1);
        Game.getInstance().addUser(user);
    }

    public static void changeMenuToMain(User user) {
        Game.getInstance().setLoginUser(user);
        App.changeMenu("MainPage");
    }

    public static void playAsGuest() {
        Random random = new Random(new Date().getTime() % 1000);
        User guest = new User("Guest", "1234", random.nextInt(3) + 1);
        changeMenuToMain(guest);
    }

    public static void initial(FadeTransition messageTransition, Label message) {
        messageTransition.setDuration(Duration.millis(3000));
        messageTransition.setFromValue(1);
        messageTransition.setToValue(0);
        messageTransition.setNode(message);
        readUserInfo();
    }
    private static void readUserInfo() {
        try {
            String info = new String(Files.readAllBytes(Paths.get("users.txt")));
            Game.getInstance().setUsers(new Gson().fromJson(info, new TypeToken<List<User>>(){}.getType()));
            if (Game.getInstance().getUsers() == null)
                Game.getInstance().setUsers(new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeUserInfo() {
        try {
            FileWriter fileWriter = new FileWriter("users.txt");
            fileWriter.write(new Gson().toJson(Game.getInstance().getUsers()));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
