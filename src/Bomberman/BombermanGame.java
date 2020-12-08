package Bomberman;

import Bomberman.Sound.Sound;
import Bomberman.entities.Enemy.Balloon;
import Bomberman.entities.Enemy.Doll;
import Bomberman.entities.Entity;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import Bomberman.entities.*;
import Bomberman.entities.Bomb.Bomb;
import Bomberman.entities.Bomb.Flame;
import Bomberman.entities.StillObj.Brick;
import Bomberman.entities.StillObj.Grass;
import Bomberman.entities.StillObj.Portal;
import Bomberman.entities.StillObj.Wall;
import Bomberman.entities.Player.Bomber;
import Bomberman.entities.Enemy.Enemy;
import Bomberman.entities.Enemy.Oneal;
import Bomberman.entities.PowerItem.BombItem;
import Bomberman.entities.PowerItem.FlameItem;
import Bomberman.entities.PowerItem.Item;
import Bomberman.entities.PowerItem.SpeedItem;
import Bomberman.graphics.Sprite;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class BombermanGame extends Application {
    
    public static int WIDTH = 20;
    public static int HEIGHT = 15;
    public static int level = 0;
    public static GraphicsContext gc;
    private Canvas canvas;
    private Scanner scanner;
    private int xStart;
    private int yStart;
    public static final ArrayList<Enemy> enemies = new ArrayList<>();
    public static final ArrayList<Entity> stillObjects = new ArrayList<>();
    public static final ArrayList<Flame> flameList = new ArrayList<>();
    public int startBomb = 1;
    public int startSpeed = 1;
    public int startFlame  = 1;
    public static Bomber myBomber;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {

    load();
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        scene.setOnKeyPressed(event -> myBomber.KeyPressed(event));
        scene.setOnKeyReleased(event -> myBomber.KeyReleased(event));

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();



    }
    public void update() {
        for(int i = 0; i < enemies.size(); i++)
            enemies.get(i).update();
        for (int i = 0; i < flameList.size(); i++)
            flameList.get(i).update();

        myBomber.update();
        List<Bomb> bombs = myBomber.getBombs();
        for(Bomb bomb : bombs) {
            bomb.update();
        }

        for(int i = 0; i < stillObjects.size(); i++)
            stillObjects.get(i).update();
        handleCollisions();
        checkCollisionFlame();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = stillObjects.size() - 1; i >= 0; i--) {
            stillObjects.get(i).render(gc);
        }
        enemies.forEach(g -> g.render(gc));
        List<Bomb> bombs = myBomber.getBombs();
        for(Bomb bomb : bombs) {
            bomb.render(gc);
        }
        myBomber.render(gc);
        flameList.forEach(g -> g.render(gc));
    }

    public void load() {
        try {
            scanner = new Scanner(new FileReader("res/levels/level" + level + ".txt"));
            Sound.initial();
            Sound.playBackground();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.nextInt();
        HEIGHT = scanner.nextInt();
        WIDTH = scanner.nextInt();
        scanner.nextLine();
        createMap();
    }

    public void load(int level) {
        try {
            scanner = new Scanner(new FileReader("res/levels/level" + level + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.nextInt();
        HEIGHT = scanner.nextInt();
        WIDTH = scanner.nextInt();
        scanner.nextLine();
        createMap();
    }

    public void createMap() {
        for (int i = 0; i < HEIGHT; i++) {
            String r = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                if (r.charAt(j) == '#') {
                    stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else {
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    if (r.charAt(j) == '*') {
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 'x') {
                        stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == '1') {
                        enemies.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                    }
                    if (r.charAt(j) == '2') {
                        enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                    }
                    if (r.charAt(j) == '3') {
                        enemies.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                    }
                    if (r.charAt(j) == 'b') {
                        stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 'f') {
                        stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 's') {
                        stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 'p'){
                        myBomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                        xStart = j;
                        yStart = i;
                    }
                }
            }
        }
        stillObjects.sort(new Layer());
    }

    public void handleCollisions() {
        List<Bomb> bombs = myBomber.getBombs();
        Rectangle r1 = myBomber.getBounds();
        //Bomber vs Bombs
//        for (Bomb bomb : bombs) {
//            Rectangle r2 = bomb.getBounds();
//            if (!bomb.isAllowedToPassThrough(myBomber) && r1.intersects(r2)) {
//                //myBomber.die();
//                break;
//            }
//        }
        //Bomber vs StillObjects
        for (Entity stillObject : stillObjects) {
            Rectangle r2 = stillObject.getRec();
            if (r1.intersects(r2)) {
                if (myBomber.getLayer() == stillObject.getLayer() && stillObject instanceof Item) {
                    Sound.playGetItem();
                    if(stillObject instanceof BombItem) {
                        startBomb ++;
                        myBomber.setBombsexist(startBomb);
                        stillObjects.remove(stillObject);
                    } else if(stillObject instanceof SpeedItem) {
                        startSpeed += 1;
                        myBomber.setSpeed(startSpeed);
                        stillObjects.remove(stillObject);
                    } else if(stillObject instanceof FlameItem) {
                        startFlame ++;
                        System.out.println(startFlame);
                        myBomber.setRadius(startFlame);
                        stillObjects.remove(stillObject);
                    }
                    myBomber.stay();
                } else if(myBomber.getLayer() == stillObject.getLayer() && stillObject instanceof Portal) {
                    if(enemies.size() == 0) {
                        //pass level
                        load(++ level);
                        Sound.stopBackground();
                        Sound.playPass();
                    }
                } else if(myBomber.getLayer() >= stillObject.getLayer()) {
                    myBomber.move();
                }
                else {
                    myBomber.stay();
                }
                break;
            }
        }
        //Bomber vs Enemies
        for (Enemy enemy : enemies) {
            Rectangle r2 = enemy.getBounds();
            if (r1.intersects(r2)) {
                Sound.playGameOver();
                myBomber.setAlive(false);
                if(myBomber.isAlive() == false){
                    Timer count = new Timer();
                    count.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            count.cancel();
                            System.exit(0);
                        }
                    }, 1000,1);

                }
//                myBomber.setCoordinate(2,1);
            }
        }
        //Enemies vs Bombs
        for (Enemy enemy : enemies) {
            Rectangle r2 = enemy.getBounds();
            for (Bomb bomb : bombs) {
                Rectangle r3 = bomb.getBounds();
                if (!bomb.isAllowedToPassThrough(enemy) && r2.intersects(r3)) {
                    enemy.stay();
                    break;
                }
            }
        }
        //Enemies vs StillObjects
        for (Enemy enemy : enemies) {
            Rectangle r2 = enemy.getBounds();
            for (Entity stillObject : stillObjects) {
                Rectangle r3 = stillObject.getRec();
                if (r2.intersects(r3)) {
                    if (enemy.getLayer() >= stillObject.getLayer()) {
                        enemy.move();
                    } else {
                        enemy.stay();
                    }
                    break;
                }
            }
        }
    }

    public void checkCollisionFlame() {
        //if(explosionList != null){
            for(int i = 0; i < flameList.size(); i++) {
                Rectangle r1 = flameList.get(i).getRec();
                for (int j = 0; j < stillObjects.size(); j++) {
                    Rectangle r2 = stillObjects.get(j).getRec();
                    if(r1.intersects(r2) && !(stillObjects.get(j) instanceof Item))
                        stillObjects.get(j).setAlive(false);

                }
                for(int j = 0; j < enemies.size(); j++){
                    Rectangle r2 = enemies.get(j).getBounds();
                    if(r1.intersects(r2)) {
                        Sound.playMobDie();
                        enemies.get(j).setAlive(false);
                    }

                }
                Rectangle r2 = myBomber.getBounds();
                if(r1.intersects(r2)) {
                    myBomber.setAlive(false);
                    //myBomber.die();
                    //myBomber = new Bomber(xStart, yStart, Sprite.player_right.getFxImage());
                    startBomb = 1;
                    startFlame = 1;
                    startSpeed = 1;
                    if(myBomber.isAlive() == false){
                        Sound.playGameOver();
                        Timer count = new Timer();
                        count.schedule(new TimerTask() {
                            @Override
                            public void run() {

                                count.cancel();
                                System.exit(0);
                            }
                        }, 500,1);

                    }

                    //createMap();
            }
        }
    }

    public void Endgame(State state) {
        javafx.scene.control.Dialog<Pair<String, String>> dialog = new javafx.scene.control.Dialog<>();
        dialog.setTitle("Messenger");
        dialog.setContentText("Gameover");
        dialog.showAndWait();
    }

}

