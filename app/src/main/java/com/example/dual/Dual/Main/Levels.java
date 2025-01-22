package com.example.dual.Dual.Main;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.dual.Dual.TileMap.FadingObstacle;
import com.example.dual.Dual.TileMap.FadingRotatingObstacle;
import com.example.dual.Dual.TileMap.Obstacle;
import com.example.dual.Dual.TileMap.RotatingObstacle;
import com.example.dual.Dual.TileMap.Textures;

public class Levels {

    private HashMap<Integer, List<Obstacle>> levels = new HashMap<Integer, List<Obstacle>>();
    List<Obstacle> obstacles = new ArrayList<Obstacle>();
    Textures textures;
    double factor = 300.0/350.0;
    double speed;

    public Levels(Textures textures, double diameter) {
        this.textures = textures;
        this.factor = (300.0/diameter)/(300.0/350.0);
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
        double blockWidth = 40*factor;
        switch(number)
        {
            case 0:
                obstacles.clear();
                obstacles.add(new RotatingObstacle(textures,-300,25,40,Math.PI,true));
                //obstacles.add(new Obstacle(0,-300,Main.WIDTH/2-20,40));
                levels.put(1,obstacles);
                break;
            case 1:
                obstacles.clear();

                obstacles.add(new Obstacle(textures,0,-300*factor, Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,0,-600*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,0,-900*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-1200*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                obstacles.add(new Obstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-1500*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                obstacles.add(new Obstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-1800*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));

                levels.put(number,obstacles);
                break;
            case 2:
                obstacles.clear();
                obstacles.add(new Obstacle(textures,-300*factor, 60, blockWidth, true));
                obstacles.add(new Obstacle(textures,-300*factor, 20, blockWidth, false));
                obstacles.add(new Obstacle(textures,-600*factor, 60, blockWidth, false));
                obstacles.add(new Obstacle(textures,-600*factor, 20, blockWidth, true));
                levels.put(number,obstacles);
                break;
            case 3:
                obstacles.clear();
                obstacles.add(new Obstacle(textures,-300*factor, 60, blockWidth, true));
                obstacles.add(new Obstacle(textures,-300*factor, 20, blockWidth, false));
                obstacles.add(new Obstacle(textures,-600*factor, 60, blockWidth, false));
                obstacles.add(new Obstacle(textures,-600*factor, 20, blockWidth, true));
                obstacles.add(new Obstacle(textures,-900*factor, 60, blockWidth, false));
                obstacles.add(new Obstacle(textures,-900*factor, 20, blockWidth, true));
                obstacles.add(new Obstacle(textures,-1200*factor, 60, blockWidth, true));
                obstacles.add(new Obstacle(textures,-1200*factor, 20, blockWidth, false));
                levels.put(number,obstacles);
                break;
            case 4:
                obstacles.clear();
                double diferencia = Resources.getSystem().getDisplayMetrics().heightPixels-1920;
                obstacles.add(new RotatingObstacle(textures,-600*factor,25,blockWidth,Math.PI,true));
                obstacles.add(new RotatingObstacle(textures,-1250*factor,25,blockWidth,Math.PI,true));
                obstacles.add(new RotatingObstacle(textures,-2150*factor,25,blockWidth,Math.PI,false));
                levels.put(number,obstacles);
                break;
            case 5:
                obstacles.clear();
                obstacles.add(new Obstacle(textures,-300*factor, 60, blockWidth, true));
                obstacles.add(new Obstacle(textures,-300*factor, 20, blockWidth, false));
                obstacles.add(new Obstacle(textures,0,-600*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,0,-900*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,0,-1200*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,-1500*factor, 60, blockWidth, false));
                obstacles.add(new Obstacle(textures,-1500*factor, 20, blockWidth, true));
                obstacles.add(new Obstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-1800*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                obstacles.add(new Obstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-2100*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                obstacles.add(new Obstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-2400*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                levels.put(number,obstacles);
                break;
            case 6:
                obstacles.clear();
                obstacles.add(new Obstacle(textures,0,-300*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,0,-600*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,-900*factor, 60, blockWidth, false));
                obstacles.add(new Obstacle(textures,-900*factor, 20, blockWidth, true));
                obstacles.add(new Obstacle(textures,-1200*factor, 40, blockWidth));
                obstacles.add(new Obstacle(textures,-1500*factor, 60, blockWidth, true));
                obstacles.add(new Obstacle(textures,-1500*factor, 20, blockWidth, false));
                obstacles.add(new Obstacle(textures,-1800*factor, 40, blockWidth));
                obstacles.add(new Obstacle(textures,-2100*factor, 50, blockWidth, true));
                obstacles.add(new Obstacle(textures,-2400*factor, 50, blockWidth, true));
                obstacles.add(new Obstacle(textures,-2700*factor, 60, blockWidth, true));
                obstacles.add(new Obstacle(textures,-2700*factor, 20, blockWidth, false));
                levels.put(number,obstacles);
                break;

            case 7:
                obstacles.clear();
                obstacles.add(new Obstacle(textures,0,-300*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                //obstacles.add(new Obstacle(textures,0,-300*factor,Main.WIDTH/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,0,-600*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,0,-900*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new FadingObstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-1200*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                obstacles.add(new Obstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-1500*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                obstacles.add(new Obstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-1800*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                //obstacles.add(new RotatingObstacle(Main.WIDTH/2+20,-1800,(Main.WIDTH)-(Main.WIDTH/2+20),40,0,0.07));

                levels.put(number,obstacles);
                break;
            case 8:
                obstacles.clear();
                obstacles.add(new Obstacle(textures,0,-300*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,0,-600*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,-900*factor, 60, blockWidth, false));
                obstacles.add(new Obstacle(textures,-900*factor, 20, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,0,-1200*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,-1500*factor, 60, blockWidth, true));
                obstacles.add(new Obstacle(textures,-1500*factor, 20, blockWidth, false));
                obstacles.add(new FadingObstacle(textures,-1800*factor, 40, blockWidth));
                obstacles.add(new Obstacle(textures,-2100*factor, 50, blockWidth, true));
                obstacles.add(new Obstacle(textures,-2400*factor, 50, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-2700*factor, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-2700*factor, 20, blockWidth, false));
                levels.put(number,obstacles);
                break;
            case 9:
                obstacles.clear();
                obstacles.add(new FadingObstacle(textures,-300*factor, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-300*factor, 20, blockWidth, false));
                obstacles.add(new FadingObstacle(textures,-600*factor, 60, blockWidth, false));
                obstacles.add(new FadingObstacle(textures,-600*factor, 20, blockWidth, true));
                levels.put(number,obstacles);
                break;
            case 10:
                obstacles.clear();
                obstacles.add(new FadingObstacle(textures,-300*factor, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-300*factor, 20, blockWidth, false));
                obstacles.add(new Obstacle(textures,-600*factor, 60, blockWidth, false));
                obstacles.add(new Obstacle(textures,-600*factor, 20, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-900*factor, 60, blockWidth, false));
                obstacles.add(new FadingObstacle(textures,-900*factor, 20, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-1200*factor, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-1200*factor, 20, blockWidth, false));
                levels.put(number,obstacles);
                break;
            case 11:
                obstacles.clear();
                obstacles.add(new FadingRotatingObstacle(textures,-600*factor,25,blockWidth,Math.PI,true));
                obstacles.add(new RotatingObstacle(textures,-1250*factor,25,blockWidth,Math.PI,true));
                obstacles.add(new FadingRotatingObstacle(textures,-2150*factor,25,blockWidth,Math.PI,false));
                levels.put(number,obstacles);
                break;
            case 12:
                obstacles.clear();
                obstacles.add(new FadingObstacle(textures,-300*factor, 60, blockWidth, true));
                obstacles.add(new Obstacle(textures,-300*factor, 20, blockWidth, false));
                obstacles.add(new FadingObstacle(textures,0,-600*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,0,-900*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new FadingObstacle(textures,0,-1200*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new Obstacle(textures,-1500*factor, 60, blockWidth, false));
                obstacles.add(new Obstacle(textures,-1500*factor, 20, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-1800*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                obstacles.add(new Obstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-2100*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                obstacles.add(new FadingObstacle(textures,Resources.getSystem().getDisplayMetrics().widthPixels/2+20,-2400*factor,(Resources.getSystem().getDisplayMetrics().widthPixels)-(Resources.getSystem().getDisplayMetrics().widthPixels/2+20),blockWidth));
                levels.put(number,obstacles);
                break;
            case 13:
                obstacles.clear();
                obstacles.add(new FadingObstacle(textures,0,-300*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new FadingObstacle(textures,0,-600*factor,Resources.getSystem().getDisplayMetrics().widthPixels/2-20,blockWidth));
                obstacles.add(new FadingObstacle(textures,-900*factor, 60, blockWidth, false));
                obstacles.add(new FadingObstacle(textures,-900*factor, 20, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-1200*factor, 40, blockWidth));
                obstacles.add(new FadingObstacle(textures,-1500*factor, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-1500*factor, 20, blockWidth, false));
                obstacles.add(new FadingObstacle(textures,-1800*factor, 40, blockWidth));
                obstacles.add(new FadingObstacle(textures,-2100*factor, 50, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-2400*factor, 50, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-2700*factor, 60, blockWidth, true));
                obstacles.add(new FadingObstacle(textures,-2700*factor, 20, blockWidth, false));
                levels.put(number,obstacles);
                break;
            default:
                obstacles.clear();
                return obstacles;
        }
        return obstacles;
    }

}
