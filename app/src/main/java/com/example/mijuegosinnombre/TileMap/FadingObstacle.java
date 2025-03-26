package com.example.mijuegosinnombre.TileMap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import androidx.core.content.ContextCompat;

import com.example.mijuegosinnombre.GameState.GameStateManager;
import com.example.mijuegosinnombre.Main.Collision;
import com.example.mijuegosinnombre.R;

public class FadingObstacle extends Obstacle{
    double detectionDistance = 1200.0;
    double fadeDistance = 500.0;
    float alphaValue = 1.0f;

    public FadingObstacle(GameStateManager gsm, Textures textures, double x, double y, double width, double length) {
        super(gsm, textures,x, y, width, length);
    }

    public FadingObstacle(GameStateManager gsm, Textures textures, double y, int percentajeFree, double length) {
        super(gsm, textures,y, percentajeFree, length);
    }

    public FadingObstacle(GameStateManager gsm, Textures textures, double y, int percentaje, double length, boolean side) {//false = left
        super(gsm, textures,y, percentaje, length, side);
    }

    public void update() {
        super.update();
        if(!collisioned) {
            double distance = playerHeight - y;
            if(distance < detectionDistance && distance > fadeDistance) {
                alphaValue = (float) ((distance-fadeDistance)/(detectionDistance-fadeDistance));
            }
            else if(distance <= fadeDistance){
                alphaValue = 0.0f;
            }
        }
        else { //unfade
            collisionY-=resetSpeed;
            double distance = playerHeight - collisionY;
            if(distance < detectionDistance && distance > fadeDistance) {
                alphaValue = (float) ((distance-fadeDistance)/(detectionDistance-fadeDistance));
            }
            else if(distance <= fadeDistance){
                alphaValue = 0.0f;
            }
        }
    }

    public void restart() {
        super.restart();
        this.alphaValue = 1.0f;
        this.collisioned = false;
    }

    @Override
    public void draw(Canvas canvas) {
        int prev = this.paint.getAlpha();
        this.paint.setAlpha((int)((alphaValue)*255));
        super.draw(canvas);
        this.paint.setAlpha(prev);
    }
}
