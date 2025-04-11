package com.example.mijuegosinnombre.Main;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.view.SurfaceHolder;

import com.example.mijuegosinnombre.GameState.GameStateManager;

public class GameLoop extends Thread{
    private Main game;
    private Thread thread;
    private boolean running;
    private SurfaceHolder surfaceHolder;
    private double averageUPS;
    private double averageFPS;
    private static final double MAX_UPS = 120;
    private long lastFrameTime;
    private GameStateManager gsm;
    private float refreshPeriod;

    public GameLoop(Main game, SurfaceHolder surfaceHolder, Context context, GameStateManager gsm) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
        this.gsm = gsm;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        running = true;
        start();
    }

    @Override
    public void run(){
        super.run();

        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while(running){
            float refreshRate = -1;
            if(this.gsm.getDisplay()==null){
                refreshRate = (float) MAX_UPS;
            }
            else{
                refreshRate = this.gsm.getDisplay().getRefreshRate();
            }
            refreshPeriod = (float) (1E+3/refreshRate);
            try{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    canvas = surfaceHolder.lockHardwareCanvas();
                }
                else{
                    canvas = surfaceHolder.lockCanvas();
                }
                synchronized(surfaceHolder) {
                    game.update();
                    updateCount++;
                    game.draw(canvas);
                }
            }
            catch(IllegalArgumentException e) {
                e.printStackTrace();
            }
            finally{
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            elapsedTime = System.currentTimeMillis()-startTime;
            sleepTime = (long) (updateCount*refreshPeriod - elapsedTime);
            if(sleepTime > 0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            elapsedTime = System.currentTimeMillis()-startTime;
            if(elapsedTime > 1000){
                lastFrameTime = (long) refreshPeriod;
                averageUPS = (double) 1000 / updateCount;
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }

    public void stopLoop() {
        this.running = false;
        try{
            join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public long getLastFrameTime() {
        return lastFrameTime;
    }
}
