package GameState;

import TileMap.Background;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{

	private Background bg;

	private int currentChoice = 0;
	private String[] options = {
		"Start",
		"Options",
		"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	private Font font;
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			//bg = new Background("/menubg.gif",1);
			//bg.setVector(-0.1, 0);
			bg = new Background(Color.BLACK);
			titleColor = new Color(128, 0, 0);
			titleFont = new Font("Century Gothic", Font.PLAIN,70);
			font = new Font("Arial",Font.PLAIN, 40);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void init() {}
	public void update() {
		bg.update();
	}
	
	private void select() {
		if(currentChoice == 0) {//start
			gsm.setState(GameStateManager.RUNNINGSTATE);
		}
		if(currentChoice == 1) {//help
			gsm.setState(GameStateManager.OPTIONSSTATE);
		}
		if(currentChoice == 2) {//quit
			System.exit(0);
		}
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
	}

	public void keyReleased(int k) {
		
	}
	
	public void setPause() {
		
	}
}
