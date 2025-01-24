package com.example.dual.Dual.TileMap;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

import androidx.core.content.ContextCompat;

import com.example.dual.Dual.GameState.GameStateManager;
import com.example.dual.Dual.Main.Collision;
import com.example.dual.Dual.Main.AffineTransform;
import com.example.dual.R;

import java.util.Vector;

public class RotatingObstacle extends Obstacle{
    double angle;
    double rotationSpeed;
    double initialangle;
    double initialrotationSpeed;
    double initialY;
    boolean direction;

    public RotatingObstacle(GameStateManager gsm, Textures textures, double x, double y, double width, double length, double initialAngle, boolean direction) {
        super(gsm, textures,x, y, width, length);
        this.angle = initialAngle;
        this.direction = direction;
        //restart
        this.initialangle = this.angle;
        this.initialrotationSpeed = this.rotationSpeed;
        this.initialY = y;
    }

    public RotatingObstacle(GameStateManager gsm, Textures textures, double y, int percentajeFree, double length, double initialAngle, boolean direction) {
        super(gsm, textures,y, percentajeFree, length);
        this.angle = initialAngle;
        this.direction = direction;
        //restart
        this.initialangle = this.angle;
        this.initialrotationSpeed = this.rotationSpeed;
        this.initialY = y;
    }

    public RotatingObstacle(GameStateManager gsm, Textures textures, double y, int percentaje, double length, boolean side, double initialAngle, boolean direction) {//false = left
        super(gsm, textures,y, percentaje, length, side);
        this.angle = initialAngle;
        this.direction = direction;
        //restart
        this.initialangle = this.angle;
        this.initialrotationSpeed = this.rotationSpeed;
        this.initialY = y;
    }

    public void update() {
        this.rotationSpeed = 0.07/(16.66/16.0) * (frameTime/16.0);
        super.update();
        //System.out.println(y);
        if(!collisioned) {
            if(direction) {
                angle += this.rotationSpeed;
            }
            else{
                angle -= this.rotationSpeed;
            }
            this.angle = normalizeAngle(this.angle);
        }
    }

    public static double normalizeAngle(double angle) {
        return Math.atan2(Math.sin(angle), Math.cos(angle));
    }

    public void restart() {
        super.restart();
        this.angle = this.initialangle;
        this.rotationSpeed = this.initialrotationSpeed;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        int fx = (int) (this.x/this.gsm.getWidth()*this.gsm.getActualWidth());
        int fy = (int) (this.y/this.gsm.getHeight()*this.gsm.getActualHeight());
        int fWidth = (int) (this.width/this.gsm.getWidth()*this.gsm.getActualWidth());
        int fHeight = (int) (this.height/this.gsm.getHeight()*this.gsm.getActualHeight());
        canvas.rotate((float)(angle*180 /Math.PI),(float)(fx+fWidth/2.0),(float)(fy+fHeight/2.0));
        Rect imageBounds = new Rect(fx, fy, (fx + fWidth), (fy + fHeight));
        textures.marble.setBounds(imageBounds);
        textures.marble.draw(canvas);
        canvas.restore();
    }

    @Override
    public double[] getBox() {
        AffineTransform transform = new AffineTransform();
        transform.translate(x+width/2, y+height/2);
        transform.rotate(angle);
        PointF tip = new PointF((float)-width/2, (float)-height/2);
        PointF tip2 = new PointF((float)width/2, (float)height/2);
        transform.transform(tip, tip);
        transform.transform(tip2, tip2);
        double[] box = {tip.x, tip.y, tip2.x, tip2.y};
        return box;
    }

    public int[] getVertices() {
        AffineTransform transform = new AffineTransform();
        transform.translate(x+width/2, y+height/2);
        transform.rotate(angle);
        PointF tip = new PointF((float)-width/2, (float)-height/2);
        PointF tip2 = new PointF((float)width/2, (float)-height/2);
        PointF tip3 = new PointF((float)-width/2, (float)height/2);
        PointF tip4 = new PointF((float)width/2, (float)height/2);
        transform.transform(tip, tip);
        transform.transform(tip2, tip2);
        transform.transform(tip3, tip3);
        transform.transform(tip4, tip4);
        int[] box = {(int)tip.x, (int)tip.y, (int)tip2.x, (int)tip2.y, (int)tip3.x, (int)tip3.y, (int)tip4.x, (int)tip4.y};
        return box;
    }

    @Override
    public boolean getCollision(double cx, double cy, double radius) {
        AffineTransform transform = new AffineTransform();
        transform.translate(((x+width/2)/this.gsm.getWidth()*this.gsm.getActualWidth()), ((y+height/2)/this.gsm.getHeight()*this.gsm.getActualHeight()));
        transform.rotate(angle);
        PointF tip = new PointF((float)(-width/2/this.gsm.getWidth()*this.gsm.getActualWidth()), (float)(-height/2)/this.gsm.getHeight()*this.gsm.getActualHeight());
        PointF tip2 = new PointF((float)(width/2/this.gsm.getWidth()*this.gsm.getActualWidth()), (float)(-height/2)/this.gsm.getHeight()*this.gsm.getActualHeight());
        PointF tip3 = new PointF((float)(-width/2/this.gsm.getWidth()*this.gsm.getActualWidth()), (float)(height/2)/this.gsm.getHeight()*this.gsm.getActualHeight());
        PointF tip4 = new PointF((float)(width/2/this.gsm.getWidth()*this.gsm.getActualWidth()), (float)(height/2)/this.gsm.getHeight()*this.gsm.getActualHeight());
        transform.transform(tip, tip);
        transform.transform(tip2, tip2);
        transform.transform(tip3, tip3);
        transform.transform(tip4, tip4);
        //g.setColor(Color.GREEN);
        //g.fillOval((int)(tip.x-5), (int)(tip.y-5), (int)(2 * 5), (int)(2 * 5));
        //g.fillOval((int)(tip3.x-5), (int)(tip3.y-5), (int)(2 * 5), (int)(2 * 5));
        if(pDistance(cx,cy,tip.x,tip.y,tip2.x,tip2.y,1)<radius) {
            if(FindPoint(cx,cy, tip.x,tip.y,tip2.x,tip2.y,radius)) {
                return true;
            }

        }
        if(pDistance(cx,cy,tip2.x,tip2.y,tip4.x,tip4.y,2)<radius) {
            if(FindPoint(cx,cy,tip2.x,tip2.y,tip4.x,tip4.y,radius)) {
                return true;
            }
        }
        if(pDistance(cx,cy,tip3.x,tip3.y,tip4.x,tip4.y,3)<radius) {
            if(FindPoint(cx,cy,tip3.x,tip3.y,tip4.x,tip4.y,radius)) {
                return true;
            }
        }
        if(pDistance(cx,cy,tip3.x,tip3.y,tip.x,tip.y,4)<radius) {
            if(FindPoint(cx,cy,tip3.x,tip3.y,tip.x,tip.y,radius)) {
                return true;
            }
        }
        return false;
    }

    static boolean FindPoint(double x, double y, double x1, double y1, double x2, double y2, double radius){
        double m = ((y2-y1)/(x2-x1));
        double b = y1-(m*x1);
        double m2 = -1/m;
        double b2 = y-(m2*x);
        double intx = (b - b2) / (m2 - m);
        double inty = m2 * intx + b2;

        double AB = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        double AP = Math.sqrt((intx-x1)*(intx-x1)+(inty-y1)*(inty-y1));
        double PB = Math.sqrt((x2-intx)*(x2-intx)+(y2-inty)*(y2-inty));
        if(Math.abs(AB - (AP + PB))<0.1) {
            return true;
        }
        else if(Math.abs((x2-x)*(x2-x) + (y2-y)*(y2-y))<radius*radius || Math.abs((x1-x)*(x1-x) + (y1-y)*(y1-y))<radius*radius) {
            return true;
        }
        return false;
    }

    public double pDistance(double x, double y, double x1, double y1, double x2, double y2, int n) {

        double A = x - x1; // position of point rel one end of line
        double B = y - y1;
        double C = x2 - x1; // vector along line
        double D = y2 - y1;
        double E = -D; // orthogonal vector
        double F = C;
        double dot = A * E + B * F;
        double len_sq = E * E + F * F;

        return (Math.abs(dot) / Math.sqrt(len_sq));
    }

    public void appendCollision(double x, double y, boolean selector) {

        AffineTransform transform = new AffineTransform();
        transform.rotate(initialangle-angle,this.x+width/2, this.y+height-20);
        PointF tip = new PointF((float)x, (float)y);
        transform.transform(tip, tip);
        PointF center = new PointF((float)(this.x+width/2), (float)(this.y+height-20));
        transform.transform(center, center);

        Collision col;
        if(tip.y-center.y>0) {
            col = new Collision(tip.x,initialY+(tip.y-center.y-0.25*height),selector);
        }
        else {
            col = new Collision(tip.x,initialY+(tip.y-center.y+1.5*height)/2,selector);
        }
        collisionList.add(col);
    }
}
