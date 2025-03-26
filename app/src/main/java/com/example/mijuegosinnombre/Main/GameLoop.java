package com.example.mijuegosinnombre.Main;

import com.example.mijuegosinnombre.GameState.GameStateManager;
import com.example.mijuegosinnombre.Main.Main;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.mijuegosinnombre.Main.Main;

public class GameLoop extends Thread{
    private Main game;
    private Thread thread;
    private boolean running;
    private SurfaceHolder surfaceHolder;
    private double averageUPS;
    private double averageFPS;
    private static final double MAX_UPS = 60;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;


    public GameLoop(Main game, SurfaceHolder surfaceHolder, Context context) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
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
            try{
                canvas = surfaceHolder.lockCanvas();
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
            sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
            if(sleepTime > 0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while(sleepTime < 0 && updateCount < MAX_UPS-1){
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis()-startTime;
                sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
            }

            elapsedTime = System.currentTimeMillis()-startTime;
            if(elapsedTime >= 1000){
                averageUPS = 1000 / updateCount;
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
}
