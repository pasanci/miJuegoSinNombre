package com.example.dual.Dual.TileMap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.fonts.Font;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.Main.Main;
import com.example.dual.R;

public class Player{

    private int color1;
    private int color2;
    private double x;
    private double y;
    private double angle;
    private double targetAngle;
    private double diameter;
    private double ballRadius;
    private boolean angleLeft = false;
    private boolean angleRight = false;
    private boolean centering = false;
    private boolean waitingRestart = false;
    private double frameTime;
    private String level;
    private long levelTime;
    private Font font;
    private float transparency = 1.0f;
    private float fadeTimeInms = 2000.0f;
    private boolean displayingLevel;
    private double [] historico;
    private int historicoIterator;
    private long lastTime;
    //private BufferedImage fire;
    //private BufferedImage ice;
    Textures textures;
    //private double lastAngle;

    public Player(Textures textures, int color1, int color2, double angle, double diameter, double ballDiameter) {
        try {
            this.color1 = color1;
            this.color2 = color2;
            this.angle = angle;
            this.diameter = diameter;
            this.ballRadius = ballDiameter/2;
            this.historico = new double [20];
            historicoIterator = 0;
            lastTime = System.currentTimeMillis();
            //this.lastAngle = angle;
            //fire = ImageIO.read(getClass().getResourceAsStream("/fuego.gif"));
            //ice = ImageIO.read(getClass().getResourceAsStream("/hielo.gif"));
            this.textures = textures;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y, double angle, int level) {
        this.level = level+"";
        displayingLevel = true;
        levelTime = System.currentTimeMillis();
        this.x = x * (Resources.getSystem().getDisplayMetrics().widthPixels/100.0);
        this.y = y * (Resources.getSystem().getDisplayMetrics().heightPixels/100.0);
        centering = true;
        this.targetAngle = normalizeAngle(angle);
    }

    public void rotateToTarget() {
        double diference = this.angle - this.targetAngle;
        if(Math.abs(diference)<0.07/(16.66/frameTime)) {
            this.angle = this.targetAngle;
            centering = false;
        }
        else if(Math.abs(diference)>Math.PI/2 && Math.abs(diference)>(Math.PI)) {
            this.angle-=0.07/(16.66/frameTime);
        }
        else{
            this.angle+=0.07/(16.66/frameTime);
        }
        this.angle = normalizeAngle(this.angle);
    }

    public boolean isRotatingToTarget() {
        return this.centering;
    }

    public void setPosition(double x, double y) {
        this.x = x * (Resources.getSystem().getDisplayMetrics().widthPixels/100);
        this.y = y * (Resources.getSystem().getDisplayMetrics().heightPixels/100);
    }

    public void setAngleLeft(boolean value) {
        this.angleLeft = value;
    }

    public double[] getCircles() {
        double x1 = (diameter * Math.cos(angle) + this.x);
        double y1 = (diameter * Math.sin(angle) + this.y);
        double x2 = (diameter * Math.cos(Math.PI+angle) + this.x);
        double y2 = (diameter * Math.sin(Math.PI+angle) + this.y);
        double[] circles = {x1, y1, x2, y2, ballRadius};
        return circles;
    }

    public void setAngleRight(boolean value) {
        this.angleRight = value;
    }

    public void setVector(double dx, double dy) {
    }

    public void update() {
        if(displayingLevel) {
            this.transparency = (float) (1.0-((System.currentTimeMillis()-levelTime)/fadeTimeInms));
            if(System.currentTimeMillis()-levelTime>fadeTimeInms) {
                displayingLevel = false;
                this.transparency = 0;
            }
        }

        if(System.currentTimeMillis()-lastTime>10) {
            historico[historicoIterator] = this.angle;
            lastTime = System.currentTimeMillis();
            historicoIterator++;
            if(historicoIterator==historico.length) {
                historicoIterator = 0;
            }
        }
        if(!centering && !waitingRestart) {
            if(angleLeft) {
                this.angle-=0.07/(16.66/frameTime);
            }
            else if(angleRight) {
                this.angle+=0.07/(16.66/frameTime);
            }
        }
    }

    public static double normalizeAngle(double angle) {
        return Math.atan2(Math.sin(angle), Math.cos(angle));
    }

    public void draw(Canvas canvas, Context context) {
        if(centering) {
            rotateToTarget();
        }
        this.angle = normalizeAngle(this.angle);
        Paint paint = new Paint();
        //canvas.drawColor(ContextCompat.getColor(context, R.color.white));
        paint.setColor(ContextCompat.getColor(context, R.color.white));
        paint.setTextSize((float)diameter);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle((int)(this.x), (int)(this.y), (int)diameter, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(context, R.color.white));
        paint.setAlpha((int)(transparency*250));
        canvas.drawText(this.level, (int) (this.x-diameter/2/2), (int) (this.y+diameter/2/2), paint);
        paint.setAlpha(100);

        paint.setColor(this.color1);
        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int historicoIteratorAux = historicoIterator;
        for(int i = 0; i<historico.length; i++) {
            paint.setAlpha((int)(0.3*(i/(float)historico.length*250)));
            canvas.drawCircle((int)((diameter * Math.cos(historico[historicoIteratorAux]) + this.x)), (int)((diameter * Math.sin(historico[historicoIteratorAux]) + this.y)) , (int)(2 * ballRadius*(i/(float)historico.length)),paint);
            paint.setAlpha(100);
            historicoIteratorAux++;
            if(historicoIteratorAux==historico.length) {
                historicoIteratorAux = 0;
            }
        }

        paint.setColor(this.color2);

        historicoIteratorAux = historicoIterator;
        for(int i = 0; i<historico.length; i++) {
            paint.setAlpha((int)(0.3*(i/(float)historico.length*250)));
            canvas.drawCircle((int)(diameter * Math.cos(Math.PI+historico[historicoIteratorAux]) + this.x), (int)(diameter * Math.sin(Math.PI+historico[historicoIteratorAux]) + this.y), (int)(2 * ballRadius*(i/(float)historico.length)),paint);
            paint.setAlpha(100);
            historicoIteratorAux++;
            if(historicoIteratorAux==historico.length) {
                historicoIteratorAux = 0;
            }
        }

/*
		g.setColor(this.color1);
		g.fillOval((int)((diameter/2 * Math.cos(angle) + this.x) - ballRadius), (int)((diameter/2 * Math.sin(angle) + this.y) - ballRadius), (int)(2 * ballRadius), (int)(2 * ballRadius));

		g.setColor(this.color2);
		g.fillOval((int)((diameter/2 * Math.cos(Math.PI+angle) + this.x) - ballRadius), (int)((diameter/2 * Math.sin(Math.PI+angle) + this.y) - ballRadius), (int)(2 * ballRadius), (int)(2 * ballRadius));
*/

/*
        Ellipse2D azul = new Ellipse2D.Float((int)((diameter/2 * Math.cos(angle) + this.x) - ballRadius), (int)((diameter/2 * Math.sin(angle) + this.y) - ballRadius), (int)(2 * ballRadius), (int)(2 * ballRadius));

        //g.setColor(this.color2);
        Ellipse2D rojo = new Ellipse2D.Float((int)((diameter/2 * Math.cos(Math.PI+angle) + this.x) - ballRadius), (int)((diameter/2 * Math.sin(Math.PI+angle) + this.y) - ballRadius), (int)(2 * ballRadius), (int)(2 * ballRadius));

        Rectangle r = new Rectangle(0,0,Main.WIDTH,Main.HEIGHT);
        //g.setClip(r);
        canvas.setClip(azul);
        double radio = (3 * ballRadius);

        double texturewidthIce = 1.8*radio;
        double textureheightIce = 1.3*radio;
        //g.drawImage(textures.gifBolaHielo, (int)((diameter/2 * Math.cos(angle) + this.x) - texturewidthIce/2), (int)((diameter/2 * Math.sin(angle) + this.y) - textureheightIce/2), (int)(texturewidthIce), (int)(textureheightIce), null);

        g.drawImage(textures.gifIce, (int)((diameter/2 * Math.cos(angle) + this.x) - (1.2*radio)/2), (int)((diameter/2 * Math.sin(angle) + this.y) - radio/2), (int)(1.2*radio), (int)radio, null);
        g.setClip(rojo);

        double texturewidth = 1.2*radio;
        double textureheight = 0.9*radio;
        g.drawImage(textures.gifBolaFuego, (int)(((diameter/2 * Math.cos(Math.PI+angle) + this.x) - texturewidth/2)), (int)((diameter/2 * Math.sin(Math.PI+angle) + this.y) - textureheight/2), (int)(texturewidth), (int)(textureheight), null);
        //g.drawImage(textures.fire, (int)((diameter/2 * Math.cos(Math.PI+angle) + this.x) - ballRadius), (int)((diameter/2 * Math.sin(Math.PI+angle) + this.y) - ballRadius), null);

        g.setClip(r);
        g.setColor(Color.BLACK);
        //g.setColor(Color.RED);
        g.draw(rojo);
        //g.setColor(Color.CYAN);
        g.draw(azul);
*/
        paint.setAlpha(100);
        paint.setColor(this.color1);
        canvas.drawCircle((int)(diameter * Math.cos(angle) + this.x), (int)(diameter * Math.sin(angle) + this.y), (int)(2 * ballRadius), paint);
        paint.setColor(this.color2);
        canvas.drawCircle((int)(diameter * Math.cos(Math.PI+angle) + this.x), (int)(diameter * Math.sin(Math.PI+angle) + this.y), (int)(2 * ballRadius), paint);


    }

    public void setFrameTime(long frameTime) {
        this.frameTime = (double)frameTime;
    }

    public double getHeight() {
        return this.y;
    }

    public void setWaitingRestart(boolean status) {
        this.waitingRestart = status;
    }
}
