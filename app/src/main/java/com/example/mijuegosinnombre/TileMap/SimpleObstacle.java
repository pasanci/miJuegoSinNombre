package com.example.mijuegosinnombre.TileMap;

public class SimpleObstacle {
    public static final int NONE = 0;

    public static final int TUTORIAL = -1;

    public static final int HALF = 0;
    public static final int HOLE = 1;
    public static final int SMALLHOLE = 2;
    public static final int CENTER = 3;

    public static final int STEADY = 0;
    public static final int ROTATING = 1;

    public static final int SOLID = 0;
    public static final int FADING = 1;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public int getType() {
        return type;
    }

    public int getRotating() {
        return rotating;
    }

    public int getFading() {
        return fading;
    }

    public int getSide() {
        return side;
    }

    public int getFadingSide() {
        return fadingSide;
    }

    public int getInstance() {
        return instance;
    }

    private int type;
    private int rotating;
    private int fading;
    private int side;
    private int fadingSide;
    private int instance;

    public SimpleObstacle(int instance){
        this.instance = instance;
    }

    public SimpleObstacle(int type, int rotating, int fading, int side){
        this.type = type;
        this.rotating = rotating;
        this.fading = fading;
        this.side = side;
        this.fadingSide = NONE;
    }
    public SimpleObstacle(int type, int rotating, int fading, int side, int fadingSide){
        this.type = type;
        this.rotating = rotating;
        this.fading = fading;
        this.side = side;
        this.fadingSide = fadingSide;
    }
}
