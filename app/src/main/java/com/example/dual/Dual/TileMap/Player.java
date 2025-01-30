package com.example.dual.Dual.TileMap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.fonts.Font;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.GameState.GameStateManager;
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
    private double rotationSpeed = 0.07/(16.66/60);
    private GameStateManager gsm;
    Paint paint = new Paint();
    Paint textPaint = new Paint();

    public Player(GameStateManager gsm, Textures textures, int color1, int color2, double angle, double diameter, double ballDiameter) {
        this.gsm = gsm;
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
        this.x = x * (this.gsm.getWidth()/100.0);
        this.y = y * (this.gsm.getHeight()/100.0);
        centering = true;
        this.targetAngle = normalizeAngle(angle);
    }

    public void rotateToTarget() {
        double diference = this.angle - this.targetAngle;
        if(Math.abs(diference)<this.rotationSpeed) {
            this.angle = this.targetAngle;
            centering = false;
        }
        else if(Math.abs(diference)>Math.PI/2 && Math.abs(diference)>(Math.PI)) {
            this.angle-=this.rotationSpeed;
        }
        else{
            this.angle+=this.rotationSpeed;
        }
        this.angle = normalizeAngle(this.angle);
    }

    public boolean isRotatingToTarget() {
        return this.centering;
    }

    public void setPosition(double x, double y) {
        this.x = x * (this.gsm.getWidth()/100);
        this.y = y * (this.gsm.getHeight()/100);
    }

    public void setAngleLeft(boolean value) {
        this.angleLeft = value;
    }

    public double[] getCircles() {
        double x1 = (this.diameter * Math.cos(angle) + this.x);
        double y1 = (this.diameter * Math.sin(angle) + this.y);
        double x2 = (this.diameter * Math.cos(Math.PI+angle) + this.x);
        double y2 = (this.diameter * Math.sin(Math.PI+angle) + this.y);
        double[] circles = {x1, y1, x2, y2, ballRadius};
        return circles;
    }

    public void setAngleRight(boolean value) {
        this.angleRight = value;
    }

    public void setVector(double dx, double dy) {
    }

    public void update() {
        this.rotationSpeed = 0.07/(16.66/16.0) * (frameTime/16.0);
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
                this.angle-=this.rotationSpeed;
            }
            else if(angleRight) {
                this.angle+=this.rotationSpeed;
            }
        }
    }

    public static double normalizeAngle(double angle) {
        return Math.atan2(Math.sin(angle), Math.cos(angle));
    }

    public void draw(Canvas canvas, Context context) {
        int fx = (int) (this.x/this.gsm.getWidth()*this.gsm.getActualWidth());
        int fy = (int) (this.y/this.gsm.getHeight()*this.gsm.getActualHeight());
        int fd = (int) (this.diameter/this.gsm.getWidth()*this.gsm.getActualWidth());
        double fBallDiam = ((2*this.ballRadius)/this.gsm.getWidth()*this.gsm.getActualWidth());

        if(centering) {
            rotateToTarget();
        }
        this.angle = normalizeAngle(this.angle);
        //canvas.drawColor(ContextCompat.getColor(context, R.color.white));
        paint.setColor(ContextCompat.getColor(context, R.color.white));
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(fx, fy, fd, paint);
        paint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(ContextCompat.getColor(context, R.color.white));
        textPaint.setAlpha((int)(transparency*250));
        textPaint.setTextSize((float)fd);
        canvas.drawText(this.level, (this.gsm.getActualWidth()/2), fy-((textPaint.descent() + textPaint.ascent()) / 2), textPaint);
        textPaint.setAlpha(100);

        paint.setColor(this.color1);
        //g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int historicoIteratorAux = historicoIterator;
        for(int i = 0; i<historico.length; i++) {
            paint.setAlpha((int)(0.3*(i/(float)historico.length*250)));
            canvas.drawCircle((int)((fd * Math.cos(historico[historicoIteratorAux]) + fx)), (int)((fd * Math.sin(historico[historicoIteratorAux]) + fy)) , (int)(fBallDiam*(i/(float)historico.length)),paint);
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
            canvas.drawCircle((int)(fd * Math.cos(Math.PI+historico[historicoIteratorAux]) + fx), (int)(fd * Math.sin(Math.PI+historico[historicoIteratorAux]) + fy), (int)(fBallDiam*(i/(float)historico.length)),paint);
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
        canvas.drawCircle((int)(fd * Math.cos(angle) + fx), (int)(fd * Math.sin(angle) + fy), (float) fBallDiam, paint);
        paint.setColor(this.color2);
        canvas.drawCircle((int)(fd * Math.cos(Math.PI+angle) + fx), (int)(fd * Math.sin(Math.PI+angle) + fy), (float) fBallDiam, paint);
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

    public double getDiameter(){
        return this.diameter;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public double getBallRadius(){
        return this.ballRadius;
    }

    public double getAngle(){
        return this.angle;
    }
}
