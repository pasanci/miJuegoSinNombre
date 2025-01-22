package com.example.dual.Dual.Main;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.GameState.GameStateManager;
import com.example.dual.R;
import com.example.dual.Dual.Main.GameLoop;

public class Main extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Context context;
    //public double SCALE;
    private GameStateManager gsm;

    public Main(Context context) {
        super(context);
        gsm = new GameStateManager(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        //this.SCALE = height/(HEIGHT)*0.99;

        this.context = context;
        this.gameLoop = new GameLoop(this, surfaceHolder, context);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gsm.notifyTouchEvent(event);
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder, this.context);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        //super.draw(canvas);
        gsm.draw(canvas);
        //drawUPS(canvas);
        //drawFPS(canvas);
        //drawResolution(canvas);
    }

    public void update() {
        gsm.setFrameTime((long)gameLoop.getAverageUPS());
        gsm.update();
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100,100, paint);
    }

    public void drawResolution(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100,100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100,200, paint);
    }

    public void pause() {
        gameLoop.stopLoop();
    }

    public void notifyBackPressed() {
        gsm.notifyBackPressed();
    }
}
