package com.example.dual.Dual.GameState;

import static java.lang.Double.min;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import com.example.dual.Dual.TileMap.Textures;

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;
    private long frameTime;
    private double scale = 1.0;
    private Context context;

    public static final int MENUSTATE = 0;
    public static final int OPTIONSSTATE = 1;
    public static final int RUNNINGSTATE = 2;
    protected Textures textures;
    protected boolean rotatingLevels = true;

    private int width = 1440;
    private int height = 3200;
    private int actualWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int actualHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private double ratio = min(width/actualHeight,height/actualHeight);

    public GameStateManager(Context context) {
        textures = new Textures(context);
        gameStates = new ArrayList<GameState>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this, context));
        gameStates.add(new OptionsState(this, context));
        gameStates.add(new RunningState(this, context));
    }

    public void setState (int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update() {
        gameStates.get(currentState).update();
    }

    public boolean getRotatingLevels() {
        return this.rotatingLevels;
    }

    public void setRotatingLevels(boolean set) {
        this.rotatingLevels = set;
    }

    public void draw(Canvas canvas) {
        gameStates.get(currentState).draw(canvas);
    }

/*
    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }
*/

    public double getScale() {
        return this.scale;
    }

    public void setScale(double scale) {

        this.scale = (double)Math.round(scale * 10d) / 10d;
    }

    public long getFrameTime() {
        return this.frameTime;
    }

    public void setFrameTime(long frameTime) {
        this.frameTime = frameTime;
    }

    public void setPause() {
        gameStates.get(currentState).setPause();
    }

    public void notifyTouchEvent(MotionEvent event) {
        gameStates.get(currentState).notifyTouchEvent(event);
    }

    public void notifyBackPressed() {
        gameStates.get(currentState).notifyBackPressed();
        this.currentState = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getActualWidth() {
        return actualWidth;
    }

    public int getActualHeight() {
        return actualHeight;
    }
}
