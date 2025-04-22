package com.example.mijuegosinnombre.TileMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.VectorDrawable;

import androidx.core.content.ContextCompat;

import com.example.mijuegosinnombre.GameState.GameStateManager;
import com.example.mijuegosinnombre.R;

public class TutorialObstacle extends Obstacle{

    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int BOTH = 2;

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    Textures textures;
    Bitmap tempBitmap;
    VectorDrawable touchDrawable;
    VectorDrawable rotateLeftDrawable;
    VectorDrawable rotateRightDrawable;

    protected Context context;
    protected GameStateManager gsm;
    private double playerX;
    private double playerY;
    private double playerD;
    private double previousAngle;
    private double totalAngle;
    private int step = 0;

    public TutorialObstacle(GameStateManager gsm, Textures textures) {
        this.gsm = gsm;
        this.textures = textures;
        this.playerD = this.gsm.getPlayer().getDiameter()/this.gsm.getWidth()*this.gsm.getActualWidth();
        this.playerX = this.gsm.getPlayer().getX()/this.gsm.getWidth()*this.gsm.getActualWidth();
        this.playerY = this.gsm.getPlayer().getY()/this.gsm.getHeight()*this.gsm.getActualHeight();
        step = 0;
        this.gsm.getPlayer().setAllowedMovement(Player.RIGHT);
        this.previousAngle = this.gsm.getPlayer().getAngle();
        this.totalAngle = 0;
    }

    public void update() {
        double angle1 = this.previousAngle;
        double angle2 = this.gsm.getPlayer().getAngle();
        double difference;
        if (angle1 > angle2)
        {
            if ((angle1 - angle2) > Math.PI)
            {
                difference = (2*Math.PI - angle1) + angle2;
            }
            else
            {
                difference = angle1 - angle2;
            }
        }
        else
        {
            if ((angle2 - angle1) > Math.PI)
            {
                difference = (2*Math.PI - angle2) + angle1;
            }
            else
            {
                difference = angle2 - angle1;
            }
        }
        this.totalAngle += difference;
        this.previousAngle = this.gsm.getPlayer().getAngle();
        if(totalAngle>2*Math.PI){
            advanceStep();
            this.totalAngle = 0;
        }
        //System.out.println(this.totalAngle);
    }

    private void advanceStep(){
        if(step==RIGHT){
            step = LEFT;
            this.gsm.getPlayer().setAllowedMovement(Player.LEFT);
        }
        else if(step==LEFT){
            step = BOTH;
            this.gsm.getPlayer().setAllowedMovement(Player.BOTH);
        }
    }

    public void draw(Canvas canvas) {
        if(step==RIGHT) {
            touchDrawable.setBounds(this.gsm.getActualWidth() / 2, this.gsm.getActualWidth() / 3, this.gsm.getActualWidth(), this.gsm.getActualWidth() / 3 + this.gsm.getActualWidth() / 2);
            touchDrawable.draw(canvas);
            rotateRightDrawable.setBounds((int) (this.playerX - this.playerD), (int) (this.playerY - this.playerD), (int) (this.playerX + this.playerD), (int) (this.playerY + this.playerD));
            rotateRightDrawable.draw(canvas);
        }
        else if (step==LEFT){
            touchDrawable.setBounds(0, this.gsm.getActualWidth() / 3, this.gsm.getActualWidth() / 2, this.gsm.getActualWidth() / 3 + this.gsm.getActualWidth() / 2);
            touchDrawable.draw(canvas);
            rotateLeftDrawable.setBounds((int) (this.playerX - this.playerD), (int) (this.playerY - this.playerD), (int) (this.playerX + this.playerD), (int) (this.playerY + this.playerD));
            rotateLeftDrawable.draw(canvas);
        }
        else{

        }
        Path path = new Path ();
        path.moveTo((float) this.gsm.getActualWidth() /2, 0);
        path.lineTo((float) this.gsm.getActualWidth() /2, this.gsm.getActualHeight());
        canvas.drawPath(path, paint);
    }

    public boolean getCollision(double cx, double cy, double radius) {
        return false;
    }

    public void setContext(Context context) {
        this.context = context;
        this.touchDrawable = (VectorDrawable) context.getResources().getDrawable(R.drawable.touch_vector);
        this.rotateLeftDrawable = (VectorDrawable) context.getResources().getDrawable(R.drawable.rotate_left_vector);
        this.rotateRightDrawable = (VectorDrawable) context.getResources().getDrawable(R.drawable.rotate_right_vector);
        touchDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.LIGHTEN);
        rotateLeftDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.DST_IN);
        rotateRightDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.DST_IN);
        paint.setColor(ContextCompat.getColor(context, R.color.white));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        this.paint.setAlpha((150));
        touchDrawable.setAlpha(150);
        rotateLeftDrawable.setAlpha(150);
        rotateRightDrawable.setAlpha(150);
        DashPathEffect effects = new DashPathEffect (new float [] { 40, 20}, 0);
        paint.setPathEffect(effects);
    }
}