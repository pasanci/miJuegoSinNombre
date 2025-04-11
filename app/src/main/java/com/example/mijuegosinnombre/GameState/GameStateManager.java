package com.example.mijuegosinnombre.GameState;

import static java.lang.Double.min;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.Display;
import android.view.MotionEvent;

import java.util.ArrayList;

import com.example.mijuegosinnombre.Main.Levels;
import com.example.mijuegosinnombre.TileMap.Textures;

public class GameStateManager {
    public static final String SAVEGAMESTR = "TestSavedLevel";
    public static final String SHOWFPSSTR = "ShowFPS";

    private ArrayList<GameState> gameStates;
    private int currentState;
    private long frameTime;
    private double scale = 1.0;
    private Context context;
    public static final boolean DEBUG = true;

    public static final int EXIT = -1;
    public static final int MENUSTATE = 0;
    public static final int OPTIONSSTATE = 1;
    public static final int RUNNINGSTATE = 2;
    public static final int LEVELSELECTIONSTATE = 3;
    protected Textures textures;

    private int width = 1440;
    private int height = 3200;
    private int actualWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int actualHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int topMargin = 0;
    private int bottomMargin = 0;
    private double ratio = min(width/actualHeight,height/actualHeight);

    private final SharedPreferences sharedPref;
    private final SharedPreferences.Editor editor;
    private Levels levels;
    private int currentLevel;
    private boolean showFPS;
    private Display display;

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void loadOptions() {
        this.showFPS = this.sharedPref.getBoolean(SHOWFPSSTR, false);
    }

    public SharedPreferences.Editor getEditor() {
        return this.editor;
    }

    public SharedPreferences getSharedPreferences() {
        return this.sharedPref;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
        if(currentLevel>sharedPref.getInt(SAVEGAMESTR, 1)) {
            editor.putInt(SAVEGAMESTR, currentLevel);
            editor.apply();
        }
    }

    public void setMaxLevel(int maxLevel) {
        editor.putInt(SAVEGAMESTR, maxLevel);
        editor.apply();
    }

    public int getMaxLevel() {
        return sharedPref.getInt(SAVEGAMESTR, 1);
    }

    public Levels getLevels() {
        return levels;
    }

    public void setLevels(Levels levels) {
        this.levels = levels;
    }

    public GameStateManager(Context context) {
        this.context = context;
        this.sharedPref = this.context.getSharedPreferences(SAVEGAMESTR, Context.MODE_PRIVATE);
        this.currentLevel = this.sharedPref.getInt(SAVEGAMESTR, 1);
        loadOptions();
        this.editor = this.sharedPref.edit();
        this.textures = new Textures(context);
        this.levels = new Levels(this, this.textures);
        this.gameStates = new ArrayList<GameState>();
        this.currentState = MENUSTATE;
        this.gameStates.add(new MenuState(this, context));
        this.gameStates.add(new OptionsState(this, context));
        this.gameStates.add(new RunningState(this, context));
        this.gameStates.add(new LevelSelection(this, context));
    }

    public void setState (int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void update() {
        gameStates.get(currentState).update();
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
        //this.currentState = 0;
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

    public int getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }

    public int getBottomMargin() {
        return bottomMargin;
    }

    public void setBottomMargin(int bottomMargin) {
        this.bottomMargin = bottomMargin;
    }

    public boolean getShowFPS() {
        return showFPS;
    }

    public void setShowFPS(boolean newVal) {
        this.showFPS = newVal;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }
}
