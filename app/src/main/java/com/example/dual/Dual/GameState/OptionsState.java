package com.example.dual.Dual.GameState;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

public class OptionsState extends GameState{

    private Background bg;
    private Bitmap tempBitmap;
    private int section;

    private int currentChoice = 0;
    private class Option{
        public static final int DELETE = 0;
        public static final int OPTIONS = 1;
        public static final int QUIT = 2;
        public VectorDrawable texture;
        public String name;
        public int option;
        public int left, top, right, bottom;
        public Option(int constant){
            if(constant == DELETE) {
                this.option = DELETE;
                this.name = "Reset";
                this.texture = (VectorDrawable) context.getResources().getDrawable(R.drawable.delete_vector);
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
    int colorMagenta;

    public OptionsState(GameStateManager gsm, Context context) {
        this.gsm = gsm;
        this.context = context;
        this.options = new Option[]{
                new Option(Option.DELETE)
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
            options[i].texture.setBounds((section/2)+(section/4), currentY+(section/8), (section/2)+(section)-(section/4), currentY+section-(section/3));
            currentY += section;
        }
        colorMagenta = ContextCompat.getColor(this.context, R.color.magenta);
        textPaint.setColor(colorMagenta);
        textPaint.setTextAlign(Paint.Align.LEFT);
        optionPaint.setTextSize(100);
        optionPaint.setColor(Color.BLACK);
        optionPaint.setTextAlign(Paint.Align.CENTER);
        optionPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
    }
    public void init() {}
    public void update() {
        bg.update();
    }

    public void draw(Canvas canvas) {
        int currentY = (int) startY;
        bg.draw(canvas);
        canvas.drawText("El jueguito", 80, startY, textPaint);
        currentY = (int) (currentY+((textPaint.descent() + textPaint.ascent()) / 2));
        for(int i=0; i<options.length; i++) {
            canvas.drawBitmap(tempBitmap,(section/2)+(section/8),currentY+(section/8),textPaint);
            options[i].texture.draw(canvas);
            canvas.drawText(options[i].name, (canvas.getWidth() / 2), currentY+section-((textPaint.descent() + textPaint.ascent()) / 2)-(section/4), optionPaint);
            currentY += section;
        }
    }

    public void setPause() {

    }


    public void notifyTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(Option opt:options) {
                    if(opt.texture.getBounds().contains((int) event.getRawX(), (int) event.getRawY())){
                        gsm.setMaxLevel(1);
                        break;
                    }
                }
                break;
        }
    }

    @Override
    public void notifyBackPressed() {
        gsm.setState(GameStateManager.MENUSTATE);
    }

}
