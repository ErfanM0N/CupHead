package view;

import Controller.GameController;
import Controller.PlaneController;
import application.App;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Game;
import transitions.BossTransition;
import transitions.MiniBossTransition;

import java.applet.AudioClip;

public class GamePageController {
    @FXML
    private Rectangle backGround;
    @FXML
    private Rectangle backGround2;
    @FXML
    private Label time;
    @FXML
    private Label cupHeadScore;
    @FXML
    private Label cupHeadLives;
    @FXML
    private Label lives;
    @FXML
    private Rectangle bossRect;
    @FXML
    private Pane pane;
    @FXML
    private Rectangle cupHead;

    private int mute = 0;

    public void initialize() {
        backGround.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/backGround.png").toExternalForm())));
        backGround2.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/backGround2.png").toExternalForm())));
        GameController.getInstance().initial(cupHead, bossRect, pane, lives, cupHeadLives, cupHeadScore, time);
    }
    public void moveAndFire(KeyEvent keyEvent) {
        GameController.getInstance().movePlaneAndFire(keyEvent,cupHead,pane);
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        App.changeMenu("MainPage");
        GameController.getInstance().getMediaPlayer().stop();
    }

    public void restart(MouseEvent mouseEvent) {
        App.changeMenu("GamePage");
        GameController.getInstance().getMediaPlayer().stop();
    }

    public void pause(MouseEvent mouseEvent) {
        GameController.getInstance().pause();
    }

    public void mute(MouseEvent mouseEvent) {
        MediaPlayer mediaPlayer = GameController.getInstance().getMediaPlayer();
        mediaPlayer.setMute(!mediaPlayer.isMute());

    }
}
