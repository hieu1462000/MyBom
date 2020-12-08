package Bomberman.entities.PowerItem;

import javafx.scene.image.Image;
import Bomberman.entities.StillObj.StillEntity;

public abstract class Item extends StillEntity {
    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
    }
}
