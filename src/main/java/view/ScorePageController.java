package view;

import Controller.ScoreBoardController;
import application.App;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ScorePageController {
    @FXML
    private VBox vboxScore;
    @FXML
    private VBox vbox;

    public void initialize() {
        ScoreBoardController.showPlayers(vbox,vboxScore);
    }
    public void back(MouseEvent mouseEvent) {
        App.changeMenu("MainPage");
    }
}
