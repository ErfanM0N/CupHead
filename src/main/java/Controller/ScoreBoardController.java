package Controller;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Game;
import model.User;

import java.awt.*;
import java.util.ArrayList;

public class ScoreBoardController {
    public static void showPlayers(VBox vbox,VBox vboxScore) {
        ArrayList<User> users = Game.getInstance().getUsers();
        sort(users);

        int count = 0;
        for (User user : users) {
            if (count < 10) {
                Label label = new Label(user.getUsername());
                Label score = new Label(String.valueOf(user.getScore()));
                switch (count) {
                    case 0:
                        label.setStyle("-fx-font-size: 25 ; -fx-text-fill:  #fafa01");
                        score.setStyle("-fx-font-size: 25 ; -fx-text-fill:  #fafa01");
                        break;
                    case 1:
                        label.setStyle("-fx-font-size: 25 ; -fx-text-fill:  #969595");
                        score.setStyle("-fx-font-size: 25 ; -fx-text-fill:  #969595");
                        break;
                    case 2:
                        label.setStyle("-fx-font-size: 25 ; -fx-text-fill:  #b94f03");
                        score.setStyle("-fx-font-size: 25 ; -fx-text-fill:  #b94f03");
                        break;
                    default:
                        label.setStyle("-fx-font-size: 25 ; -fx-text-fill:  #000000");
                        score.setStyle("-fx-font-size: 25 ; -fx-text-fill:  #000000");
                        break;
                }
                vbox.getChildren().add(label);
                vboxScore.getChildren().add(score);
                count++;
            }
        }

    }

    private static void sort(ArrayList<User> users) {
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (users.get(i).getScore() < users.get(j).getScore()) {
                    User user = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, user);
                }
            }
        }

    }
}
