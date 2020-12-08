package Bomberman.entities.Player;

import Bomberman.entities.Animation;
import Bomberman.entities.Bomb.Bomb;
import javafx.scene.image.Image;

import javafx.scene.input.KeyEvent;
import Bomberman.graphics.Sprite;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends Animation {
    private int Bombsexist;
    private boolean placeBombCommand = false;
    private final List<Bomb> bombs = new ArrayList<>();
    private int radius;
    private int power;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        setLayer(1);
        setSpeed(3);
        setBombsexist(1);
        setPower(2);
        setRadius(1);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void update() {
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                Bombsexist++;
            }
        }
        if(!isAlive()) {
            die ++;
            die();
        }
    }

    public void KeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT: {
                goLeft();
                break;
            }
            case RIGHT: {
                goRight();
                break;
            }
            case DOWN: {
                goDown();
                break;
            }
            case UP: {
                goUp();
                break;
            }
            case SPACE:
            {
                placeBombCommand=true;
                placeBomb();
            }

        }
    }

    public void KeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT: {
                img = Sprite.player_left.getFxImage();
                break;
            }
            case RIGHT: {
                img = Sprite.player_right.getFxImage();
                break;
            }
            case DOWN: {
                img = Sprite.player_down.getFxImage();
                break;
            }
            case UP: {
                img = Sprite.player_up.getFxImage();
                break;
            }

        }
    }
    public void goLeft() {
        X = x - speed*3;
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, moveleft++, 10).getFxImage();
    }

    public void goRight() {
        X = x + speed*3;
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, moveright++, 10).getFxImage();
    }

    public void goUp() {
        Y = y - speed*3;
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, moveup++, 10).getFxImage();
    }
    public void goDown() {
        Y = y + speed*3;
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, movedown++, 10).getFxImage();
    }

    public void placeBomb() {
        if (Bombsexist > 0) {
            int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);
            int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);
            for (Bomb bomb : bombs) {
                if (xB * Sprite.SCALED_SIZE == bomb.getX() && yB * Sprite.SCALED_SIZE == bomb.getY()) return;
            }
            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage(), radius));
            Bombsexist--;
        }
    }

    public void setBombsexist(int bombsexist) {
        Bombsexist = bombsexist;
    }

    public int getBombsexist() {
        return Bombsexist;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        if(die <= 45) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, die, 20).getFxImage();
        }

    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Rectangle getBounds() {
        return new Rectangle(X + 4, Y + 4, Sprite.SCALED_SIZE - 12, Sprite.SCALED_SIZE * 3 / 4);
    }


}
