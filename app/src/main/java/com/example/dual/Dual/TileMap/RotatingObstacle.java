package com.example.dual.Dual.TileMap;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

import androidx.core.content.ContextCompat;

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

    public RotatingObstacle(Textures textures, double x, double y, double width, double length, double initialAngle, double rotationSpeed) {
        super(textures,x, y, width, length);
        this.angle = initialAngle;
        this.rotationSpeed = rotationSpeed;

        //restart
        this.initialangle = this.angle;
        this.initialrotationSpeed = this.rotationSpeed;
        this.initialY = y;
    }

    public RotatingObstacle(Textures textures, double y, int percentajeFree, double length, double initialAngle, double rotationSpeed) {
        super(textures,y, percentajeFree, length);
        this.angle = initialAngle;
        this.rotationSpeed = rotationSpeed;
        //restart
        this.initialangle = this.angle;
        this.initialrotationSpeed = this.rotationSpeed;
        this.initialY = y;
    }

    public RotatingObstacle(Textures textures, double y, int percentaje, double length, boolean side, double initialAngle, double rotationSpeed) {//false = left
        super(textures,y, percentaje, length, side);
        this.angle = initialAngle;
        this.rotationSpeed = rotationSpeed;
        //restart
        this.initialangle = this.angle;
        this.initialrotationSpeed = this.rotationSpeed;
        this.initialY = y;
    }

    public void update() {
        super.update();
        //System.out.println(y);
        if(!collisioned) {
            angle+=this.rotationSpeed;
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

        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor(this.context, R.color.white));

        canvas.save();
        canvas.rotate((float)(angle*180 /Math.PI),(float)(x+width/2.0),(float)(y+height/2.0));
        canvas.drawRect ((int)(x), (int) (y), (int) (x + width), (int) (y + height), paint);
        canvas.restore();

        /*
        Rectangle r = new Rectangle(0,0,Main.WIDTH,Main.HEIGHT);
        Rectangle2D rect = new Rectangle2D.Double(-width/2., -height/2., width, height);
        AffineTransform transform = new AffineTransform();
        transform.translate(x+width/2.0, y+height/2.0);
        transform.rotate(angle);
        Shape rotatedRect = transform.createTransformedShape(rect);
        g.fill(rotatedRect);
        g.setClip(rotatedRect);
        //int[] vertices = getVertices();
        AffineTransform transform2 = new AffineTransform();
        transform2.translate(x+width/2.0, y+height/2.0);
        transform2.rotate(angle);
        transform2.translate(-width/2.0, -height/2.0 - randomHeight);
        g.drawImage(this.textures.marble, transform2, null);
        //g.drawImage(this.textures.marble, (int)x, (int)y, this.textures.marble.getWidth(), this.textures.marble.getHeight(), null);
        //g.drawImage(this.textures.marble, vertices[0], vertices[1], vertices[2], vertices[3], vertices[4], vertices[5], vertices[6], vertices[7], null);
*/
        /*
        for (Collision collision : collisionList) {
            Graphics2D g2d = (Graphics2D)g;
            AffineTransform old = g2d.getTransform();

            g2d.rotate(Math.PI+angle,this.x+width/2, this.y+height-20);
            g2d.translate((int)(collision.getX() - this.textures.splash.getWidth()/20), (int) ((this.y - this.initialY) + collision.getY() - this.textures.splash.getHeight()/20));

            //g2d.transform(transform2);
            if(!collision.getColor()) {
                g.setColor(Color.BLUE);
                g2d.drawImage(this.textures.splashazul, 0, 0, this.textures.splash.getWidth()/10, this.textures.splash.getHeight()/10, null);
                //g.drawImage(this.textures.splashazul, (int)(this.x - collision.getX() - this.textures.splash.getWidth()/20), (int) (this.y - collision.getY() - collision.getSplashHeight() - this.textures.splash.getHeight()/10), this.textures.splash.getWidth()/10, this.textures.splash.getHeight()/10, null);
            }
            else {
                g.setColor(Color.RED);
                g2d.drawImage(this.textures.splashrojo, 0, 0, this.textures.splash.getWidth()/10, this.textures.splash.getHeight()/10, null);
                //g.drawImage(this.textures.splashrojo, (int)(this.x - collision.getX() - this.textures.splash.getWidth()/20), (int) (this.y - collision.getY() - collision.getSplashHeight() - this.textures.splash.getHeight()/10), this.textures.splash.getWidth()/10, this.textures.splash.getHeight()/10, null);
            }
            g2d.setTransform(old);
            //double ballRadius = 20;
            //Ellipse2D azul = new Ellipse2D.Float((int)((int)(collision.getX()) - ballRadius), (int)((collision.getY()) - ballRadius), (int)(2.0 * ballRadius), (int)(2 * ballRadius));
            //g.draw(azul);
            g.setColor(Color.WHITE);

        }
        g.setClip(r);

        //double ballRadius = 20;
        //Ellipse2D azul = new Ellipse2D.Float((int)(this.x+width/2.0 - ballRadius), (int)(this.y+height-20 - ballRadius), (int)(2.0 * ballRadius), (int)(2 * ballRadius));
        //double ballRadius2 = 10;
        //Ellipse2D azul2 = new Ellipse2D.Float((int)(this.x+width/2.0 - ballRadius2), (int)(this.y+height-20 - ballRadius2), (int)(2.0 * ballRadius2), (int)(2 * ballRadius2));
        //g.draw(azul2);
        //g.draw(azul);


         */
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
