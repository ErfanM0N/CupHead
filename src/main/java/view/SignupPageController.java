package view;

import Controller.SignupController;
import application.App;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.Game;
import model.User;

public class SignupPageController {
    @FXML
    private Label message;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    private FadeTransition messageTransition = new FadeTransition();

    public void initialize() {
        SignupController.initial(messageTransition,message);
    }
    public void signup(MouseEvent mouseEvent) {
        if (username.getText().length() < 4)
            message.setText("Error | Invalid Username");
        else if (password.getText().length() < 8)
            message.setText("Error | Password is Weak");
        else if (SignupController.isExistUser(username.getText()) != null)
            message.setText("Error | Username is Exist");
        else {
            SignupController.addUser(username.getText(),password.getText());
            message.setText("Success | User created");
        }
        username.clear();
        password.clear();
        messageTransition.play();
    }

    public void login(MouseEvent mouseEvent) {
        User user;
        if ((user = SignupController.isExistUser(username.getText())) == null)
            message.setText("Error | please Register First");
        else if (!user.getPassword().equals(password.getText()))
            message.setText("Error | Incorrect Password");
        else {
            SignupController.changeMenuToMain(user);
        }
        messageTransition.play();
    }

    public void playAsGuest(MouseEvent mouseEvent) {
        SignupController.playAsGuest();
    }

}
