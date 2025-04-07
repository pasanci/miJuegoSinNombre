package com.example.mijuegosinnombre.TileMap;

import com.example.mijuegosinnombre.GameState.GameStateManager;
import android.graphics.Canvas;

public class Background {

    private int color;
    private GameStateManager gsm;

    public Background(GameStateManager gsm, int c) {
        this.gsm = gsm;
        this.color = c;
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        canvas.drawColor(this.color);
    }
}
