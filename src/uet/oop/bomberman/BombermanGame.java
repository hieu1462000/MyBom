package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Bomber;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    private boolean UP,DOWN,LEFT,RIGHT;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        createMap();
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        Enemy enemy =new Enemy(3,4,Sprite.balloom_right1.getFxImage());
        entities.add(bomberman);
        entities.add(enemy);
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        UP=true;
        DOWN=true;
        LEFT=true;
        RIGHT=true;
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode())
                {
                    case UP:
                    {   UP=CheckGrass(bomberman.x,bomberman.y-Sprite.SCALED_SIZE);
                        if(UP==true)
                        {
                            bomberman.MoveUp();
                            break;
                        }
                        else break;
                    }
                    case DOWN:
                    {
                        DOWN=CheckGrass(bomberman.x,bomberman.y+Sprite.SCALED_SIZE);
                        if(DOWN==true)
                        {
                            bomberman.MoveDown();
                            break;
                        }
                        else break;
                    }
                    case LEFT:
                    {   LEFT=CheckGrass(bomberman.x-Sprite.SCALED_SIZE,bomberman.y);
                        if(LEFT==true)
                        {
                            bomberman.MoveLeft();
                            break;
                        }
                        else break;
                    }
                    case RIGHT:
                    {   RIGHT=CheckGrass(bomberman.x+Sprite.SCALED_SIZE,bomberman.y);
                        if(RIGHT==true)
                        {
                            bomberman.MoveRight();
                            break;
                        }
                        else break;
                    }

                }
            }
        });
        scene.setOnKeyReleased(event -> bomberman.KeyReleased(event));
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();


    }
    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1||(i==3&&j==2)||(i==3&&j==10)) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }
    public boolean CheckGrass(int X,int Y)
    {
        for(Entity e:stillObjects)
        {
            if(e.x==X&&e.y==Y)
            {
                if(e instanceof Grass)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
