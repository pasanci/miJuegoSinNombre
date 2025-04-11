package com.example.mijuegosinnombre.Main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import com.example.mijuegosinnombre.GameState.GameStateManager;
import com.example.mijuegosinnombre.R;

import java.text.DecimalFormat;

public class Main extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private Context context;
    //public double SCALE;
    private GameStateManager gsm;

    public Main(Context context, Display display) {
        super(context);
        gsm = new GameStateManager(context);
        int topMargin = 0;
        int bottomMargin = 0;
        gsm.setDisplay(display);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if(display.getCutout()!=null) {
                topMargin = display.getCutout().getSafeInsetTop();
                bottomMargin = display.getCutout().getSafeInsetBottom();
            }
        }
        gsm.setTopMargin(topMargin);
        gsm.setBottomMargin(bottomMargin);
        gsm.setState(GameStateManager.MENUSTATE);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
        this.gameLoop = new GameLoop(this, surfaceHolder, context, this.gsm);
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
            gameLoop = new GameLoop(this, surfaceHolder, this.context, this.gsm);
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
        super.draw(canvas);
        gsm.draw(canvas);
        if(gsm.getShowFPS()) {
            drawUPS(canvas);
            drawFPS(canvas);
        }
    }

    public void update() {
        gsm.setFrameTime((long)gameLoop.getLastFrameTime());
        //gsm.setFrameTime((long)gameLoop.getLastFrameTime());
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

    public void drawFPS(Canvas canvas) {
        DecimalFormat df = new DecimalFormat("#.##");
        String averageFPS = df.format(gameLoop.getAverageFPS());
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
