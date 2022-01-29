package TileMap;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import Main.Main;

public class Player{

	private Color color1;
	private Color color2;
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
	
	public Player(Textures textures, Color color1, Color color2, double angle, double diameter, double ballDiameter) {
		try {
			this.color1 = color1;
			this.color2 = color2;
			this.angle = angle;
			this.diameter = diameter;
			this.ballRadius = ballDiameter/2;
			this.font = new Font("Arial",Font.PLAIN, (int) diameter/2);
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
		this.x = x * (Main.WIDTH/100.0);
		this.y = y * (Main.HEIGHT/100.0);
		centering = true;
		this.targetAngle = normalizeAngle(angle);
	}
	
	public void rotateToTarget() {
		double diference = this.angle - this.targetAngle;
		if(Math.abs(diference)<0.07/(16.66/frameTime)) {
			this.angle = this.targetAngle;
			centering = false;
		}
		else if(Math.abs(diference)>Math.PI/2 && Math.abs(diference)>(Math.PI)) {
			this.angle-=0.07/(16.66/frameTime);
		}
		else{
			this.angle+=0.07/(16.66/frameTime);
		}
		this.angle = normalizeAngle(this.angle);
	}
	
	public boolean isRotatingToTarget() {
		return this.centering;
	}
	
	public void setPosition(double x, double y) {
		this.x = x * (Main.WIDTH/100);
		this.y = y * (Main.HEIGHT/100);
	}
	
	public void setAngleLeft(boolean value) {
		this.angleLeft = value;
	}
	
	public double[] getCircles() {
		double x1 = (diameter/2 * Math.cos(angle) + this.x);
		double y1 = (diameter/2 * Math.sin(angle) + this.y);
		double x2 = (diameter/2 * Math.cos(Math.PI+angle) + this.x);
		double y2 = (diameter/2 * Math.sin(Math.PI+angle) + this.y);
		double[] circles = {x1, y1, x2, y2, ballRadius}; 
		return circles;
	}
	
	public void setAngleRight(boolean value) {
		this.angleRight = value;
	}
	
	public void setVector(double dx, double dy) {
	}
	
	public void update() {
		if(displayingLevel) {
			this.transparency = 1-((System.currentTimeMillis()-levelTime)/fadeTimeInms);
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
				this.angle-=0.07/(16.66/frameTime);
			}
			else if(angleRight) {
				this.angle+=0.07/(16.66/frameTime);
			}
		}
	}
	
	public static double normalizeAngle(double angle) {
	    return Math.atan2(Math.sin(angle), Math.cos(angle));
	}
	
	public void draw(Graphics2D g) {
		if(centering) {
			rotateToTarget();
		}
		this.angle = normalizeAngle(this.angle);
		g.setColor(Color.WHITE);
		g.drawOval((int)(this.x-diameter/2), (int)(this.y-diameter/2), (int)diameter, (int)diameter);
		
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency));
		g.setFont(font);
		g.drawString(this.level,(int) this.x-(g.getFontMetrics().stringWidth(this.level)/2),(int) (this.y+(g.getFontMetrics().getMaxAscent()/2.0)-g.getFontMetrics().getDescent()/2));
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
		g.setColor(this.color1);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		/*for(int i = 0; i<10; i++) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) (0.3*(i/10.0))));
			g.fillOval((int)((diameter/2 * Math.cos((this.angle-(this.lastAngle/10*i))) + this.x) - ballRadius*(i/(float)historico.length)), (int)((diameter/2 * Math.sin((this.angle-(this.lastAngle/10*i))) + this.y) - ballRadius*(i/(float)historico.length)), (int)(2 * ballRadius*(i/(float)historico.length)), (int)(2 * ballRadius*(i/(float)historico.length)));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		}*/
		
		int historicoIteratorAux = historicoIterator;
		for(int i = 0; i<historico.length; i++) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.3*(i/(float)historico.length)));
			g.fillOval((int)((diameter/2 * Math.cos(historico[historicoIteratorAux]) + this.x) - ballRadius*(i/(float)historico.length)), (int)((diameter/2 * Math.sin(historico[historicoIteratorAux]) + this.y) - ballRadius*(i/(float)historico.length)), (int)(2 * ballRadius*(i/(float)historico.length)), (int)(2 * ballRadius*(i/(float)historico.length)));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
			historicoIteratorAux++;
			if(historicoIteratorAux==historico.length) {
				historicoIteratorAux = 0;
			}
		}

		g.setColor(this.color2);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		historicoIteratorAux = historicoIterator;
		for(int i = 0; i<historico.length; i++) {
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.3*(i/(float)historico.length)));
			g.fillOval((int)((diameter/2 * Math.cos(Math.PI+historico[historicoIteratorAux]) + this.x) - ballRadius*(i/(float)historico.length)), (int)((diameter/2 * Math.sin(Math.PI+historico[historicoIteratorAux]) + this.y) - ballRadius*(i/(float)historico.length)), (int)(2 * ballRadius*(i/(float)historico.length)), (int)(2 * ballRadius*(i/(float)historico.length)));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
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

		Ellipse2D azul = new Ellipse2D.Float((int)((diameter/2 * Math.cos(angle) + this.x) - ballRadius), (int)((diameter/2 * Math.sin(angle) + this.y) - ballRadius), (int)(2 * ballRadius), (int)(2 * ballRadius));

		//g.setColor(this.color2);
		Ellipse2D rojo = new Ellipse2D.Float((int)((diameter/2 * Math.cos(Math.PI+angle) + this.x) - ballRadius), (int)((diameter/2 * Math.sin(Math.PI+angle) + this.y) - ballRadius), (int)(2 * ballRadius), (int)(2 * ballRadius));
		
		Rectangle r = new Rectangle(0,0,Main.WIDTH,Main.HEIGHT);
		//g.setClip(r);
		g.setClip(azul);
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
}
