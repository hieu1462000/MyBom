package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class Bomber extends Entity {
    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }
    public void MoveLeft() {
            x -= Sprite.DEFAULT_SIZE;
            img = Sprite.player_left_1.getFxImage();
            x -= Sprite.DEFAULT_SIZE;
            img = Sprite.player_left_2.getFxImage();

    }

    public void MoveRight() {

            x += Sprite.DEFAULT_SIZE * 2;
            img = Sprite.player_right_1.getFxImage();
//            x += Sprite.DEFAULT_SIZE;
//            img = Sprite.player_right_2.getFxImage();

    }

    public void MoveDown() {

            y += Sprite.DEFAULT_SIZE * 2;
            img = Sprite.player_down_1.getFxImage();
//            y += Sprite.DEFAULT_SIZE;
//            img = Sprite.player_down_2.getFxImage();

    }

    public void MoveUp() {

            y -= Sprite.DEFAULT_SIZE*2;
            img = Sprite.player_up_1.getFxImage();
//            y -= Sprite.DEFAULT_SIZE;
//            img = Sprite.player_up_2.getFxImage();

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


}

