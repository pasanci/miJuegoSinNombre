package com.example.dual.Dual.Main;

import static com.example.dual.Dual.Main.ObstacleCreator.HALF;
import static com.example.dual.Dual.Main.ObstacleCreator.HOLE;
import static com.example.dual.Dual.Main.ObstacleCreator.SMALLHOLE;
import static com.example.dual.Dual.Main.ObstacleCreator.CENTER;
import static com.example.dual.Dual.Main.ObstacleCreator.STEADY;
import static com.example.dual.Dual.Main.ObstacleCreator.ROTATING;
import static com.example.dual.Dual.Main.ObstacleCreator.SOLID;
import static com.example.dual.Dual.Main.ObstacleCreator.FADING;
import static com.example.dual.Dual.Main.ObstacleCreator.LEFT;
import static com.example.dual.Dual.Main.ObstacleCreator.RIGHT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.dual.Dual.GameState.GameStateManager;
import com.example.dual.Dual.TileMap.Obstacle;
import com.example.dual.Dual.TileMap.FadingObstacle;
import com.example.dual.Dual.TileMap.FadingRotatingObstacle;
import com.example.dual.Dual.TileMap.RotatingObstacle;
import com.example.dual.Dual.TileMap.Textures;

public class Levels {

    private HashMap<Integer, List<Obstacle>> levels = new HashMap<Integer, List<Obstacle>>();
    List<Obstacle> obstacles = new ArrayList<Obstacle>();
    Textures textures;
    double speed;
    private GameStateManager gsm;

    public Levels(GameStateManager gsm, Textures textures) {
        this.gsm = gsm;
        this.textures = textures;
    }

    public void setRotationSpeed(double speed) {
        this.speed = speed;
    }

    public List<Obstacle> restartLevel() {
        for (Obstacle obstacle : obstacles) {
            obstacle.restart();
        }
        return obstacles;
    }

    public List<Obstacle> resetLevel() {
        for (Obstacle obstacle : obstacles) {
            obstacle.reset();
        }
        return obstacles;
    }

    //level1
    public List<Obstacle> loadLevel(int number) {
        double blockWidth = 100;
        ObstacleCreator obsCreator = new ObstacleCreator(this.gsm, this.textures, obstacles);
        switch(number)
        {
            case 0:
                obstacles.clear();
                obstacles.add(new RotatingObstacle(this.gsm, textures,-300,25,40,Math.PI,true));
                levels.put(1,obstacles);
                break;
            case 1:
                obsCreator.reset();
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                levels.put(number,obstacles);
                break;
            case 2:
                obsCreator.reset();
                obsCreator.addObstacle(HOLE, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HOLE, STEADY, SOLID, RIGHT);
                levels.put(number,obstacles);
                break;
            case 3:
                obsCreator.reset();
                obsCreator.addObstacle(HOLE, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HOLE, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HOLE, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HOLE, STEADY, SOLID, LEFT);
                levels.put(number,obstacles);
                break;
            case 4:
                obsCreator.reset();
                obsCreator.addObstacle(HALF, ROTATING, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, ROTATING, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, ROTATING, SOLID, LEFT);
                levels.put(number,obstacles);
                break;
            case 5:
                obsCreator.reset();
                obsCreator.addObstacle(SMALLHOLE, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                levels.put(number,obstacles);
                break;
            case 6:
                obsCreator.reset();
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(CENTER, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(CENTER, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, SOLID, LEFT);
                levels.put(number,obstacles);
                break;

            case 7:
                obsCreator.reset();
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, FADING, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                levels.put(number,obstacles);
                break;
            case 8:
                obsCreator.reset();
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, FADING, LEFT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(CENTER, STEADY, FADING, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, FADING, LEFT);
                levels.put(number,obstacles);
                break;
            case 9:
                obsCreator.reset();
                obsCreator.addObstacle(SMALLHOLE, STEADY, FADING, LEFT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, FADING, RIGHT);
                levels.put(number,obstacles);
                break;
            case 10:
                obsCreator.reset();
                obsCreator.addObstacle(SMALLHOLE, STEADY, FADING, LEFT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, FADING, RIGHT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, FADING, LEFT);
                levels.put(number,obstacles);
                break;
            case 11:
                obsCreator.reset();
                obsCreator.addObstacle(HALF, ROTATING, FADING, LEFT);
                obsCreator.addObstacle(HALF, ROTATING, FADING, LEFT);
                obsCreator.addObstacle(HALF, ROTATING, FADING, RIGHT);
                levels.put(number,obstacles);
                break;
            case 12:
                obsCreator.reset();
                obsCreator.addObstacle(SMALLHOLE, STEADY, FADING, LEFT, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, FADING, LEFT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, LEFT);
                obsCreator.addObstacle(HALF, STEADY, FADING, LEFT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, FADING, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, SOLID, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, FADING, RIGHT);levels.put(number,obstacles);
                break;
            case 13:
                obsCreator.reset();
                obsCreator.addObstacle(HALF, STEADY, FADING, LEFT);
                obsCreator.addObstacle(HALF, STEADY, FADING, LEFT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, FADING, RIGHT);
                obsCreator.addObstacle(CENTER, STEADY, FADING, RIGHT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, FADING, LEFT);
                obsCreator.addObstacle(CENTER, STEADY, FADING, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, FADING, RIGHT);
                obsCreator.addObstacle(HALF, STEADY, FADING, RIGHT);
                obsCreator.addObstacle(SMALLHOLE, STEADY, FADING, LEFT);
                levels.put(number,obstacles);
                break;
            default:
                obstacles.clear();
                return obstacles;
        }
        return obstacles;
    }

}
