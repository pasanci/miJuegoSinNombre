package GameState;

import TileMap.Background;
import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionsState extends GameState{

	private Background bg;

	private int currentChoice = 0;
	private double scale;
	private String[] options = {
		"Scale: " + scale
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public OptionsState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			//gsm.setScale((float)this.scale);
			bg = new Background(Color.BLACK);
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN,70);
			font = new Font("Arial",Font.PLAIN, 40);
			init();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void init() {
		this.scale = gsm.getScale();
		options[0]="Scale: " + ((float)this.scale);
	}
	public void update() {
		bg.update();
	}
	
	private void select() {
	}
	
	public void draw(Graphics2D g) {
		bg.draw(g);
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("El jueguito", 80, 70);
		
		g.setFont(font);
		for(int i=0; i<options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i],145,140+i*40);
		}
	}
	public void keyPressed(int k) {
		if(k==KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice<0) {
				currentChoice = options.length -1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice>options.length-1) {
				currentChoice = 0;
			}
		}
		if(k == KeyEvent.VK_LEFT) {
			if(currentChoice == 0 && scale>0.1) {
				scale -= 0.1;
				scale = (double)Math.round(scale * 10d) / 10d;
				options[0]="Scale: " + ((float)this.scale);
			}
		}
		if(k == KeyEvent.VK_RIGHT) {
			if(currentChoice == 0) {
				scale += 0.1;
				options[0]="Scale: " + ((float)this.scale);
			}
		}
		if(k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
		if(k == KeyEvent.VK_ENTER) {
			gsm.setScale((float)this.scale);
		}
	}
	
	public void keyReleased(int k) {
		
	}
	
	public void setPause() {
		
	}
}
