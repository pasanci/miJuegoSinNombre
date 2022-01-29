package TileMap;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Textures {

	public BufferedImage marble;
	public BufferedImage fire;
	public BufferedImage ice;
	public BufferedImage chikito;
	public BufferedImage splash;
	public BufferedImage splashred;
	public BufferedImage splashazul;
	public BufferedImage splashrojo;
	public Image gifFire;
	public Image gifBolaFuego;
	public Image gifIce;
	public Image gifBolaHielo;
	
	public Textures() {
		try {
			this.fire = ImageIO.read(getClass().getResourceAsStream("/fuego.gif"));
			this.ice = ImageIO.read(getClass().getResourceAsStream("/hielo.gif"));
			this.marble = ImageIO.read(getClass().getResourceAsStream("/marmol2.jpg"));
			this.chikito = ImageIO.read(getClass().getResourceAsStream("/chikito.jpg"));
			this.splash = ImageIO.read(getClass().getResourceAsStream("/splash.png"));
			this.splashred = ImageIO.read(getClass().getResourceAsStream("/splashred.png"));
			this.splashazul = ImageIO.read(getClass().getResourceAsStream("/splashazul.png"));
			this.splashrojo = ImageIO.read(getClass().getResourceAsStream("/splashrojo.png"));
			this.gifFire = new ImageIcon(this.getClass().getResource("/fuego.gif")).getImage();
			this.gifBolaFuego = new ImageIcon(this.getClass().getResource("/bolafuego.gif")).getImage();
			this.gifIce = new ImageIcon(this.getClass().getResource("/hielo2.gif")).getImage();
			this.gifBolaHielo = new ImageIcon(this.getClass().getResource("/bolahielo.gif")).getImage();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
