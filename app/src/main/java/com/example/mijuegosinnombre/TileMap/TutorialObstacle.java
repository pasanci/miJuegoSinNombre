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

    public TutorialObstacle(GameStateManager gsm, Textures textures) {
        this.gsm = gsm;
        this.textures = textures;
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        touchDrawable.setBounds(this.gsm.getActualWidth()/2, this.gsm.getActualWidth()/3, this.gsm.getActualWidth(), this.gsm.getActualWidth()/3+this.gsm.getActualWidth()/2);
        touchDrawable.draw(canvas);
        rotateRightDrawable.setBounds(this.gsm.getActualWidth()/2, this.gsm.getActualWidth()/3+this.gsm.getActualWidth()/2, this.gsm.getActualWidth(), this.gsm.getActualWidth()/3+2*this.gsm.getActualWidth()/2);
        rotateRightDrawable.draw(canvas);
        touchDrawable.setBounds(0, this.gsm.getActualWidth()/3, this.gsm.getActualWidth()/2, this.gsm.getActualWidth()/3+this.gsm.getActualWidth()/2);
        touchDrawable.draw(canvas);
        rotateLeftDrawable.setBounds(0, this.gsm.getActualWidth()/3+this.gsm.getActualWidth()/2, this.gsm.getActualWidth()/2, this.gsm.getActualWidth()/3+2*this.gsm.getActualWidth()/2);
        rotateLeftDrawable.draw(canvas);
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
        rotateLeftDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.LIGHTEN);
        rotateRightDrawable.setColorFilter(Color.BLACK, PorterDuff.Mode.LIGHTEN);
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