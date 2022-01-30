package com.example.dual.Dual.GameState;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.TileMap.Background;
import com.example.dual.Dual.TileMap.Obstacle;
import com.example.dual.Dual.TileMap.Textures;
import com.example.dual.Dual.TileMap.Player;


import com.example.dual.Dual.Main.Levels;
import com.example.dual.Dual.Main.Main;
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
    double widthFactor = 768.0/Resources.getSystem().getDisplayMetrics().widthPixels;

    public RunningState(GameStateManager gsm, Context context) {
        this.gsm = gsm;
        this.context = context;
        this.textures = this.gsm.textures;
        levels = new Levels(this.textures,350*widthFactor);
        try {
            bg = new Background(ContextCompat.getColor(context, R.color.black));
            player = new Player(this.textures, ContextCompat.getColor(context, R.color.cyan), ContextCompat.getColor(context, R.color.red), Math.PI, 350*widthFactor, 50*widthFactor);
            init();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {
        currentLevel = 1;
        loadLevel();
        restart();
    }

    public void loadLevel() {
        levels.setRotationSpeed(0.07/(16.66/frameTime));
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
            this.frameTime = this.gsm.getFrameTime();
            for (Obstacle obstacle : obstacles) {
                obstacle.setFrameTime(this.frameTime);
                obstacle.setPlayerHeight(player.getHeight());
                obstacle.update();
            }
            this.player.update();
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
				/*
				if((System.nanoTime()/1000000>this.choqueTime/1000000+1000)) {
					restart();
					//setPause();
					choqueState = false;
				}
				*/
            }
            else {
                this.bg.update();
                if(obstacles.size()>0 && obstacles.get(obstacles.size() - 1).getY()>Resources.getSystem().getDisplayMetrics().heightPixels) {
                    levelCompleted = true;
                    currentLevel++;
                    loadLevel();
                    restart();
                }
                if(collision(player)) {//DEATH
                    choqueState();
                    //restart();
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

    private boolean collision( Player p) {
        double circles [] = p.getCircles();
        for(Obstacle obstacle : obstacles) {
            if(obstacle.getCollision(circles[0],circles[1],circles[4])) {
                obstacle.appendCollision(circles[0],circles[1],false);
                return true;
            }
            if(obstacle.getCollision(circles[2],circles[3],circles[4])) {
                obstacle.appendCollision(circles[2],circles[3],true);
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
                if (event.getRawX() < Resources.getSystem().getDisplayMetrics().widthPixels * 0.5) {
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
