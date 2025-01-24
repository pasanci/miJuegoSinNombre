package com.example.dual.Dual.GameState;

import static java.util.Collections.min;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.TileMap.Background;
import com.example.dual.Dual.TileMap.Obstacle;
import com.example.dual.Dual.TileMap.Textures;
import com.example.dual.Dual.TileMap.Player;


import com.example.dual.Dual.Main.Levels;
import com.example.dual.R;

import java.util.ArrayList;
import java.util.List;

public class RunningState extends GameState{

    private Background bg;
    private Player player;
    Textures textures;
    private Levels levels;
    private List<Obstacle> obstacles = new ArrayList<Obstacle>();
    private int currentLevel;
    private boolean levelCompleted = false;
    private long frameTime = (long) 16.0;
    private long choqueTime = System.nanoTime();
    private boolean choqueState = false;
    private boolean pause = false;
    private Context context;

    public RunningState(GameStateManager gsm, Context context) {
        this.gsm = gsm;
        this.context = context;
        this.textures = this.gsm.textures;
        this.levels = new Levels(this.gsm, this.textures);
        try {
            bg = new Background(this.gsm, ContextCompat.getColor(context, R.color.black));
            player = new Player(this.gsm, this.textures, ContextCompat.getColor(context, R.color.cyan), ContextCompat.getColor(context, R.color.red), Math.PI, 300, 50);
            init();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        currentLevel = 4;
        loadLevel();
        restart();
    }

    public void loadLevel() {
        this.pause = false;
        levels.setRotationSpeed(0.07/(16.66/frameTime));
        if(!this.gsm.getRotatingLevels() && (currentLevel==4 || currentLevel==11)){
            currentLevel++;
        }
        obstacles = levels.loadLevel(currentLevel);
    }

    public void restart() {
        player.setPosition(50,80,Math.PI,currentLevel);
        player.setWaitingRestart(false);
        obstacles = levels.restartLevel();
        for (Obstacle obstacle : obstacles) {
            obstacle.setContext(this.context);
        }
        if(obstacles.size()==0) {
            levelCompleted = true;
        }
        else {
            levelCompleted = false;
        }
    }

    public void reset() {
        player.setPosition(50,80,Math.PI,currentLevel);
        player.setWaitingRestart(true);
        obstacles = levels.resetLevel();
        if(obstacles.size()==0) {
            levelCompleted = true;
        }
        else {
            levelCompleted = false;
        }
    }

    public void update() {
        if(!pause ) {
            if(choqueState) {
                boolean notReseting = true;
                for (Obstacle obstacle : obstacles) {
                    if(obstacle.resetting()) {
                        notReseting = false;
                    }
                }
                if(notReseting) {
                    restart();
                    choqueState = false;
                }
            }
            else {
                this.frameTime = this.gsm.getFrameTime();
                for (Obstacle obstacle : obstacles) {
                    obstacle.setFrameTime(this.frameTime);
                    obstacle.setPlayerHeight(player.getHeight());
                    obstacle.update();
                }
                this.player.update();
                this.bg.update();
                List<Double> obsY = new ArrayList<Double>();
                for (Obstacle obstacle : obstacles) {
                    obsY.add(obstacle.getY());
                }
                if(obstacles.size()>0 && min(obsY)>this.gsm.getHeight()+player.getDiameter()) {
                    levelCompleted = true;
                    currentLevel++;
                    loadLevel();
                    restart();
                }
                if(collision()) {//DEATH
                    //choqueState();
                    this.pause = true;
                }
            }
        }
    }

    public void choqueState() {
        this.choqueTime = System.nanoTime();
        choqueState = true;
        for(Obstacle obstacle : obstacles) {
            obstacle.collisioned();
        }
        reset();
    }

    private boolean collision() {
        //double circles [] = p.getCircles();
        double d = (player.getDiameter()/this.gsm.getWidth()*this.gsm.getActualWidth());
        double x = (player.getX()/this.gsm.getWidth()*this.gsm.getActualWidth());
        double y = (player.getY()/this.gsm.getHeight()*this.gsm.getActualHeight());
        double angle = player.getAngle();
        double x1 = (d * Math.cos(angle) + x);
        double y1 = (d * Math.sin(angle) + y);
        double x2 = (d * Math.cos(Math.PI+angle) + x);
        double y2 = (d * Math.sin(Math.PI+angle) + y);
        double fBallRad = ((2*player.getBallRadius())/this.gsm.getWidth()*this.gsm.getActualWidth());
        for(Obstacle obstacle : obstacles) {
            if(obstacle.getCollision(x1,y1,fBallRad)) {
                obstacle.appendCollision(x1,y1,false);
                return true;
            }
            if(obstacle.getCollision(x2,y2,fBallRad)) {
                obstacle.appendCollision(x2,y2,true);
                return true;
            }
        }
        return false;
    }

    public boolean intersects(double cx, double cy, double radius, double x1, double y1, double x2, double y2)
    {
        double closestX = (cx < x1 ? x1 : (cx > x2 ? x2 : cx));
        double closestY = (cy < y1 ? y1 : (cy > y2 ? y2 : cy));
        double dx = closestX - cx;
        double dy = closestY - cy;
        return ( dx * dx + dy * dy ) <= radius * radius;
    }

    public void draw(Canvas canvas) {
        bg.draw(canvas);
        player.setFrameTime(this.frameTime);
        player.draw(canvas, this.context);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(canvas);
        }

        if(levelCompleted) {

        }
    }

    public void drawDebug(Canvas canvas) {
        bg.draw(canvas);
        player.setFrameTime(this.frameTime);
        player.draw(canvas, this.context);
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.purple_200));
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(canvas);
            double x1 = (obstacle.getX()/this.gsm.getWidth()*this.gsm.getActualWidth());
            double y1 = (obstacle.getY()/this.gsm.getHeight()*this.gsm.getActualHeight());
            double x2 = ((obstacle.getX()+obstacle.getWidth())/this.gsm.getWidth()*this.gsm.getActualWidth());
            double y2 = ((obstacle.getY()+obstacle.getHeight())/this.gsm.getHeight()*this.gsm.getActualHeight());
            canvas.drawRect((float) x1,(float) y1,(float) x2,(float) y2,paint);
        }


        double d = (player.getDiameter()/this.gsm.getWidth()*this.gsm.getActualWidth());
        double x = (player.getX()/this.gsm.getWidth()*this.gsm.getActualWidth());
        double y = (player.getY()/this.gsm.getHeight()*this.gsm.getActualHeight());
        double angle = player.getAngle();
        double x1 = (d * Math.cos(angle) + x);
        double y1 = (d * Math.sin(angle) + y);
        double x2 = (d * Math.cos(Math.PI+angle) + x);
        double y2 = (d * Math.sin(Math.PI+angle) + y);
        double fBallDiam = ((2*player.getBallRadius())/this.gsm.getWidth()*this.gsm.getActualWidth());
        canvas.drawCircle((int)x1, (int)y1, (float) fBallDiam, paint);
        canvas.drawCircle((int)x2, (int)y2, (float) fBallDiam, paint);

        if(levelCompleted) {
            //b.draw(g);

            //canvas.drawImage(this.textures.chikito, 0, 0, Main.WIDTH, (int)(((double)Main.WIDTH/this.textures.chikito.getWidth())*this.textures.chikito.getHeight()), null);
        }
    }

/*
    public void keyPressed(int k) {
        if(k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A) {
            player.setAngleLeft(true);
        }
        if(k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D) {
            player.setAngleRight(true);
        }
        if(k == KeyEvent.VK_R) {
            levelCompleted = false;
            restart();
        }
        if(k == KeyEvent.VK_ESCAPE) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if(k == KeyEvent.VK_N) {
            currentLevel++;
            loadLevel();
            restart();
        }
        if(k == KeyEvent.VK_P) {
            pause = !pause;
        }
    }

    public void keyReleased(int k) {
        if(k == KeyEvent.VK_LEFT || k == KeyEvent.VK_A) {
            player.setAngleLeft(false);
        }
        if(k == KeyEvent.VK_RIGHT || k == KeyEvent.VK_D) {
            player.setAngleRight(false);
        }
    }
 */

    public void setPause() {
        pause = !pause;
    }

    @Override
    public void notifyTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (event.getRawX() < this.gsm.getActualWidth() * 0.5) {
                    player.setAngleLeft(true);
                } else {
                    player.setAngleRight(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                player.setAngleLeft(false);
                player.setAngleRight(false);
        }
    }

    @Override
    public void notifyBackPressed() {

    }

}
