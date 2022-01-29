package TileMap;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import Main.Main;
import Main.Collision;

public class FadingRotatingObstacle extends RotatingObstacle{
	double detectionDistance = 800.0;
	double fadeDistance = 300.0;
	float alphaValue = 1.0f;

	public FadingRotatingObstacle(Textures textures, double x, double y, double width, double length, double initialAngle, double rotationSpeed) {
		super(textures,x, y, width, length, initialAngle , rotationSpeed);
	}
	
	public FadingRotatingObstacle(Textures textures, double y, int percentajeFree, double length, double initialAngle, double rotationSpeed) {
		super(textures,y, percentajeFree, length, initialAngle, rotationSpeed);
	}
	
	public FadingRotatingObstacle(Textures textures, double y, int percentaje, double length, boolean side, double initialAngle, double rotationSpeed) {//false = left
		super(textures,y, percentaje, length, side, initialAngle, rotationSpeed);
	}

	public void update() {
		super.update();
		if(!collisioned) {
			double distance = playerHeight - y;
			if(distance < detectionDistance && distance > fadeDistance) {
				alphaValue = (float) ((distance-fadeDistance)/(detectionDistance-fadeDistance));
			}
			else if(distance <= fadeDistance){
				alphaValue = 0.0f;
			}
			this.angle = normalizeAngle(this.angle);
		}
		else { //unfade
			collisionY-=resetSpeed;
			double distance = playerHeight - collisionY;
			if(distance < detectionDistance && distance > fadeDistance) {
				alphaValue = (float) ((distance-fadeDistance)/(detectionDistance-fadeDistance));
			}
			else if(distance <= fadeDistance){
				alphaValue = 0.0f;
			}
		}
	}
	
	public void restart() {
		super.restart();
		this.alphaValue = 1.0f;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
		super.draw(g);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
}
