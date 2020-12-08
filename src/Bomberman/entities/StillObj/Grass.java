package Bomberman.entities.StillObj;

import Bomberman.entities.Entity;
import javafx.scene.image.Image;

public class Grass extends Entity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
        setLayer(0);
    }

    @Override
    public void update() {

    }
}
