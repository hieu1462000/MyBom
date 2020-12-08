package Bomberman.entities.StillObj;

import Bomberman.entities.Entity;
import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        setLayer(4);
    }

    @Override
    public void update() {

    }
}
