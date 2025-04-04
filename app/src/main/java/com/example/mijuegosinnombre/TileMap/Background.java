package com.example.mijuegosinnombre.TileMap;

import com.example.mijuegosinnombre.GameState.GameStateManager;
import com.example.mijuegosinnombre.Main.GameLoop;
import com.example.mijuegosinnombre.Main.Main;
import com.example.mijuegosinnombre.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Background {

    private int color;
    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;
    private GameStateManager gsm;

    public Background(GameStateManager gsm, int c) {
        this.gsm = gsm;
        try {
            color = c;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % this.gsm.getWidth();
        this.y = (y * moveScale) % this.gsm.getHeight();
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(color);
        //g.setColor(this.color);
        //g.fillRect (0, 0, (int)x + GameLoop.WIDTH, (int)y + GameLoop.HEIGHT);
    }
}
