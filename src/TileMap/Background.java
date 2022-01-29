package TileMap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import Main.Main;

public class Background {

	private BufferedImage image;
	private Color color;
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Background(String s, double ms) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Background(Color c) {
		try {
			color = c;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % Main.WIDTH;
		this.y = (y * moveScale) % Main.HEIGHT;
	}
	
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g) {
		if(image!=null) {
			
			g.drawImage(image, (int)x, (int)y, Main.WIDTH, image.getHeight(), null);
			if(x<0) {
				g.drawImage(image, (int)x + Main.WIDTH, (int)y, null);
			}
			if(x>0){
				g.drawImage(image, (int)x - Main.WIDTH, (int)y, null);
			}
		}
		else {
			g.setColor(this.color);
			g.fillRect (0, 0, (int)x + Main.WIDTH, (int)y + Main.HEIGHT);
		}
	}
}
