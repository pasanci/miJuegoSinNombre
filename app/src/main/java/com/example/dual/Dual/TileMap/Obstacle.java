package com.example.dual.Dual.TileMap;

import static java.lang.Math.sqrt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Random;

import com.example.dual.Dual.GameState.GameStateManager;
import com.example.dual.Dual.Main.Collision;
import com.example.dual.R;

public class Obstacle {
    public double getX() {
        return x;
    }

    public double getWidth() {
        return width;
    }

    public double getFrameTime() {
        return frameTime;
    }

    public double getHeight() {
        return height;
    }

    protected double x=0;
    protected double y=0;
    protected double width=0;
    protected double height=0;
    protected int fWidth;
    protected int fHeight;
    protected double frameTime;
    Textures textures;
    Bitmap tempBitmap;
    Canvas tempImage;
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

    protected Context context;
    protected GameStateManager gsm;
    Paint paintB = new Paint();
    Paint paintR = new Paint();
    Paint paintP = new Paint();
    Paint paint = new Paint();

    public Obstacle(GameStateManager gsm, Textures textures, double x, double y, double width, double length) {
        this.gsm = gsm;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = length;
        this.textures = textures;
        this.fWidth = (int) (this.width/this.gsm.getWidth()*this.gsm.getActualWidth());
        this.fHeight = (int) (this.height/this.gsm.getHeight()*this.gsm.getActualHeight());
        this.tempBitmap = Bitmap.createScaledBitmap(textures.marbleBitmap, this.fWidth, this.fHeight, true);
        this.initialx = x;
        this.initialy = y;
        this.initialwidth = this.width;
        this.initialheight = this.height;
        this.tempImage = new Canvas(tempBitmap);
    }

    public Obstacle(GameStateManager gsm, Textures textures, double y, int percentajeFree, double length) {
        this.gsm = gsm;
        this.y = y;
        this.width = this.gsm.getWidth() -((this.gsm.getWidth()/100.0) * percentajeFree*2);
        this.height = length;
        this.x = this.gsm.getWidth()/2.0 - this.width/2;
        this.textures = textures;
        this.fWidth = (int) (this.width/this.gsm.getWidth()*this.gsm.getActualWidth());
        this.fHeight = (int) (this.height/this.gsm.getHeight()*this.gsm.getActualHeight());
        this.tempBitmap = Bitmap.createScaledBitmap(textures.marbleBitmap, this.fWidth, this.fHeight, true);
        this.initialx = this.x;
        this.initialy = this.y;
        this.initialwidth = this.width;
        this.initialheight = this.height;
        this.tempImage = new Canvas(tempBitmap);
    }

    public Obstacle(GameStateManager gsm, Textures textures, double y, int percentaje, double length, boolean side) {//false = left
        this.gsm = gsm;
        this.y = y;
        if(!side) {//false = left
            this.x = 0;
            this.width = (this.gsm.getWidth()/100.0) * percentaje;
        }
        else {//true = right
            this.x = (this.gsm.getWidth()/100.0) * (100-percentaje);
            this.width = this.gsm.getWidth() - this.x;
        }
        this.height = length;
        this.textures = textures;
        this.fWidth = (int) (this.width/this.gsm.getWidth()*this.gsm.getActualWidth());
        this.fHeight = (int) (this.height/this.gsm.getHeight()*this.gsm.getActualHeight());
        this.tempBitmap = Bitmap.createScaledBitmap(textures.marbleBitmap, this.fWidth, this.fHeight, true);
        this.initialx = this.x;
        this.initialy = this.y;
        this.initialwidth = this.width;
        this.initialheight = this.height;
        this.tempImage = new Canvas(tempBitmap);
    }

    public void update() {
        this.speed = (6*(1/(1366.0/this.gsm.getHeight())))/(16.66/16.0)* (frameTime/16.0);
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
        int fx = (int) (this.x/this.gsm.getWidth()*this.gsm.getActualWidth());
        int fy = (int) (this.y/this.gsm.getHeight()*this.gsm.getActualHeight());
        canvas.drawBitmap(tempBitmap,fx,fy,paint);
    }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        bmp1.recycle();
        bmp2.recycle();
        return bmOverlay;
    }

    public void setFrameTime(long frameTime) {
        this.frameTime = (double)frameTime;
    }

    public boolean getCollision(double cx, double cy, double radius) {
        double x1 = (this.x/this.gsm.getWidth()*this.gsm.getActualWidth());
        double y1 = (this.y/this.gsm.getHeight()*this.gsm.getActualHeight());
        double x2 = ((this.x+this.width)/this.gsm.getWidth()*this.gsm.getActualWidth());
        double y2 = ((this.y+this.height)/this.gsm.getHeight()*this.gsm.getActualHeight());
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
        int fx = (int) (this.x/this.gsm.getWidth()*this.gsm.getActualWidth());
        int fy = (int) (this.y/this.gsm.getHeight()*this.gsm.getActualHeight());
        Collision col = new Collision((((int)x)-fx),((int)y)-fy,!selector);
        collisionList.add(col);
        if(selector) {
            Bitmap b = textures.splashesR[new Random().nextInt((3)+1)];
            this.tempImage.drawBitmap(b,((int)x-fx)-b.getWidth()/2,((int)y-fy)-b.getHeight()/2,paintR);
            //this.tempImage.drawCircle(((int)x-fx), ((int)y-fy), 60, paintR);
        }
        else {
            Bitmap b = textures.splashesB[new Random().nextInt((3)+1)];
            this.tempImage.drawBitmap(b,((int)x-fx)-b.getWidth()/2,((int)y-fy)-b.getHeight()/2,paintB);
            //this.tempImage.drawCircle(((int)x-fx), ((int)y-fy), 60, paintB);
        }
    }

    public void collisioned() {
        this.collisioned = true;
        this.collisionY = this.playerHeight;
    }

    public void setContext(Context context) {
        this.context = context;
        paintB.setColor(ContextCompat.getColor(context, R.color.cyan));
        paintR.setColor(ContextCompat.getColor(context, R.color.red));
        paintP.setColor(ContextCompat.getColor(context, R.color.purple_500));
    }
}