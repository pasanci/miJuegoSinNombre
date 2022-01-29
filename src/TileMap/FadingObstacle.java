package TileMap;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import Main.Main;
import Main.Collision;

public class FadingObstacle extends Obstacle{
	double detectionDistance = 800.0;
	double fadeDistance = 300.0;
	float alphaValue = 1.0f;

	public FadingObstacle(Textures textures, double x, double y, double width, double length) {
		super(textures,x, y, width, length);
	}
	
	public FadingObstacle(Textures textures, double y, int percentajeFree, double length) {
		super(textures,y, percentajeFree, length);
	}
	
	public FadingObstacle(Textures textures, double y, int percentaje, double length, boolean side) {//false = left
		super(textures,y, percentaje, length, side);
	}

	public void update() {
		super.update();
		//System.out.println(y);
		if(!collisioned) {
			double distance = playerHeight - y;
			if(distance < detectionDistance && distance > fadeDistance) {
				alphaValue = (float) ((distance-fadeDistance)/(detectionDistance-fadeDistance));
			}
			else if(distance <= fadeDistance){
				alphaValue = 0.0f;
			}
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
		this.collisioned = false;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
		super.draw(g);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
	}
}
