package com.example.mijuegosinnombre.Main;

import com.example.mijuegosinnombre.GameState.GameStateManager;
import com.example.mijuegosinnombre.TileMap.FadingObstacle;
import com.example.mijuegosinnombre.TileMap.Obstacle;
import com.example.mijuegosinnombre.TileMap.RotatingObstacle;
import com.example.mijuegosinnombre.TileMap.FadingRotatingObstacle;
import com.example.mijuegosinnombre.TileMap.Textures;

import static com.example.mijuegosinnombre.Main.SimpleObstacle.HALF;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.HOLE;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.SMALLHOLE;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.CENTER;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.STEADY;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.ROTATING;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.SOLID;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.FADING;
import static com.example.mijuegosinnombre.Main.SimpleObstacle.LEFT;

import java.util.List;

public class ObstacleCreator {

    protected GameStateManager gsm;
    private Textures textures;
    private List<Obstacle> obstacles;
    private double blockWidth = 100;
    private double y = -300;

    public ObstacleCreator(GameStateManager gsm, Textures textures, List<Obstacle> obstacles){
        this.gsm = gsm;
        this.textures = textures;
        obstacles.clear();
        this.obstacles = obstacles;
    }

    public void reset(){
        this.obstacles.clear();
    }

    public void addObstacle(int type, int rotating, int fading, int side){
        boolean realside = true;
        if(side == LEFT){
            realside = false;
        }
        int percentaje1 = 0;
        int percentaje2 = 0;
        if (type == HALF){
            percentaje1 = 50;
        }
        else if (type == HOLE){
            percentaje1 = 20;
            percentaje2 = 50;
        }
        else if (type == SMALLHOLE){
            percentaje1 = 20;
            percentaje2 = 58;
        }
        if(rotating == STEADY && fading == SOLID){
            if(type == CENTER){
                obstacles.add(new Obstacle(this.gsm, this.textures,this.y, 40, blockWidth));
            }else {
                obstacles.add(new Obstacle(this.gsm, this.textures, this.y, percentaje1, blockWidth, realside));
                if (type == HOLE || type == SMALLHOLE) {
                    obstacles.add(new Obstacle(this.gsm, this.textures, this.y, percentaje2, blockWidth, !realside));
                }
            }
            this.y -= 1000;
        }
        else if(rotating == STEADY && fading == FADING){
            if(type == CENTER){
                obstacles.add(new FadingObstacle(this.gsm, this.textures,this.y, 40, blockWidth));
            }else {
                obstacles.add(new FadingObstacle(this.gsm, this.textures, this.y, percentaje1, blockWidth, realside));
                if (type == HOLE || type == SMALLHOLE) {
                    obstacles.add(new FadingObstacle(this.gsm, this.textures, this.y, percentaje2, blockWidth, !realside));
                }
            }
            this.y -= 1000;
        }
        else if(rotating == ROTATING && fading == SOLID){
            obstacles.add(new RotatingObstacle(this.gsm, this.textures,this.y,25,blockWidth,Math.PI,realside));
            this.y -= 1000;
            //this.y -= 1000;
        }
        else{
            obstacles.add(new FadingRotatingObstacle(this.gsm, this.textures,this.y,25,blockWidth,Math.PI,realside));
            this.y -= 1000;
            //this.y -= 1000;
        }
    }

    public void addObstacle(int type, int rotating, int fading, int side, int fadingSide){
        boolean realside = true;
        if(side == LEFT){
            realside = false;
        }
        int percentaje1 = 0;
        int percentaje2 = 0;
        if (type == HOLE){
            percentaje1 = 20;
            percentaje2 = 50;
        }
        else if (type == SMALLHOLE){
            percentaje1 = 20;
            percentaje2 = 58;
        }
        if ((type == HOLE || type == SMALLHOLE) && fading == FADING) {
            if(side == LEFT) {
                if (fadingSide == LEFT) {
                    obstacles.add(new FadingObstacle(this.gsm, this.textures, this.y, percentaje1, blockWidth, realside));
                    if (type == HOLE || type == SMALLHOLE) {
                        obstacles.add(new Obstacle(this.gsm, this.textures, this.y, percentaje2, blockWidth, !realside));
                    }
                } else {
                    obstacles.add(new Obstacle(this.gsm, this.textures, this.y, percentaje1, blockWidth, realside));
                    if (type == HOLE || type == SMALLHOLE) {
                        obstacles.add(new FadingObstacle(this.gsm, this.textures, this.y, percentaje2, blockWidth, !realside));
                    }
                }
            }
            else{
                if (fadingSide == LEFT) {
                    obstacles.add(new Obstacle(this.gsm, this.textures, this.y, percentaje1, blockWidth, realside));
                    if (type == HOLE || type == SMALLHOLE) {
                        obstacles.add(new FadingObstacle(this.gsm, this.textures, this.y, percentaje2, blockWidth, !realside));
                    }
                } else {
                    obstacles.add(new FadingObstacle(this.gsm, this.textures, this.y, percentaje1, blockWidth, realside));
                    if (type == HOLE || type == SMALLHOLE) {
                        obstacles.add(new Obstacle(this.gsm, this.textures, this.y, percentaje2, blockWidth, !realside));
                    }
                }
            }
            this.y -= 1000;
        }
    }
}


