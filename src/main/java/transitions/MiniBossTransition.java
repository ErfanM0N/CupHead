package transitions;

import Controller.GameController;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.MiniBoss;

import java.util.Date;

public class MiniBossTransition extends Transition {
    private Pane pane;
    private MiniBoss[] miniBosses = new MiniBoss[6];
    private int count = 0;
    private long time;

    private Rectangle cupHead;
    public MiniBossTransition(Pane pane) {
        this.pane = pane;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
        createMiniBosses();
        time = new Date().getTime();
        cupHead = GameController.getInstance().getCupHead().getRectangle();
    }

    @Override
    protected void interpolate(double frac) {
        count++;
        if (count % 7 > 0) {
            return;
        }
        count = 0;
        for (int i = 0; i < 6; i++) {
            miniBosses[i].getRectangle().setX(miniBosses[i].getRectangle().getX() - 15);
            miniBosses[i].increaseCount();
            setImage(miniBosses[i]);
            if (cupHead.getBoundsInParent().intersects(miniBosses[i].getRectangle().getLayoutBounds()))
                GameController.getInstance().removeMiniBossAndDecreaseCupHeadHealth(miniBosses[i]);

        }
        if (time + 27000 < new Date().getTime()) {
            reset();
            time = new Date().getTime();
        }
    }
    private void reset() {
        for (int i = 0; i < 6; i++) {
            if (i < 3)
                miniBosses[i].getRectangle().setX(1400 + 100 * i);
            else
                miniBosses[i].getRectangle().setX(2800 + 100 * i);
            miniBosses[i].setLive(2);
        }
    }
    private void createMiniBosses() {
        for (int i = 0; i < 6; i++) {
            Rectangle rectangle = new Rectangle();
            if (i < 3) {
                miniBosses[i] = new MiniBoss(i + 1, 2, "yellow");
                rectangle.setX(1400 + 100 * i);
            }
            else {
                miniBosses[i] = new MiniBoss(i - 2, 2, "purple");
                rectangle.setX(2800 + 100 * i);
            }
            rectangle.setY(100);
            rectangle.setWidth(100);
            rectangle.setHeight(60);
            miniBosses[i].setRectangle(rectangle);
            setImage(miniBosses[i]);
            this.pane.getChildren().add(rectangle);
        }
    }

    private void setImage(MiniBoss miniBoss) {
        miniBoss.getRectangle().setFill(new ImagePattern(new Image(this.getClass().
                getResource("/image/" + miniBoss.getColor() + "/" + miniBoss.getCount() + ".png").toExternalForm())));
    }

    public MiniBoss[] getMiniBosses() {
        return miniBosses;
    }
}
