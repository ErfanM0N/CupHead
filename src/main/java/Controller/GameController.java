package Controller;

import application.App;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Boss;
import model.CupHead;
import model.Game;
import model.MiniBoss;
import transitions.BossTransition;
import transitions.FireBullet;
import transitions.MiniBossTransition;

import java.util.Date;

public class GameController {
    private static GameController instance;
    private long lastBulletFireTime = 0;
    private Pane pane;
    private MiniBoss[] miniBosses;
    private Boss boss;
    private CupHead cupHead;
    private Label lives;
    private Label cupHeadLives;
    private Label score;
    private BossTransition bossTransition;
    private MiniBossTransition miniBossTransition;
    private long time;
    private Stage endStage;
    private Label showTime;
    private MediaPlayer mediaPlayer;

    private int pause = 0;

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }

    private GameController() {
    }

    public MiniBoss[] getMiniBosses() {
        return miniBosses;
    }

    public Rectangle getBoss() {
        return boss.getRectangle();
    }

    public CupHead getCupHead() {
        return cupHead;
    }

    public Label getScore() {
        return score;
    }

    public Stage getEndStage() {
        return endStage;
    }

    public Label getShowTime() {
        return showTime;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void initial(Rectangle cupHeadRect, Rectangle bossRect, Pane pane, Label bossLives , Label cupHeadLives, Label cupHeadScore, Label showTime) {
        this.pane = pane;
        this.lives = bossLives;
        this.cupHead = new CupHead(10,cupHeadRect);
        this.cupHeadLives = cupHeadLives;
        this.score = cupHeadScore;
        this.time = new Date().getTime();
        this.showTime = showTime;
        cupHeadRect.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/plane.png").toExternalForm())));
        cupHeadRect.setX(100);
        cupHeadRect.setY(50);
        cupHeadRect.requestFocus();
        MiniBossTransition miniBossTransition = new MiniBossTransition(pane);
        miniBosses = miniBossTransition.getMiniBosses();
        this.miniBossTransition = miniBossTransition;
        miniBossTransition.play();
        bossRect.setX(900);
        bossRect.setY(400);
        this.boss = new Boss(50,bossRect);
        lives.setText(String.valueOf(boss.getLives()));
        cupHeadLives.setText(String.valueOf(cupHead.getLives()));
        score.setText(String.valueOf(cupHead.getScore()));
        BossTransition bossTransition = new BossTransition(this.boss,pane,time,showTime);
        this.bossTransition = bossTransition;
        bossTransition.play();
        Media media = new Media(this.getClass().getResource("/music/backGround.mp3").toExternalForm());
        this.mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(-1);
        mediaPlayer.play();
    }
    public void movePlaneAndFire(KeyEvent key, Rectangle cupHead, Pane pane){
        String direction = key.getCode().getName();
        switch (direction) {
            case "Right":
                if (cupHead.getX() + 20 + 150 < 1280)
                    cupHead.setX(cupHead.getX() + 20);
                break;
            case "Left":
                if (cupHead.getX() - 20 > 0)
                    cupHead.setX(cupHead.getX() - 20);
                break;
            case "Up":
                if (cupHead.getY() - 20 > 0)
                    cupHead.setY(cupHead.getY() - 20);
                break;
            case "Down":
                if (cupHead.getY() + 20 + 120 < 650)
                    cupHead.setY(cupHead.getY() + 20);
                break;
            case "Space":
                fireBullet(cupHead,pane);
                break;
        }
    }

    private void fireBullet(Rectangle cupHead,Pane pane) {
        if (this.lastBulletFireTime + 500 > new Date().getTime())
            return;
        Rectangle bullet = new Rectangle();
        bullet.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/bullet.png").toExternalForm())));
        bullet.setWidth(110);
        bullet.setHeight(30);
        bullet.setX(cupHead.getX() + 120);
        bullet.setY(cupHead.getY() + 60);
        this.lastBulletFireTime = new Date().getTime();
        pane.getChildren().add(bullet);
        FireBullet fireBullet = new FireBullet(bullet);
        fireBullet.play();
    }

    public void removeBulletAndMiniBoss(Rectangle bullet,int i) {
        pane.getChildren().remove(bullet);
        if (miniBosses[i].getLive() == 2)
            miniBosses[i].setLive(1);
        else
            miniBosses[i].getRectangle().setX(-5000);
        cupHead.addScore(2);
        score.setText(String.valueOf(cupHead.getScore()));
    }

    public void removeBulletAndDecreaseBossHealth(Rectangle bullet) {
        pane.getChildren().remove(bullet);
        boss.decreaseLives();
        lives.setText(String.valueOf(boss.getLives()));
        cupHead.addScore(2);
        score.setText(String.valueOf(cupHead.getScore()));
    }

    public void removeEggAndDecreaseCupHeadHealth(Rectangle egg) {
        egg.setX(-100);
        this.cupHead.decreaseLives();
        cupHeadLives.setText(String.valueOf(cupHead.getLives()));
        if (this.cupHead.getLives() < 2)
            cupHeadLives.setTextFill(Paint.valueOf("Red"));
    }

    public void removeMiniBossAndDecreaseCupHeadHealth(MiniBoss miniBoss) {
        this.cupHead.decreaseLives();
        cupHeadLives.setText(String.valueOf(cupHead.getLives()));
        if (this.cupHead.getLives() < 4)
            cupHeadLives.setTextFill(Paint.valueOf("Red"));
        miniBoss.getRectangle().setX(-5000);

    }

    public void DecreaseCupHeadAndBossHealth() {
        this.cupHead.decreaseLives();
        if (this.cupHead.getLives() < 2)
            cupHeadLives.setTextFill(Paint.valueOf("Red"));
        cupHeadLives.setText(String.valueOf(cupHead.getLives()));
        this.boss.decreaseLives();
        lives.setText(String.valueOf(boss.getLives()));
    }

    public void endGame() {
        bossTransition.pause();
        miniBossTransition.pause();
        mediaPlayer.pause();
        Stage stage = new Stage();
        this.endStage = stage;
        Parent root = App.loadFXML("EndPage");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("End");
        stage.setResizable(false);
        Game.getInstance().getLoginUser().setScore(Game.getInstance().getLoginUser().getScore() + Integer.parseInt(score.getText()));
        stage.show();
    }

    public void pause() {
        if (this.pause == 0) {
            bossTransition.pause();
            miniBossTransition.pause();
            mediaPlayer.pause();
            pause = 1;
        }
        else {
            bossTransition.play();
            miniBossTransition.play();
            mediaPlayer.play();
            pause = 0;
        }
    }
}
