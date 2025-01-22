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

public class OptionsState extends GameState{

    private Background bg;

    private int currentChoice = 0;
    private double scale;
    private String[] options = {
            "Scale: " + scale,
            "Niveles giran"
    };

    private Color titleColor;
    private Font titleFont;
    private Font font;

    float [] optionsrange;
    float startY = 280;

    public OptionsState(GameStateManager gsm, Context context) {
        this.gsm = gsm;
        this.context = context;
        this.optionsrange = new float [options.length + 1];
        for(int i=0; i<options.length; i++) {
            this.optionsrange[i] = (startY +i*100+50);
        }
        this.optionsrange[optionsrange.length-1] = (startY +optionsrange.length*100-50);
        try {
            bg = new Background(ContextCompat.getColor(context, R.color.black));
            init();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void init() {
        this.scale = gsm.getScale();
        options[0]="Scale: " + ((float)this.scale);
    }
    public void update() {
        bg.update();
    }

    private void select() {
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
            if(i == 1){
                if(this.gsm.getRotatingLevels()) {
                    canvas.drawText(options[1] + "Yes", 145, (startY + 100 +i*100), paint);
                }
                else{
                    canvas.drawText(options[1] + "No", 145, (startY + 100 +i*100), paint);
                }
            }
            else{
                canvas.drawText(options[i], 145,(startY + 100 +i*100), paint);
            }
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
        if(k == KeyEvent.VK_LEFT) {
            if(currentChoice == 0 && scale>0.1) {
                scale -= 0.1;
                scale = (double)Math.round(scale * 10d) / 10d;
                options[0]="Scale: " + ((float)this.scale);
            }
        }
        if(k == KeyEvent.VK_RIGHT) {
            if(currentChoice == 0) {
                scale += 0.1;
                options[0]="Scale: " + ((float)this.scale);
            }
        }
        if(k == KeyEvent.VK_ESCAPE) {
            gsm.setState(GameStateManager.MENUSTATE);
        }
        if(k == KeyEvent.VK_ENTER) {
            gsm.setScale((float)this.scale);
        }
    }

    public void keyReleased(int k) {

    }
*/

    public void setPause() {

    }

    @Override
    public void notifyTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                boolean selection = false;
                for(int i=0; i<optionsrange.length-1; i++) {
                    if(event.getRawY()>optionsrange[i] && event.getRawY()<optionsrange[i+1]){
                        selection = true;
                        if(this.currentChoice == i){
                            select();
                        }
                        else {
                            this.currentChoice = i;
                        }
                        break;
                    }
                }
                if(!selection) {
                    if (event.getRawX() < Resources.getSystem().getDisplayMetrics().widthPixels * 0.5) {
                        if (currentChoice == 0 && scale > 0.1) {
                            scale -= 0.1;
                            scale = (double) Math.round(scale * 10d) / 10d;
                            options[0] = "Scale: " + ((float) this.scale);
                        }
                        if (currentChoice == 1) {
                            this.gsm.setRotatingLevels(false);
                        }
                    } else {
                        if (currentChoice == 0) {
                            scale += 0.1;
                            options[0] = "Scale: " + ((float) this.scale);
                        }
                        if (currentChoice == 1) {
                            this.gsm.setRotatingLevels(true);
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void notifyBackPressed() {
        gsm.setScale((float)this.scale);
    }
}
