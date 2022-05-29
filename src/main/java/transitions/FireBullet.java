package transitions;

import Controller.GameController;
import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.MiniBoss;

public class FireBullet extends Transition {
    private Rectangle bullet;
    public FireBullet(Rectangle bullet) {
        this.bullet = bullet;
        setCycleDuration(Duration.millis(3500));
        setCycleCount(1);
        Media media = new Media(this.getClass().getResource("/music/shootEffect.mp3").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(1);
        mediaPlayer.play();
    }

    @Override
    protected void interpolate(double frac) {
        this.bullet.setX(bullet.getX() + 7);
        MiniBoss[] miniBosses = GameController.getInstance().getMiniBosses();
        Rectangle boss = GameController.getInstance().getBoss();
        for (int i = 0; i < 6; i++) {
            if (miniBosses[i].getRectangle().getBoundsInParent().intersects(bullet.getLayoutBounds())) {
                GameController.getInstance().removeBulletAndMiniBoss(bullet,i);
                this.stop();
                break;
            }
        }
        if (boss.getBoundsInParent().intersects(bullet.getLayoutBounds())) {
            GameController.getInstance().removeBulletAndDecreaseBossHealth(bullet);
            this.stop();
        }

    }
}
