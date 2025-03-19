package com.example.dual.Dual.GameState;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Pair;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.TileMap.Background;
import com.example.dual.R;
import static com.example.dual.Dual.GameState.GameStateManager.SAVEGAMESTR;

import java.util.Random;

public class LevelSelection extends GameState {

    private Background bg;
    private int width;
    private int height;
    private int inicioDespY;
    private int desplazamiento;
    private boolean validDesplazamiento;
    private boolean validClick;
    private Paint textPaint;
    private int numberLevels;
    private int maxDesp;
    private int section;
    private int maxLevel;
    private SharedPreferences sharedPref;
    private Paint paint;
    private Paint paintB;
    private Paint paintR;
    private Bitmap tempBitmap;
    private Canvas tempImage;
    private Pair<Integer,Integer> clickPos;
    private Bitmap splash;

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
        this.desplazamiento = 0;
        this.inicioDespY = 0;
        this.validDesplazamiento = false;
        this.validClick = false;
        int section = (this.width/3);
        this.numberLevels = gsm.getLevels().getLevelsNumber();
        int num = ((numberLevels/3));
        if((numberLevels%3)!=0){
            num++;
        }
        this.maxDesp = section*num;
        this.maxDesp = this.maxDesp-this.gsm.getActualHeight();
        this.section = (this.width/3);
        sharedPref = this.context.getSharedPreferences(SAVEGAMESTR, Context.MODE_PRIVATE);
        this.paint = new Paint();
        paintB = new Paint();
        paintR = new Paint();
    }

    @Override
    public void init() {
        this.desplazamiento = 0;
        this.validDesplazamiento = false;
        this.validClick = false;
        this.maxLevel = sharedPref.getInt(SAVEGAMESTR, 1);
        paintB.setColor(ContextCompat.getColor(context, R.color.cyan));
        paintR.setColor(ContextCompat.getColor(context, R.color.red));
        this.tempBitmap = Bitmap.createScaledBitmap(gsm.textures.marbleBitmap, section-(section/4), section-(section/4), true);
        this.tempImage = new Canvas(tempBitmap);
        clickPos = null;
    }

    @Override
    public void update() {
        bg.update();
    }

    @Override
    public void draw(Canvas canvas) {
        bg.draw(canvas);
        int currentY = 0;
        int prev = this.paint.getAlpha();
        paint.setColor(ContextCompat.getColor(context, R.color.white));
        for(int i = 0; i<numberLevels; i++) {
            if(i==this.maxLevel) {
                this.paint.setAlpha(80);
            }
            int pos = i%3;
            canvas.drawBitmap(tempBitmap,(pos*section)+(section/8),currentY+(section/8)-this.desplazamiento,paint);
            //canvas.drawRect((float) (pos*section)+(section/8), (float) currentY+(section/8)-this.desplazamiento, (float) ((pos+1)*section)-(section/8), (float) currentY+section-(section/8)-this.desplazamiento, paint);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setColor(ContextCompat.getColor(context, R.color.black));
            textPaint.setTextSize((float)(section*0.7));
            canvas.drawText("" + (i + 1), (((pos * section) + ((pos + 1) * section)) / 2), (currentY + section) - (section / 2) - ((textPaint.descent() + textPaint.ascent()) / 2) - this.desplazamiento, textPaint);
            if(pos == 2){
                currentY+=section;
            }
            if(clickPos!=null) {
                //this.tempImage.drawBitmap(b,((int)dx)-b.getWidth()/2,((int)dy)-b.getHeight()/2,paintB);
                canvas.drawBitmap(splash, clickPos.first-splash.getWidth()/2, clickPos.second-splash.getHeight()/2, paintB);
            }
        }
        this.paint.setAlpha(prev);
    }

    @Override
    public void setPause() {

    }

    @Override
    public void notifyTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if(this.validDesplazamiento) {
                    this.desplazamiento = (int) (this.desplazamiento + (this.inicioDespY - event.getRawY()));
                    if (this.desplazamiento > this.maxDesp) {
                        this.desplazamiento = this.maxDesp;
                    }
                    if (this.desplazamiento < 0) {
                        this.desplazamiento = 0;
                    }
                }
                this.inicioDespY = (int) event.getRawY();
                this.validDesplazamiento = true;
                break;
            case MotionEvent.ACTION_UP:
                if(!this.validDesplazamiento && this.validClick) {
                    int x = (int) (event.getRawX() / this.section);
                    int y = (int) ((event.getRawY() + this.desplazamiento) / this.section);
                    int n = ((y * 3) + (x + 1));
                    if(n<=this.maxLevel) {
                        gsm.setCurrentLevel(n);
                        gsm.setState(GameStateManager.RUNNINGSTATE);
                    }
                    clickPos = clickPos.create((int) event.getRawX(),(int) event.getRawY());
                    splash = gsm.textures.splashesB[new Random().nextInt((3)+1)];
                }
                this.validDesplazamiento = false;
                this.validClick = true;
        }
    }

    @Override
    public void notifyBackPressed() {

    }
}
