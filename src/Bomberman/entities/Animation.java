package Bomberman.entities;

import javafx.scene.image.Image;

import Bomberman.graphics.Sprite;

import java.awt.*;


public abstract class Animation extends Entity {
    protected int X = x;
    protected int Y = y;
    protected int speed;
    protected int moveleft = 0;
    protected int moveright = 0;
    protected int moveup = 0;
    protected int movedown = 0;

    public Animation(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        alive = true;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void goLeft() {
        X = x - speed;
    }

    public void goRight() {
        X = x + speed;
    }

    public void goUp() {
        Y = y - speed;
    }

    public void goDown() {
        Y = y + speed;
    }

    public void move() {
        x = X;
        y = Y;
    }

    public void stay() {
        X = x;
        Y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(X, Y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }



}
