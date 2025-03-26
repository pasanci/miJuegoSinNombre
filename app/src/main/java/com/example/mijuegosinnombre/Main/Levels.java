package com.example.mijuegosinnombre.Main;

import static com.example.mijuegosinnombre.Main.SimpleObstacle.HALF;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.HOLE;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.NONE;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.SMALLHOLE;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.CENTER;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.STEADY;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.ROTATING;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.SOLID;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.FADING;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.LEFT;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.RIGHT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.mijuegosinnombre.GameState.GameStateManager;
import com.example.mijuegosinnombre.TileMap.Obstacle;
import com.example.mijuegosinnombre.TileMap.RotatingObstacle;
import com.example.mijuegosinnombre.TileMap.Textures;

public class Levels {

    private HashMap<Integer, List<Obstacle>> levels = new HashMap<Integer, List<Obstacle>>();
    private List<Obstacle> obstacles = new ArrayList<Obstacle>();
    private Textures textures;
    private GameStateManager gsm;

    private List<List<SimpleObstacle>> levelsList;

    public int getLevelsNumber() {
        return levelsList.size();
    }

    public Levels(GameStateManager gsm, Textures textures) {
        this.gsm = gsm;
        this.textures = textures;
        this.levelsList = new ArrayList<List<SimpleObstacle>>();
        //Principiante
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
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
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
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        this.levelsList.add(level);
        //Muy Facil
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, LEFT));
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
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        this.levelsList.add(level);
        //Facil
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, ROTATING, SOLID, LEFT));
        this.levelsList.add(level);
        //Medio
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(CENTER, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, LEFT));
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
        //Dificil
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
        //Muy Dificil
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HALF, ROTATING, FADING, LEFT));
        level.add(new SimpleObstacle(HALF, ROTATING, FADING, LEFT));
        level.add(new SimpleObstacle(HALF, ROTATING, FADING, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(HOLE, STEADY, FADING, LEFT, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(HOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, RIGHT));
        this.levelsList.add(level);
        level = new ArrayList<SimpleObstacle>();
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, FADING, LEFT, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, LEFT));
        level.add(new SimpleObstacle(SMALLHOLE, STEADY, SOLID, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, RIGHT));
        level.add(new SimpleObstacle(HALF, STEADY, SOLID, LEFT));
        level.add(new SimpleObstacle(HALF, STEADY, FADING, RIGHT));
        this.levelsList.add(level);
        //Experto
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
