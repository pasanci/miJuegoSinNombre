package com.example.dual.Dual.GameState;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.TileMap.Background;
import com.example.dual.R;

public class LevelSelection extends GameState {

    private Background bg;
    private int width;
    private int height;
    Paint textPaint;

    LevelSelection(GameStateManager gsm, Context context){
        this.gsm = gsm;
        this.context = context;
        try {
            bg = new Background(gsm, ContextCompat.getColor(context, R.color.black));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        this.width = this.gsm.getActualWidth();
        this.height = this.gsm.getActualHeight();
        this.textPaint = new Paint();
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Canvas canvas) {
        bg.draw(canvas);
        int section = (this.width/3);
        int currentY = 0;
        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.white));
        for(int i = 0; i<100; i++) {
            int pos = i%3;
            canvas.drawRect((float) (pos*section)+(section/8), (float) currentY+(section/8), (float) ((pos+1)*section)-(section/8), (float) currentY+section-(section/8), paint);

            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setColor(ContextCompat.getColor(context, R.color.black));
            textPaint.setTextSize((float)section);
            canvas.drawText(""+i, (((pos*section)+((pos+1)*section))/2), (currentY+section)-(section/2)-((textPaint.descent() + textPaint.ascent()) / 2), textPaint);
            if(pos == 2){
                currentY+=section;
            }
        }
    }

    @Override
    public void setPause() {

    }

    @Override
    public void notifyTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //System.out.println(event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println(event.getRawY());
                gsm.setState(GameStateManager.RUNNINGSTATE);
                break;
        }
    }

    @Override
    public void notifyBackPressed() {

    }
}
