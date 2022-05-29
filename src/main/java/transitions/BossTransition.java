package transitions;

import Controller.GameController;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.Boss;
import model.MiniBoss;
import java.util.Date;

public class BossTransition extends Transition {
    private Boss boss;
    private Pane pane;
    private int count;
    private int imageNumber;
    private Rectangle egg;
    private int bossSpeed = -7;
    private long startedTime;
    private Label showTime;
    private long crashTime;
    public BossTransition(Boss boss, Pane pane,long time,Label showTime) {
        this.boss = boss;
        this.pane = pane;
        this.startedTime = time;
        count = 0;
        this.imageNumber = 1;
        this.showTime = showTime;
        this.crashTime = new Date().getTime();
        setImage();
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
        egg = new Rectangle();
        egg.setFill(new ImagePattern(new Image(this.getClass().getResource("/image/egg.png").toExternalForm())));
        pane.getChildren().add(egg);
        egg.setWidth(50);
        egg.setHeight(50);
        egg.setX(-100);
        egg.setY(490);
    }

    @Override
    protected void interpolate(double frac) {
        setTime();
        GameController.getInstance().getCupHead().getRectangle().requestFocus();
        if (crashTime + 1000 < new Date().getTime() &&
                boss.getRectangle().getBoundsInParent().intersects
                        (GameController.getInstance().getCupHead().getRectangle().getLayoutBounds())) {
            GameController.getInstance().DecreaseCupHeadAndBossHealth();
            crashTime = new Date().getTime();
        }
        if (GameController.getInstance().getCupHead().getLives() == 0 || boss.getLives() == 0)
            GameController.getInstance().endGame();
        count++;
        egg.setX(egg.getX() - 9);
        if (egg.getBoundsInParent().intersects(GameController.getInstance().getCupHead().getRectangle().getLayoutBounds())) {
            GameController.getInstance().removeEggAndDecreaseCupHeadHealth(egg);
        }
        if (count % 10 > 0)
            return;
        count = 0;
        imageNumber++;
        boss.getRectangle().setY(boss.getRectangle().getY() + bossSpeed);
        if (boss.getRectangle().getY() < 10 || boss.getRectangle().getY() > 450)
            bossSpeed *= -1;
        if (imageNumber == 13)
            imageNumber = 1;
        if (imageNumber == 5) {
            egg.setX(900);
            egg.setY(boss.getRectangle().getY() + 80);
        }
        setImage();
    }

    private void setImage() {
        boss.getRectangle().setFill(new ImagePattern(new Image(this.getClass().
                getResource("/image/BossShoot/" + imageNumber + ".png").toExternalForm())));
    }

    private void setTime() {
        long time = new Date().getTime();
        time -= this.startedTime;
        time /= 1000;
        int second = (int) (time % 60);
        String sec;
        if (second > 9)
            sec = String.valueOf(second);
        else
            sec = "0" + String.valueOf(second);

        int minute = (int) (time / 60);
        String min;
        if (minute > 9)
            min = String.valueOf(minute);
        else
            min = "0" + String.valueOf(minute);

        showTime.setText(min + ":" + sec);
    }

}
