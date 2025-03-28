package com.example.mijuegosinnombre.GameState;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public abstract class GameState extends AppCompatActivity {

    protected GameStateManager gsm;
    protected Context context;

    public abstract void init();
    public abstract void update();
    public abstract void draw(Canvas g);
/*
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
*/
    public abstract void setPause();

    public abstract void notifyTouchEvent(MotionEvent event);

    public abstract void notifyBackPressed();

}
