package Bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import Bomberman.graphics.Sprite;

import java.awt.*;


public abstract class Entity {
    protected int x;
    protected int y;
    protected Image img;
    protected int die = 0;
    protected int layer;
    protected boolean alive;

    public Entity( int x, int y, Image img) {
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Rectangle getRec() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public boolean isAlive() {
        return alive;
    }
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();


}
