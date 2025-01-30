package com.example.dual.Dual.Main;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.dual.Dual.GameState.GameStateManager;
import com.example.dual.Dual.TileMap.FadingObstacle;
import com.example.dual.Dual.TileMap.FadingRotatingObstacle;
import com.example.dual.Dual.TileMap.Obstacle;
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
        switch(number)
        {
            case 0:
                obstacles.clear();
                obstacles.add(new RotatingObstacle(this.gsm, textures,-300,25,40,Math.PI,true));
                levels.put(1,obstacles);
                break;
            case 1:
                obstacles.clear();
                obstacles.add(new Obstacle(this.gsm, textures,-300, 50, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-1300, 50, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-2300, 50, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-3300, 50, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-4300, 50, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-5300, 50, blockWidth, true));

                levels.put(number,obstacles);
                break;
            case 2:
                obstacles.clear();
                obstacles.add(new Obstacle(this.gsm, textures,-300, 50, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-300, 20, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-1300, 50, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-1300, 20, blockWidth, true));
                levels.put(number,obstacles);
                break;
            case 3:
                obstacles.clear();
                obstacles.add(new Obstacle(this.gsm, textures,-300, 50, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-300, 20, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-1300, 50, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-1300, 20, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-2300, 50, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-2300, 20, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-3300, 50, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-3300, 20, blockWidth, false));
                levels.put(number,obstacles);
                break;
            case 4:
                obstacles.clear();
                obstacles.add(new RotatingObstacle(this.gsm, textures,-600,25,blockWidth,Math.PI,true));
                obstacles.add(new RotatingObstacle(this.gsm, textures,-1950,25,blockWidth,Math.PI,true));
                obstacles.add(new RotatingObstacle(this.gsm, textures,-3200,25,blockWidth,Math.PI,false));
                levels.put(number,obstacles);
                break;
            case 5:
                obstacles.clear();
                obstacles.add(new Obstacle(this.gsm, textures,-300, 60, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-300, 20, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,0,-1300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,0,-2300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,0,-3300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,-4300, 60, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-4300, 20, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,this.gsm.getWidth()/2+20,-5300,(this.gsm.getWidth())-(this.gsm.getWidth()/2+20),blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,this.gsm.getWidth()/2+20,-6300,(this.gsm.getWidth())-(this.gsm.getWidth()/2+20),blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,this.gsm.getWidth()/2+20,-7300,(this.gsm.getWidth())-(this.gsm.getWidth()/2+20),blockWidth));
                levels.put(number,obstacles);
                break;
            case 6:
                obstacles.clear();
                obstacles.add(new Obstacle(this.gsm, textures,0,-300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,0,-1300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,-2300, 60, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-2300, 20, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-3300, 40, blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,-4300, 60, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-4300, 20, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-5300, 40, blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,-6300, 50, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-7300, 50, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-8300, 60, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-8300, 20, blockWidth, false));
                levels.put(number,obstacles);
                break;

            case 7:
                obstacles.clear();
                obstacles.add(new Obstacle(this.gsm, textures,0,-300,this.gsm.getWidth()/2-20,blockWidth));
                //obstacles.add(new Obstacle(this.gsm, textures,0,-300,Main.WIDTH/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,0,-1300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,0,-2300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new FadingObstacle(this.gsm, textures,this.gsm.getWidth()/2+20,-3300,(this.gsm.getWidth())-(this.gsm.getWidth()/2+20),blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,this.gsm.getWidth()/2+20,-4300,(this.gsm.getWidth())-(this.gsm.getWidth()/2+20),blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,this.gsm.getWidth()/2+20,-5300,(this.gsm.getWidth())-(this.gsm.getWidth()/2+20),blockWidth));
                //obstacles.add(new RotatingObstacle(Main.WIDTH/2+20,-1800,(Main.WIDTH)-(Main.WIDTH/2+20),40,0,0.07));

                levels.put(number,obstacles);
                break;
            case 8:
                obstacles.clear();
                obstacles.add(new Obstacle(this.gsm, textures,0,-300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,0,-1300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,-2300, 60, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-2300, 20, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,0,-3300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,-4300, 60, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-4300, 20, blockWidth, false));
                obstacles.add(new FadingObstacle(this.gsm, textures,-5300, 40, blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,-6300, 50, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-7300, 50, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-8300, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-8300, 20, blockWidth, false));
                levels.put(number,obstacles);
                break;
            case 9:
                obstacles.clear();
                obstacles.add(new FadingObstacle(this.gsm, textures,-300, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-300, 20, blockWidth, false));
                obstacles.add(new FadingObstacle(this.gsm, textures,-1300, 60, blockWidth, false));
                obstacles.add(new FadingObstacle(this.gsm, textures,-1300, 20, blockWidth, true));
                levels.put(number,obstacles);
                break;
            case 10:
                obstacles.clear();
                obstacles.add(new FadingObstacle(this.gsm, textures,-300, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-300, 20, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-1300, 60, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-1300, 20, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-2300, 60, blockWidth, false));
                obstacles.add(new FadingObstacle(this.gsm, textures,-2300, 20, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-3300, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-3300, 20, blockWidth, false));
                levels.put(number,obstacles);
                break;
            case 11:
                obstacles.clear();
                obstacles.add(new FadingRotatingObstacle(this.gsm, textures,-600,25,blockWidth,Math.PI,true));
                obstacles.add(new FadingRotatingObstacle(this.gsm, textures,-1950,25,blockWidth,Math.PI,true));
                obstacles.add(new FadingRotatingObstacle(this.gsm, textures,-3200,25,blockWidth,Math.PI,false));
                levels.put(number,obstacles);
                break;
            case 12:
                obstacles.clear();
                obstacles.add(new FadingObstacle(this.gsm, textures,-300, 60, blockWidth, true));
                obstacles.add(new Obstacle(this.gsm, textures,-300, 20, blockWidth, false));
                obstacles.add(new FadingObstacle(this.gsm, textures,0,-1300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,0,-2300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new FadingObstacle(this.gsm, textures,0,-3300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,-4300, 60, blockWidth, false));
                obstacles.add(new Obstacle(this.gsm, textures,-4300, 20, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,this.gsm.getWidth()/2+20,-5300,(this.gsm.getWidth())-(this.gsm.getWidth()/2+20),blockWidth));
                obstacles.add(new Obstacle(this.gsm, textures,this.gsm.getWidth()/2+20,-6300,(this.gsm.getWidth())-(this.gsm.getWidth()/2+20),blockWidth));
                obstacles.add(new FadingObstacle(this.gsm, textures,this.gsm.getWidth()/2+20,-7300,(this.gsm.getWidth())-(this.gsm.getWidth()/2+20),blockWidth));
                levels.put(number,obstacles);
                break;
            case 13:
                obstacles.clear();
                obstacles.add(new FadingObstacle(this.gsm, textures,0,-300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new FadingObstacle(this.gsm, textures,0,-1300,this.gsm.getWidth()/2-20,blockWidth));
                obstacles.add(new FadingObstacle(this.gsm, textures,-2300, 60, blockWidth, false));
                obstacles.add(new FadingObstacle(this.gsm, textures,-2300, 20, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-3300, 40, blockWidth));
                obstacles.add(new FadingObstacle(this.gsm, textures,-4300, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-4300, 20, blockWidth, false));
                obstacles.add(new FadingObstacle(this.gsm, textures,-5300, 40, blockWidth));
                obstacles.add(new FadingObstacle(this.gsm, textures,-6300, 50, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-7300, 50, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-8300, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(this.gsm, textures,-8300, 20, blockWidth, false));
                levels.put(number,obstacles);
                break;
            default:
                obstacles.clear();
                return obstacles;
        }
        return obstacles;
    }

}
