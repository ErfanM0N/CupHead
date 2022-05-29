package view;

import Controller.ProfileController;
import Controller.SignupController;
import application.App;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;

public class ProfilePageController {
    @FXML
    private Rectangle avatar1;
    @FXML
    private Rectangle avatar2;
    @FXML
    private Rectangle avatar3;
    @FXML
    private Rectangle avatar;
    @FXML
    private Label message;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    private FadeTransition messageTransition = new FadeTransition();

    public void initialize() {
        avatar.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/Avatars/"+
                Game.getInstance().getLoginUser().getAvatarNumber() +".png").toExternalForm())));
        SignupController.initial(messageTransition,message);
        avatar1.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/Avatars/1.png").toExternalForm())));
        avatar2.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/Avatars/2.png").toExternalForm())));
        avatar3.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/Avatars/3.png").toExternalForm())));
    }
    public void changeUsername(MouseEvent mouseEvent) {
        if (username.getText().length() < 4)
            message.setText("Error | Invalid Username");
        else if (SignupController.isExistUser(username.getText()) != null)
            message.setText("Error | Username is Exist");
        else {
            Game.getInstance().getLoginUser().setUsername(username.getText());
            message.setText("Success | Username Changed");
        }
        username.clear();
        messageTransition.play();
    }

    public void changePassword(MouseEvent mouseEvent) {
        if (password.getText().length() < 8)
            message.setText("Error | Password is Weak");
        else {
            Game.getInstance().getLoginUser().setPassword(password.getText());
            message.setText("Success | Password Changed");
        }
        password.clear();
        messageTransition.play();
    }

    public void logout(MouseEvent mouseEvent) {
        ProfileController.logout();
    }

    public void deleteAccount(MouseEvent mouseEvent) {
        Game.getInstance().removeUser(Game.getInstance().getLoginUser());
        ProfileController.logout();
    }

    public void back(MouseEvent mouseEvent) {
        App.changeMenu("MainPage");
    }

    public void changeAvatar1(MouseEvent mouseEvent) {
        Game.getInstance().getLoginUser().setAvatarNumber(1);
        avatar.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/Avatars/1.png").toExternalForm())));
    }

    public void changeAvatar2(MouseEvent mouseEvent) {
        Game.getInstance().getLoginUser().setAvatarNumber(2);
        avatar.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/Avatars/2.png").toExternalForm())));
    }

    public void changeAvatar3(MouseEvent mouseEvent) {
        Game.getInstance().getLoginUser().setAvatarNumber(3);
        avatar.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/Avatars/3.png").toExternalForm())));
    }
}
