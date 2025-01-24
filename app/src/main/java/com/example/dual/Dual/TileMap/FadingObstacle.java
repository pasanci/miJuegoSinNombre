package com.example.dual.Dual.TileMap;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.GameState.GameStateManager;
import com.example.dual.Dual.Main.Collision;
import com.example.dual.R;

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
        //System.out.println(y);
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
        /*
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(this.context, R.color.white));
        paint.setAlpha((int)((alphaValue-0.2)*255));
        canvas.drawRect ((int)x, (int)y, (int) (x + width), (int) (y + height), paint);
        paint.setAlpha(100);
        */
        int fx = (int) (this.x/this.gsm.getWidth()*this.gsm.getActualWidth());
        int fy = (int) (this.y/this.gsm.getHeight()*this.gsm.getActualHeight());
        int fWidth = (int) (this.width/this.gsm.getWidth()*this.gsm.getActualWidth());
        int fHeight = (int) (this.height/this.gsm.getHeight()*this.gsm.getActualHeight());

        Rect imageBounds = new Rect(fx, fy, (fx + fWidth), (fy + fHeight));
        textures.marble.setBounds(imageBounds);
        textures.marble.setAlpha((int)((alphaValue)*255));
        System.out.println((int)((alphaValue)*255));
        textures.marble.draw(canvas);
    }
}
