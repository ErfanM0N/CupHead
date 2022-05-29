package view;

import Controller.SignupController;
import application.App;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;

public class MainPageController {
    @FXML
    private Rectangle avatar;

    public void initialize() {
        avatar.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/Avatars/"+
                Game.getInstance().getLoginUser().getAvatarNumber() +".png").toExternalForm())));
        SignupController.writeUserInfo();
    }
    public void startGame(MouseEvent mouseEvent) {
        App.changeMenu("GamePage");
    }

    public void continueLastGame(MouseEvent mouseEvent) {
    }

    public void profile(MouseEvent mouseEvent) {
        App.changeMenu("ProfilePage");
    }

    public void showScoreBoard(MouseEvent mouseEvent) {
        App.changeMenu("ScoreBoardPage");
    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
