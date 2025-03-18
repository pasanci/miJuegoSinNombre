package com.example.dual.Dual.Main;

import static com.example.dual.Dual.Main.SimpleObstacle.HALF;
import static com.example.dual.Dual.Main.SimpleObstacle.HOLE;
import static com.example.dual.Dual.Main.SimpleObstacle.NONE;
import static com.example.dual.Dual.Main.SimpleObstacle.SMALLHOLE;
import static com.example.dual.Dual.Main.SimpleObstacle.CENTER;
import static com.example.dual.Dual.Main.SimpleObstacle.STEADY;
import static com.example.dual.Dual.Main.SimpleObstacle.ROTATING;
import static com.example.dual.Dual.Main.SimpleObstacle.SOLID;
import static com.example.dual.Dual.Main.SimpleObstacle.FADING;
import static com.example.dual.Dual.Main.SimpleObstacle.LEFT;
import static com.example.dual.Dual.Main.SimpleObstacle.RIGHT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.dual.Dual.GameState.GameStateManager;
import com.example.dual.Dual.TileMap.Obstacle;
import com.example.dual.Dual.TileMap.RotatingObstacle;
import com.example.dual.Dual.TileMap.Textures;

public class Levels {

    private HashMap<Integer, List<Obstacle>> levels = new HashMap<Integer, List<Obstacle>>();
    List<Obstacle> obstacles = new ArrayList<Obstacle>();
    Textures textures;
    private GameStateManager gsm;
    List<List<SimpleObstacle>> levelsList;

    public Levels(GameStateManager gsm, Textures textures) {
        this.gsm = gsm;
        this.textures = textures;
        this.levelsList = new ArrayList<List<SimpleObstacle>>();
        List<SimpleObstacle> level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(CENTER, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, ROTATING, FADING, LEFT));
        level.add(new SimpleObstacle(HALF, ROTATING, FADING, LEFT));
        level.add(new SimpleObstacle(HALF, ROTATING, FADING, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, LEFT, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(CENTER, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, LEFT));
        this.levelsList.add(level);
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
        ObstacleCreator obsCreator = new ObstacleCreator(this.gsm, this.textures, obstacles);
        List<SimpleObstacle> level = this.levelsList.get(number-1);
        for(SimpleObstacle obs:level){
            if(obs.getFadingSide()==NONE) {
                obsCreator.addObstacle(obs.getType(), obs.getRotating(), obs.getFading(), obs.getSide());
            }
            else {
                obsCreator.addObstacle(obs.getType(), obs.getRotating(), obs.getFading(), obs.getSide(), obs.getFadingSide());
            }
        }
        levels.put(number,obstacles);
        return obstacles;
    }

}
