package model;

import javafx.scene.shape.Rectangle;

public class MiniBoss {
    private int count;
    private Rectangle rectangle ;
    private int live;

    private final String color;

    public MiniBoss(int count, int live, String color) {
        this.count = count;
        this.live = live;
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public int getLive() {
        return live;
    }

    public String getColor() {
        return color;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public void setLive(int live) {
        this.live = live;
    }

    public void increaseCount(){
        this.count++;
        if (count == 5)
            count = 1;
    }
}
