package Bomberman.entities.Enemy;

import Bomberman.entities.Animation;
import javafx.scene.image.Image;

public abstract class Enemy extends Animation {
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
    }
}
