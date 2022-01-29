package GameState;
import java.util.ArrayList;

import TileMap.Textures;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;
	private long frameTime;
	private double scale = 1.0;

	public static final int MENUSTATE = 0;
	public static final int OPTIONSSTATE = 1;
	public static final int RUNNINGSTATE = 2;
	protected Textures textures = new Textures();
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new OptionsState(this));
		gameStates.add(new RunningState(this));
	}
	
	public void setState (int state) {
		currentState = state;
		gameStates.get(currentState).init();
	}
	
	public void update() {
		gameStates.get(currentState).update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}

	public double getScale() {
		return this.scale;
	}
	
	public void setScale(double scale) {
		
		this.scale = (double)Math.round(scale * 10d) / 10d;
	}

	public long getFrameTime() {
		return this.frameTime;
	}
	
	public void setFrameTime(long frameTime) {
		this.frameTime = frameTime;
	}

	public void setPause() {
		gameStates.get(currentState).setPause();
	}
}
