package com.example.dual.Dual.TileMap;

import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.Main.Collision;
import com.example.dual.R;

public class FadingRotatingObstacle extends RotatingObstacle{
    double detectionDistance = 800.0;
    double fadeDistance = 300.0;
    float alphaValue = 1.0f;

    public FadingRotatingObstacle(Textures textures, double x, double y, double width, double length, double initialAngle, double rotationSpeed) {
        super(textures,x, y, width, length, initialAngle , rotationSpeed);
    }

    public FadingRotatingObstacle(Textures textures, double y, int percentajeFree, double length, double initialAngle, double rotationSpeed) {
        super(textures,y, percentajeFree, length, initialAngle, rotationSpeed);
    }

    public FadingRotatingObstacle(Textures textures, double y, int percentaje, double length, boolean side, double initialAngle, double rotationSpeed) {//false = left
        super(textures,y, percentaje, length, side, initialAngle, rotationSpeed);
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
            this.angle = normalizeAngle(this.angle);
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
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(this.context, R.color.white));
        paint.setAlpha((int)((alphaValue-0.2)*255));
        canvas.save();
        canvas.rotate((float)(angle*180 /Math.PI),(float)(x+width/2.0),(float)(y+height/2.0));
        canvas.drawRect ((int)(x), (int) (y), (int) (x + width), (int) (y + height), paint);
        canvas.restore();
        paint.setAlpha(100);
    }
}