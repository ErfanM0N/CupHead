package model;


import javafx.scene.shape.Rectangle;

public class Boss {
    private int lives;
    private Rectangle rectangle;

    public Boss(int lives, Rectangle rectangle) {
        this.lives = lives;
        this.rectangle = rectangle;
    }

    public int getLives() {
        return lives;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void decreaseLives() {
        this.lives--;
    }
}
