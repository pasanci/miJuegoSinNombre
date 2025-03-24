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
import android.util.Pair;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.TileMap.Background;
import com.example.dual.R;

import java.util.Random;

public class MenuState extends GameState{
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

    private Background bg;
    private Bitmap tempBitmap;
    private int section;

    private int currentChoice = 0;

    private Option[] options;

    float [] optionsrange;
    float startY = 80;

    private Color titleColor;
    private Font titleFont;
    private Font font;
    Paint textPaint = new Paint();
    Paint optionPaint = new Paint();
    boolean doubleBackToExitPressedOnce = false;

    private boolean validClick;
    private Bitmap splash;
    private Random random;
    private Paint paintSplash;
    private Paint paintB;
    private Paint paintR;
    private Pair<Integer,Integer> clickPos;
    private long transitionTime;
    private int transitionState;

    public MenuState(GameStateManager gsm, Context context) {
        this.gsm = gsm;
        this.context = context;
        this.validClick = false;
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
        this.paintB = new Paint();
        this.paintR = new Paint();
        this.random = new Random();
        init();
    }
    public void init() {
        this.transitionTime = -1;
        clickPos = null;
        this.tempBitmap = Bitmap.createScaledBitmap(gsm.textures.marbleBitmap, section-(section/4), section-(section/4), true);
    }
    public void update() {
        bg.update();
        if(this.transitionTime>0 && this.transitionTime<System.currentTimeMillis()){
            if(transitionState == GameStateManager.EXIT) {
                this.finishAffinity();
            }
            gsm.setState(this.transitionState);
        }
    }

    private void select() {
        if(currentChoice == Option.START) {//start
            transitionState = GameStateManager.LEVELSELECTIONSTATE;
        }
        else if(currentChoice == Option.OPTIONS) {//help
            transitionState = GameStateManager.OPTIONSSTATE;
        }
        else if(currentChoice == Option.QUIT) {//quit
            transitionState = GameStateManager.EXIT;
        }
        this.transitionTime = System.currentTimeMillis()+500;
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
            if(clickPos!=null) {
                Bitmap b = tempBitmap.copy(tempBitmap.getConfig(),true);
                Canvas splashCanvas = new Canvas(b);
                splashCanvas.drawBitmap(splash, (clickPos.first-(section/2)-(section/8)) - splash.getWidth()/2, (clickPos.second-currentY-(section/8))-splash.getHeight()/2, paintSplash);
                //splashCanvas.drawBitmap(splash, 0, 0, paintSplash);
                canvas.drawBitmap(b,(section/2)+(section/8),currentY+(section/8),textPaint);
            }
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
                this.validClick = true;
                break;
            case MotionEvent.ACTION_UP:
                if (this.validClick) {
                    for (Option opt : options) {
                        if (opt.bounds.contains((int) event.getX(), (int) event.getY())) {
                            this.currentChoice = opt.option;
                            select();
                            clickPos = clickPos.create((int) event.getX(), (int) event.getY());
                            if(random.nextBoolean()) {
                                paintSplash = paintR;
                                splash = gsm.textures.splashesR[new Random().nextInt((3)+1)];
                            }
                            else{
                                paintSplash = paintB;
                                splash = gsm.textures.splashesB[new Random().nextInt((3)+1)];
                            }
                            break;
                        }
                    }
                }
                this.validClick = false;
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
