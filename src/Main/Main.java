package Main;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GameState.GameStateManager;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Main extends JPanel implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 768;
	public static final int HEIGHT = 1366;
	//public static final int HEIGHT = (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.95);
	//public static final int WIDTH = (int)(HEIGHT*(768.0/1366));
	public double SCALE;
	JFrame window;
	
	private Thread thread;
	private boolean running;
	private int FPS = 144;
	private long targetTime = 1000/FPS;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private GameStateManager gsm;
	
	public Main(JFrame window) {
		super();
		this.window = window;
		//Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		//this.SCALE = 1080.0/HEIGHT;
		this.SCALE = Toolkit.getDefaultToolkit().getScreenSize().height/(HEIGHT)*0.99;
		//System.out.println(Toolkit.getDefaultToolkit().getScreenSize().height);
		
		gsm = new GameStateManager();
		gsm.setScale(this.SCALE);
		init();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init() {
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
        this.setVisible(true);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE)));
        this.requestFocus();
	}

	private void update() {
		gsm.update();
		if(gsm.getScale() != this.SCALE) {
			this.SCALE = gsm.getScale();
			this.setSize((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE));
			revalidate();
			repaint();
			//System.out.println(this.SCALE);
			init();
		}
	}
	
	private void draw() {
		gsm.draw(g);
	}
	
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image,0,0, (int) (WIDTH * SCALE), (int) (HEIGHT * SCALE),null);
		g2.dispose();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gsm.keyReleased(e.getKeyCode());
	}

	@Override
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		
		while(running) {
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			window.pack();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed /1000000;
			if(wait<0) {
				gsm.setFrameTime(elapsed/1000000);
				wait = 0;
			}
			else {
				try {
					Thread.sleep(wait);
					gsm.setFrameTime(targetTime);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
public static void main(String[] args) {
		
		JFrame window = new JFrame("Dual");
		window.setContentPane(new Main(window));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		window.pack();
		window.validate();
		window.repaint();
	}
}
