package view;

import Controller.GameController;
import application.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.Game;

public class EndPageController {
    @FXML
    private Label time;
    @FXML
    private Label score;

    public void initialize() {
        score.setText(GameController.getInstance().getScore().getText());
        time.setText(GameController.getInstance().getShowTime().getText());
    }

    public void restart(MouseEvent mouseEvent) {
        GameController.getInstance().getEndStage().close();
        App.changeMenu("GamePage");
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        GameController.getInstance().getEndStage().close();
        App.changeMenu("MainPage");
    }
}
