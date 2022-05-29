package model;

import javafx.scene.shape.Rectangle;


public class CupHead {
    private int lives;

    private int score;
    private Rectangle rectangle;

    public CupHead(int lives, Rectangle rectangle) {
        this.lives = lives;
        this.rectangle = rectangle;
        this.score = 0;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void decreaseLives() {
        this.lives--;
    }
}
