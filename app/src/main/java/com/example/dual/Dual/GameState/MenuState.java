package com.example.dual.Dual.GameState;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.VectorDrawable;
import android.graphics.fonts.Font;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.TileMap.Background;
import com.example.dual.R;

public class MenuState extends GameState{

    private Background bg;
    private Bitmap tempBitmap;
    private int section;

    private int currentChoice = 0;
    private class Option{
        public static final int START = 0;
        public static final int OPTIONS = 1;
        public static final int QUIT = 2;
        public VectorDrawable texture;
        public String name;
        public int option;
        public int left, top, right, bottom;
        public Rect bounds;
        public Option(int constant){
            if(constant == START) {
                this.option = START;
                //this.name = "Start";
                this.name = "Jugar";
                this.texture = (VectorDrawable) context.getResources().getDrawable(R.drawable.play_vector);
            }
            else if(constant == OPTIONS) {
                this.option = OPTIONS;
                //this.name = "Options";
                this.name = "Opciones";
                this.texture = (VectorDrawable) context.getResources().getDrawable(R.drawable.settings_vector);
            }
            else if(constant == QUIT) {
                this.option = QUIT;
                //this.name = "Quit";
                this.name = "Salir";
                this.texture = (VectorDrawable) context.getResources().getDrawable(R.drawable.close_vector);
            }
        }
        public Option(int constant,int left,int top,int right,int bottom){
            this(constant);
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }
    }

    private Option[] options;

    float [] optionsrange;
    float startY = 80;

    private Color titleColor;
    private Font titleFont;
    private Font font;
    Paint textPaint = new Paint();
    Paint optionPaint = new Paint();
    boolean doubleBackToExitPressedOnce = false;

    public MenuState(GameStateManager gsm, Context context) {
        this.gsm = gsm;
        this.context = context;
        this.options = new Option[]{
                new Option(Option.START),
                new Option(Option.OPTIONS),
                new Option(Option.QUIT)
        };
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
        this.section = (this.gsm.getActualWidth()/2);
        this.tempBitmap = Bitmap.createScaledBitmap(gsm.textures.marbleBitmap, section-(section/4), section-(section/4), true);

        textPaint.setTextSize(50);
        int currentY = (int) (startY+((textPaint.descent() + textPaint.ascent()) / 2));
        for(int i=0; i<options.length; i++) {
            //options[i].texture.setBounds((section/2)+(section/8), currentY+(section/8), (section/2)+(section)-(section/8), currentY+section-(section/8));
            options[i].bounds = new Rect((section/2)+(section/8), currentY+(section/8), (section/2)+section-(section/8), currentY+section-(section/8));
            options[i].texture.setBounds((section/2)+(section/4), currentY+(section/8), (section/2)+section-(section/4), currentY+section-(section/3));
            currentY += section;
        }
        optionPaint.setTextSize(90);
        optionPaint.setColor(Color.BLACK);
        optionPaint.setTextAlign(Paint.Align.CENTER);
        optionPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
    }
    public void init() {}
    public void update() {
        bg.update();
    }

    private void select() {
        if(currentChoice == Option.START) {//start
            if(gsm.getWidth()>0 && gsm.getActualWidth()>0) {//wait for apk to properly launch
                //gsm.setState(GameStateManager.RUNNINGSTATE);
                gsm.setState(GameStateManager.LEVELSELECTIONSTATE);
            }
        }
        if(currentChoice == Option.OPTIONS) {//help
            gsm.setState(GameStateManager.OPTIONSSTATE);
        }
        if(currentChoice == Option.QUIT) {//quit
            System.exit(0);
        }
    }

    public void draw(Canvas canvas) {
        int currentY = (int) startY;
        bg.draw(canvas);
        int color = ContextCompat.getColor(this.context, R.color.magenta);
        textPaint.setColor(color);
        canvas.drawText("El jueguito", 80, startY, textPaint);
        currentY = (int) (currentY+((textPaint.descent() + textPaint.ascent()) / 2));
        for(int i=0; i<options.length; i++) {
            if(i == currentChoice) {
                color = ContextCompat.getColor(this.context, R.color.white);
            }
            else {
                color = ContextCompat.getColor(this.context, R.color.red);
            }
            /*
            canvas.drawBitmap(tempBitmap,(section/2)+(section/8),currentY+(section/8),textPaint);
            options[i].texture.draw(canvas);
            */
            canvas.drawBitmap(tempBitmap,(section/2)+(section/8),currentY+(section/8),textPaint);
            options[i].texture.draw(canvas);
            canvas.drawText(options[i].name, (canvas.getWidth() / 2), currentY+section-((textPaint.descent() + textPaint.ascent()) / 2)-(section/4), optionPaint);
            currentY += section;
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
                for(Option opt:options) {
                    if(opt.bounds.contains((int) event.getX(), (int) event.getY())){
                        this.currentChoice = opt.option;
                        select();
                        break;
                    }
                }
                /*
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
                */
                break;
        }
    }

    @Override
    public void notifyBackPressed() {
        System.out.println("notifyBackPressed= "+this.doubleBackToExitPressedOnce);
        if (this.doubleBackToExitPressedOnce) {
            System.exit(0);
        }
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        this.doubleBackToExitPressedOnce = true;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
