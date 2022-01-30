package com.example.dual.Dual.Main;

import java.util.Random;

public class Collision {
    public double x;
    public double y;
    public boolean color; //red false, blue true

    //generated
    private Random rand = new Random();
    private int lowHeight = -10;
    private int highHeight = 10;
    private double splashHeight;

    private int lowAngle = 0;
    private int highAngle = 359;
    private double splashAngle;

    public Collision(double x, double y, boolean color) { this.x = x;
        this.y = y;
        this.color = color;
        this.splashHeight = rand.nextInt(highHeight-lowHeight) + lowHeight;
        this.splashAngle = rand.nextInt(highAngle-lowAngle) + lowAngle;
    }

    public static boolean same(Object x, Object y) {
        return x == null ? y == null : x.equals(y);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getSplashHeight() { return this.splashHeight; }
    public double getSplashAngle() { return this.splashAngle; }
    public boolean getColor() { return this.color; }

    void setX(double o) { x = o; }
    void setY(double o) { y = o; }
    void setsplashHeight(double splashHeight) { this.splashHeight = splashHeight; }
    void setColor(boolean color) { this.color = color; }

    public String toString() {
        return "Pair{"+x+", "+y+"}";
    }
}