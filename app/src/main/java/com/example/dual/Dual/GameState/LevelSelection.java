package com.example.dual.Dual.GameState;

import static com.example.dual.Dual.GameState.GameStateManager.SAVEGAMESTR;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Pair;
import android.view.MotionEvent;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.TileMap.Background;
import com.example.dual.R;

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
    private Paint paintSplash;
    private Paint paintB;
    private Paint paintR;
    private Bitmap tempBitmap;
    private Pair<Integer,Integer> clickPos;
    private Bitmap splash;
    private Random random;
    private long  transitionTime;

    LevelSelection(GameStateManager gsm, Context context){
        this.gsm = gsm;
        this.context = context;
        try {
            this.bg = new Background(gsm, ContextCompat.getColor(context, R.color.black));
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
        this.sharedPref = this.context.getSharedPreferences(SAVEGAMESTR, Context.MODE_PRIVATE);
        this.paint = new Paint();
        this.paintB = new Paint();
        this.paintR = new Paint();
        this.random = new Random();
        paintB.setColor(ContextCompat.getColor(context, R.color.cyan));
        paintR.setColor(ContextCompat.getColor(context, R.color.red));
        init();
    }

    @Override
    public void init() {
        this.transitionTime = -1;
        this.desplazamiento = 0;
        this.validDesplazamiento = false;
        this.maxLevel = sharedPref.getInt(SAVEGAMESTR, 1);
        this.tempBitmap = Bitmap.createScaledBitmap(gsm.textures.marbleBitmap, section-(section/4), section-(section/4), true);
        clickPos = null;
    }

    @Override
    public void update() {
        bg.update();
        if(this.transitionTime>0 && this.transitionTime<System.currentTimeMillis()){
            gsm.setState(GameStateManager.RUNNINGSTATE);
        }
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
            if(clickPos!=null && gsm.getCurrentLevel()-1 == i) {
                Bitmap b = tempBitmap.copy(tempBitmap.getConfig(),true);
                Canvas splashCanvas = new Canvas(b);
                splashCanvas.drawBitmap(splash, (clickPos.first-pos*section-(section/8)) - splash.getWidth()/2, (clickPos.second-currentY-this.desplazamiento-(section/8))-splash.getHeight()/2, paintSplash);
                //splashCanvas.drawBitmap(splash, 0, 0, paintSplash);
                canvas.drawBitmap(b,(pos*section)+(section/8),currentY+(section/8)-this.desplazamiento,paint);
            }
            //canvas.drawRect((float) (pos*section)+(section/8), (float) currentY+(section/8)-this.desplazamiento, (float) ((pos+1)*section)-(section/8), (float) currentY+section-(section/8)-this.desplazamiento, paint);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setColor(ContextCompat.getColor(context, R.color.black));
            textPaint.setTextSize((float)(section*0.7));
            canvas.drawText("" + (i + 1), (((pos * section) + ((pos + 1) * section)) / 2), (currentY + section) - (section / 2) - ((textPaint.descent() + textPaint.ascent()) / 2) - this.desplazamiento, textPaint);
            if(pos == 2){
                currentY+=section;
            }
        }
        this.paint.setAlpha(prev);
        if(this.transitionTime>0){
            System.out.println("draw");
        }
    }

    @Override
    public void setPause() {

    }

    @Override
    public void notifyTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.validClick = true;
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
                if(!this.validDesplazamiento && this.validClick && clickPos == null) {
                    int x = (int) (event.getX() / this.section);
                    int y = (int) ((event.getY() + this.desplazamiento) / this.section);
                    int n = ((y * 3) + (x + 1));
                    if(n<=this.maxLevel) {
                        this.transitionTime = System.currentTimeMillis()+1000;
                        gsm.setCurrentLevel(n);
                        clickPos = clickPos.create((int) event.getX(),(int) event.getY());
                        //gsm.setState(GameStateManager.RUNNINGSTATE);
                    }
                    if(random.nextBoolean()) {
                        paintSplash = paintR;
                        splash = gsm.textures.splashesR[new Random().nextInt((3)+1)];
                    }
                    else{
                        paintSplash = paintB;
                        splash = gsm.textures.splashesB[new Random().nextInt((3)+1)];
                    }
                }
                this.validDesplazamiento = false;
                this.validClick = false;
        }
    }

    @Override
    public void notifyBackPressed() {
        gsm.setState(GameStateManager.MENUSTATE);
    }
}
