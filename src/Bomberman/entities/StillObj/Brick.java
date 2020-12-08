package Bomberman.entities.StillObj;

import Bomberman.BombermanGame;
import javafx.scene.image.Image;
import Bomberman.graphics.Sprite;

public class Brick extends StillEntity {
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(3);
        alive = true;
    }

    @Override
    public void update() {
        if(!isAlive()){
            if(die < 40) {
                die++;
                img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1
                        , Sprite.brick_exploded2, die, 30).getFxImage();
            } else
                BombermanGame.stillObjects.remove(this);
        }
    }
}
