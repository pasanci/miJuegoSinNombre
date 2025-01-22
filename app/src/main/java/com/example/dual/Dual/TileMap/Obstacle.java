package com.example.dual.Dual.TileMap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Random;

import com.example.dual.Dual.Main.Main;
import com.example.dual.Dual.Main.Collision;
import com.example.dual.R;

public class Obstacle {
    protected double x=0;
    protected double y=0;
    protected double width=0;
    protected double height=0;
    protected double frameTime;
    Textures textures;
    int randomHeight;
    int randomWidth;
    Random rand = new Random();
    double playerHeight;
    boolean collisioned = false;
    double collisionY;
    protected double speed = (7*(1/1))/(16.66/16);

    boolean reseting = false;
    double resetSpeed;
    double resetTime = 1000;

    //splash
    protected ArrayList<Collision> collisionList = new ArrayList<Collision>();

    //restart
    protected double initialx=0;
    protected double initialy=0;
    protected double initialwidth=0;
    protected double initialheight=0;
    protected double initialframeTime;

    protected Context context;
    double heightFactor = 1366.0/Resources.getSystem().getDisplayMetrics().heightPixels;

    public Obstacle(Textures textures, double x, double y, double width, double length) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = length;
        this.textures = textures;
        //randomHeight = rand.nextInt((int) (this.textures.marble.getHeight()-height) + 1);
        //randomWidth = rand.nextInt((int) (this.textures.marble.getWidth()-width) + 1);
        //restart
        this.initialx = x;
        this.initialy = y;
        this.initialwidth = this.width;
        this.initialheight = this.height;
    }

    public Obstacle(Textures textures, double y, int percentajeFree, double length) {
        this.y = y;
        this.width = Resources.getSystem().getDisplayMetrics().widthPixels -((Resources.getSystem().getDisplayMetrics().widthPixels/100.0) * percentajeFree*2);
        this.x = Resources.getSystem().getDisplayMetrics().widthPixels/2.0 - this.width/2;
        this.textures = textures;
        this.height = length;
        //randomHeight = rand.nextInt((int) (this.textures.marble.getHeight()-height) + 1);
        //randomWidth = rand.nextInt((int) (this.textures.marble.getWidth()-width) + 1);
        //restart
        this.initialx = x;
        this.initialy = y;
        this.initialwidth = this.width;
        this.initialheight = this.height;
    }

    public Obstacle(Textures textures, double y, int percentaje, double length, boolean side) {//false = left
        this.y = y;
        if(!side) {//false = left
            this.x = 0;
            this.width = (Resources.getSystem().getDisplayMetrics().widthPixels/100.0) * percentaje;
        }
        else {//true = right
            this.x = (Resources.getSystem().getDisplayMetrics().widthPixels/100.0) * (100-percentaje);
            this.width = Resources.getSystem().getDisplayMetrics().widthPixels - this.x;
        }
        this.height = length;
        this.textures = textures;
        //randomHeight = rand.nextInt((int) (this.textures.marble.getHeight()-height) + 1);
        //randomWidth = rand.nextInt((int) (this.textures.marble.getWidth()-width) + 1);
        //restart
        this.initialx = x;
        this.initialy = y;
        this.initialwidth = this.width;
        this.initialheight = this.height;
    }

    public void update() {
        this.speed = (6*(1/heightFactor))/(16.66/16.0)* (frameTime/16.0);
        //this. resetSpeed = 3*this.speed;
        if(!collisioned) {
            y+=this.speed;
            //System.out.println(6/(16.66/frameTime));
        }
    }

    public boolean resetting() {
        y-=this.resetSpeed;
        if(y<this.initialy) {
            reseting = false;

        }
        return reseting;
    }


    public void reset() {
        this.reseting = true;
        this.resetSpeed = (this.y - this.initialy) / (resetTime/frameTime);
    }

    public void restart() {
        this.x = this.initialx;
        this.y = this.initialy;
        this.width = this.initialwidth;
        this.height = this.initialheight;
        collisioned = false;
        this.reseting = false;
    }

    public double getY() {
        return this.y;
    }

    public double[] getBox() {
        double x1 = x;
        double y1 = y;
        double x2 = x + width;
        double y2 = y + height;
        double[] box = {x1, y1, x2, y2};
        return box;
    }

    public void draw(Canvas canvas) {

        //Paint paint = new Paint();
        //paint.setColor(ContextCompat.getColor(this.context, R.color.white));
        //canvas.drawRect ((int)x, (int)y, (int) (x + width), (int) (y + height), paint);

        Rect imageBounds = new Rect((int)x, (int)y, (int) (x + width), (int) (y + height));
        textures.marble.setBounds(imageBounds);
        textures.marble.draw(canvas);


        /*
        Rectangle r = new Rectangle(0,0,Main.WIDTH,Main.HEIGHT);
        Rectangle obstacle = new Rectangle((int)x, (int)y, (int)width, (int)height);
        g.setClip(obstacle);

        g.drawImage(this.textures.marble, (int)x - randomWidth, (int)y - randomHeight, this.textures.marble.getWidth(), this.textures.marble.getHeight(), null);

        for (Collision collision : collisionList) {
            Graphics2D g2d = (Graphics2D)g;
            AffineTransform old = g2d.getTransform();
            g2d.translate((int)(this.x - collision.getX() - this.textures.splash.getWidth()/20), (int) (this.y - collision.getY() - collision.getSplashHeight() - this.textures.splash.getHeight()/10));
            g2d.rotate(Math.toRadians(collision.getSplashAngle()),this.textures.splash.getWidth()/20,this.textures.splash.getHeight()/20);
            if(collision.getColor()) {
                g2d.drawImage(this.textures.splashazul, 0, 0, this.textures.splash.getWidth()/10, this.textures.splash.getHeight()/10, null);
                //g.drawImage(this.textures.splashazul, (int)(this.x - collision.getX() - this.textures.splash.getWidth()/20), (int) (this.y - collision.getY() - collision.getSplashHeight() - this.textures.splash.getHeight()/10), this.textures.splash.getWidth()/10, this.textures.splash.getHeight()/10, null);
            }
            else {
                g2d.drawImage(this.textures.splashrojo, 0, 0, this.textures.splash.getWidth()/10, this.textures.splash.getHeight()/10, null);
                //g.drawImage(this.textures.splashrojo, (int)(this.x - collision.getX() - this.textures.splash.getWidth()/20), (int) (this.y - collision.getY() - collision.getSplashHeight() - this.textures.splash.getHeight()/10), this.textures.splash.getWidth()/10, this.textures.splash.getHeight()/10, null);
            }
            g2d.setTransform(old);
            //g2d.dispose();
        }
        g.setClip(r);


         */
    }

    public void setFrameTime(long frameTime) {
        this.frameTime = (double)frameTime;
    }

    public boolean getCollision(double cx, double cy, double radius) {
        radius = radius;
        double x1 = x;
        double y1 = y;
        double x2 = x + this.width;
        double y2 = y + this.height;
        double closestX = (cx < x1 ? x1 : (cx > x2 ? x2 : cx));
        double closestY = (cy < y1 ? y1 : (cy > y2 ? y2 : cy));
        double dx = closestX - cx;
        double dy = closestY - cy;
        return ( dx * dx + dy * dy ) <= radius * radius;
    }

    public void setPlayerHeight(double height) {
        playerHeight = height;
    }

    public void appendCollision(double x, double y, boolean selector) {
        Collision col;
        if(this.y-y>0) {
            col = new Collision(this.x-x,this.y-y-1.25*height,!selector);
        }
        else {
            col = new Collision(this.x-x,this.y-y,!selector);
        }
        collisionList.add(col);
    }

    public void collisioned() {
        this.collisioned = true;
        this.collisionY = this.playerHeight;
    }

    public void setContext(Context context) {
        this.context = context;
    }


}