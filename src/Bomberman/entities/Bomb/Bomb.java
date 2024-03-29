package Bomberman.entities.Bomb;

import Bomberman.Sound.Sound;
import Bomberman.entities.Animation;
import Bomberman.entities.Entity;
import javafx.scene.image.Image;
import Bomberman.entities.Player.Bomber;
import Bomberman.graphics.Sprite;

import java.awt.*;

public class Bomb extends Animation {
    private int timeCounter = 0;
    int radius;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = 1;
    }

    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = radius;
    }
    @Override
    public void update() {
        if (timeCounter ++ == 120) {
            Sound.initial();
            Sound.playExplosion();
            Explode();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeCounter, 60).getFxImage();
    }

    public void Explode() {
        Flame e = new Flame(x, y);
        e.setRadius(radius);
        e.render_explosion();
        alive = false;
    }


    public boolean isAllowedToPassThrough(Entity e) {
        Rectangle r1 = getBounds();
        Rectangle r2;
        if (e instanceof Bomber) {
            Bomber bomber = (Bomber) e;
            r2 = new Rectangle(bomber.getX() + 4, bomber.getY() + 4, Sprite.SCALED_SIZE * 3 / 4, Sprite.SCALED_SIZE * 3 / 4);
        } else {
            r2 = new Rectangle(e.getX(), e.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        }
        return r1.intersects(r2);
    }
}