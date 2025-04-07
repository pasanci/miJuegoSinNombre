package com.example.mijuegosinnombre.GameState;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mijuegosinnombre.R;

import java.util.Arrays;

public abstract class GameState extends AppCompatActivity {

    protected GameStateManager gsm;
    protected Context context;
    public abstract void init();
    public abstract void update();
    public abstract void draw(Canvas canvas);
/*
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
*/
    public abstract void setPause();

    public abstract void notifyTouchEvent(MotionEvent event);

    public abstract void notifyBackPressed();

}
