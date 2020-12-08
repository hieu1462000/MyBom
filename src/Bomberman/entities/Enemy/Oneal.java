package Bomberman.entities.Enemy;

import javafx.scene.image.Image;
import Bomberman.BombermanGame;
import Bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemy {
    private int move;

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(2);
        Move();
        alive = true;
    }

    @Override
    public void update() {
        if(isAlive())
        {
            if (move == 0) goLeft();
            if (move == 1) goRight();
            if (move == 2) goDown();
            if (move == 3) goUp();
        }

        else if(die < 30){
            die ++;
            img = Sprite.movingSprite(Sprite.oneal_dead, Sprite.mob_dead2,
                    Sprite.mob_dead3, die, 30).getFxImage();
        }else
            BombermanGame.enemies.remove(this);
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, moveleft++, 15).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_left3, moveright++, 15).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, moveleft++, 15).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_left3, moveright++, 15).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        Move();
    }

    public void Move() {
        Random random = new Random();
        move = random.nextInt(4);
    }
}
