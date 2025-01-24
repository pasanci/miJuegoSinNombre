package com.example.dual.Dual.GameState;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.fonts.Font;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.TileMap.Background;
import com.example.dual.R;

public class MenuState extends GameState{

    private Background bg;

    private int currentChoice = 0;
    private String[] options = {
            "Start",
            "Options",
            "Quit"
    };


    float [] optionsrange;
    float startY = 280;

    private Color titleColor;
    private Font titleFont;
    private Font font;

    public MenuState(GameStateManager gsm, Context context) {
        this.gsm = gsm;
        this.context = context;
        this.optionsrange = new float [options.length + 1];
        for(int i=0; i<options.length; i++) {
            this.optionsrange[i] = (startY +i*100+50);
        }
        this.optionsrange[optionsrange.length-1] = (startY +optionsrange.length*100-50);
        try {
            bg = new Background(gsm, ContextCompat.getColor(context, R.color.black));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void init() {}
    public void update() {
        bg.update();
    }

    private void select() {
        if(currentChoice == 0) {//start
            gsm.setState(GameStateManager.RUNNINGSTATE);
        }
        if(currentChoice == 1) {//help
            gsm.setState(GameStateManager.OPTIONSSTATE);
        }
        if(currentChoice == 2) {//quit
            System.exit(0);
        }
    }

    public void draw(Canvas canvas) {
        bg.draw(canvas);
        Paint paint = new Paint();
        int color = ContextCompat.getColor(this.context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("El jueguito", 80, startY, paint);

        for(int i=0; i<options.length; i++) {
            if(i == currentChoice) {
                color = ContextCompat.getColor(this.context, R.color.white);
            }
            else {
                color = ContextCompat.getColor(this.context, R.color.red);
            }
            paint.setColor(color);
            canvas.drawText(options[i], 145,(startY + 100 +i*100), paint);
        }
    }

/*
    public void keyPressed(int k) {
        if(k==KeyEvent.VK_ENTER) {
            select();
        }
        if(k == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice<0) {
                currentChoice = options.length -1;
            }
        }
        if(k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice>options.length-1) {
                currentChoice = 0;
            }
        }
    }

    public void keyReleased(int k) {

    }
*/

    public void setPause() {

    }


    public void notifyTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(int i=0; i<optionsrange.length-1; i++) {
                    if(event.getRawY()>optionsrange[i] && event.getRawY()<optionsrange[i+1]){
                        if(this.currentChoice == i){
                            select();
                        }
                        else {
                            this.currentChoice = i;
                        }
                        break;
                    }
                }
                break;
        }
    }

    @Override
    public void notifyBackPressed() {
    }

}
