package com.example.mijuegosinnombre.GameState;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.VectorDrawable;
import android.graphics.fonts.Font;
import android.util.Pair;
import android.view.MotionEvent;
import androidx.core.content.ContextCompat;
import java.util.Random;

import com.example.mijuegosinnombre.TileMap.Background;
import com.example.mijuegosinnombre.R;

public class OptionsState extends GameState{

    private class Option{
        public static final int DELETE = 0;
        public static final int UNLOCK = 1;
        public static final int SKIP = 2;
        public static final int SHOWFPS = 3;
        public VectorDrawable texture;
        public String name;
        public int option;
        public int left, top, right, bottom;
        public Rect bounds;
        public Option(int constant){
            if(constant == DELETE) {
                this.option = DELETE;
                this.name = "Reset";
                this.texture = (VectorDrawable) context.getResources().getDrawable(R.drawable.delete_vector);
            }
            else if(constant == UNLOCK) {
                this.option = UNLOCK;
                this.name = "Desbloquear";
                this.texture = (VectorDrawable) context.getResources().getDrawable(R.drawable.unlock_vector);
            }
            else if(constant == SKIP) {
                this.option = SKIP;
                this.name = "Saltar";
                this.texture = (VectorDrawable) context.getResources().getDrawable(R.drawable.skip_vector);
            }
            else if(constant == SHOWFPS) {
                this.option = SHOWFPS;
                this.name = "FPS";
                this.texture = (VectorDrawable) context.getResources().getDrawable(R.drawable.skip_vector);
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
    int colorMagenta;
    private boolean validClick;
    private Bitmap splash;
    private Random random;
    private Paint paintSplash;
    private Paint paintB;
    private Paint paintR;
    private Pair<Integer,Integer> clickPos;

    public OptionsState(GameStateManager gsm, Context context) {
        this.gsm = gsm;
        this.context = context;
        this.validClick = false;
        this.options = new Option[]{
                new Option(Option.DELETE),
                new Option(Option.UNLOCK),
                new Option(Option.SKIP),
                new Option(Option.SHOWFPS)
        };
        gsm.setShowFPS(gsm.getSharedPreferences().getBoolean(GameStateManager.SHOWFPSSTR, false));
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
            options[i].bounds = new Rect((section/2)+(section/8), currentY+(section/8), (section/2)+section-(section/8), currentY+section-(section/8));
            options[i].texture.setBounds((section/2)+(section/4), currentY+(section/8), (section/2)+(section)-(section/4), currentY+section-(section/3));
            currentY += section;
        }
        colorMagenta = ContextCompat.getColor(this.context, R.color.magenta);
        textPaint.setColor(colorMagenta);
        textPaint.setTextAlign(Paint.Align.LEFT);
        optionPaint.setTextSize(70);
        optionPaint.setColor(Color.BLACK);
        optionPaint.setTextAlign(Paint.Align.CENTER);
        optionPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        this.paintB = new Paint();
        this.paintR = new Paint();
        this.random = new Random();
        init();
    }
    public void init() {
        clickPos = null;
        this.tempBitmap = Bitmap.createScaledBitmap(gsm.textures.marbleBitmap, section-(section/4), section-(section/4), true);
    }

    public void update() {
        bg.update();
    }

    public void draw(Canvas canvas) {
        int currentY = (int) startY;
        bg.draw(canvas);
        canvas.drawText("El jueguito", 80, startY, textPaint);
        currentY = (int) (currentY+((textPaint.descent() + textPaint.ascent()) / 2));
        int prev = this.textPaint.getAlpha();
        if(!gsm.DEBUG) {
            this.textPaint.setAlpha(80);
        }
        for(int i=0; i<options.length; i++) {
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
        this.textPaint.setAlpha(prev);
    }

    public void setPause() {

    }


    public void notifyTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.validClick = true;
                break;
            case MotionEvent.ACTION_UP:
                if (this.validClick) {
                    if(gsm.DEBUG) {
                        for (Option opt : options) {
                            if(opt.bounds.contains((int) event.getX(), (int) event.getY())){
                                if (opt.option == Option.DELETE) {
                                    gsm.setMaxLevel(1);
                                } else if (opt.option == Option.UNLOCK) {
                                    gsm.setMaxLevel(gsm.getLevels().getLevelsNumber());
                                } else if (opt.option == Option.SKIP) {
                                    gsm.setMaxLevel(gsm.getMaxLevel() + 1);
                                } else if (opt.option == Option.SHOWFPS) {
                                    gsm.getEditor().putBoolean(GameStateManager.SHOWFPSSTR, !gsm.getShowFPS());
                                    gsm.setShowFPS(!gsm.getShowFPS());
                                }
                                clickPos = clickPos.create((int) event.getX(),(int) event.getY());
                                break;
                            }
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
                }
                this.validClick = false;
                break;
        }
    }

    @Override
    public void notifyBackPressed() {
        gsm.setState(GameStateManager.MENUSTATE);
    }

}
