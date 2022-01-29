package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TileMap.FadingObstacle;
import TileMap.FadingRotatingObstacle;
import TileMap.Obstacle;
import TileMap.RotatingObstacle;
import TileMap.Textures;

public class Levels {
	
	private HashMap<Integer, List<Obstacle>> levels = new HashMap<Integer, List<Obstacle>>();
	List<Obstacle> obstacles = new ArrayList<Obstacle>();
	Textures textures;
	
	public Levels(Textures textures) {
		this.textures = textures;
	}

	public List<Obstacle> restartLevel() {
		for (Obstacle obstacle : obstacles) {
			obstacle.restart();
		}
		return obstacles;
	}
	
	public List<Obstacle> resetLevel() {
		for (Obstacle obstacle : obstacles) {
			obstacle.reset();
		}
		return obstacles;
	}
	
	//level1
	public List<Obstacle> loadLevel(int number) {
		switch(number)
		{
		case 0:
			obstacles.clear();
			obstacles.add(new RotatingObstacle(textures,-300,25,40,Math.PI,0.07));
			//obstacles.add(new Obstacle(0,-300,Main.WIDTH/2-20,40));
			levels.put(1,obstacles);
	      break;
		case 1:
			obstacles.clear();
			
			obstacles.add(new Obstacle(textures,0,-300,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,0,-600,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,0,-900,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,Main.WIDTH/2+20,-1200,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			obstacles.add(new Obstacle(textures,Main.WIDTH/2+20,-1500,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			obstacles.add(new Obstacle(textures,Main.WIDTH/2+20,-1800,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			
			levels.put(number,obstacles);
	      break;
		case 2:
			obstacles.clear();
			obstacles.add(new Obstacle(textures,-300, 60, 40, true));
			obstacles.add(new Obstacle(textures,-300, 20, 40, false));
			obstacles.add(new Obstacle(textures,-600, 60, 40, false));
			obstacles.add(new Obstacle(textures,-600, 20, 40, true));
			levels.put(number,obstacles);
	      break;
		case 3:
			obstacles.clear();
			obstacles.add(new Obstacle(textures,-300, 60, 40, true));
			obstacles.add(new Obstacle(textures,-300, 20, 40, false));
			obstacles.add(new Obstacle(textures,-600, 60, 40, false));
			obstacles.add(new Obstacle(textures,-600, 20, 40, true));
			obstacles.add(new Obstacle(textures,-900, 60, 40, false));
			obstacles.add(new Obstacle(textures,-900, 20, 40, true));
			obstacles.add(new Obstacle(textures,-1200, 60, 40, true));
			obstacles.add(new Obstacle(textures,-1200, 20, 40, false));
			levels.put(number,obstacles);
	      break;
		case 4:
			obstacles.clear();
			obstacles.add(new RotatingObstacle(textures,-500,25,40,Math.PI,0.07));
			obstacles.add(new RotatingObstacle(textures,-1100,25,40,Math.PI,0.07));
			obstacles.add(new RotatingObstacle(textures,-1900,25,40,Math.PI,-0.07));
			levels.put(number,obstacles);
	      break;
		case 5:
			obstacles.clear();
			obstacles.add(new Obstacle(textures,-300, 60, 40, true));
			obstacles.add(new Obstacle(textures,-300, 20, 40, false));
			obstacles.add(new Obstacle(textures,0,-600,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,0,-900,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,0,-1200,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,-1500, 60, 40, false));
			obstacles.add(new Obstacle(textures,-1500, 20, 40, true));
			obstacles.add(new Obstacle(textures,Main.WIDTH/2+20,-1800,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			obstacles.add(new Obstacle(textures,Main.WIDTH/2+20,-2100,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			obstacles.add(new Obstacle(textures,Main.WIDTH/2+20,-2400,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			levels.put(number,obstacles);
	      break;
		case 6:
			obstacles.clear();
			obstacles.add(new Obstacle(textures,0,-300,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,0,-600,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,-900, 60, 40, false));
			obstacles.add(new Obstacle(textures,-900, 20, 40, true));
			obstacles.add(new Obstacle(textures,-1200, 40, 40));
			obstacles.add(new Obstacle(textures,-1500, 60, 40, true));
			obstacles.add(new Obstacle(textures,-1500, 20, 40, false));
			obstacles.add(new Obstacle(textures,-1800, 40, 40));
			obstacles.add(new Obstacle(textures,-2100, 50, 40, true));
			obstacles.add(new Obstacle(textures,-2400, 50, 40, true));
			obstacles.add(new Obstacle(textures,-2700, 60, 40, true));
			obstacles.add(new Obstacle(textures,-2700, 20, 40, false));
			levels.put(number,obstacles);
	      break;

		case 7:
			obstacles.clear();
			obstacles.add(new Obstacle(textures,0,-300,Main.WIDTH/2-20,40));
			//obstacles.add(new Obstacle(textures,0,-300,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,0,-600,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,0,-900,Main.WIDTH/2-20,40));
			obstacles.add(new FadingObstacle(textures,Main.WIDTH/2+20,-1200,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			obstacles.add(new Obstacle(textures,Main.WIDTH/2+20,-1500,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			obstacles.add(new Obstacle(textures,Main.WIDTH/2+20,-1800,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			//obstacles.add(new RotatingObstacle(Main.WIDTH/2+20,-1800,(Main.WIDTH)-(Main.WIDTH/2+20),40,0,0.07));

			levels.put(number,obstacles);
	      break;
		case 8:
			obstacles.clear();
			obstacles.add(new Obstacle(textures,0,-300,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,0,-600,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,-900, 60, 40, false));
			obstacles.add(new Obstacle(textures,-900, 20, 40, true));
			obstacles.add(new FadingObstacle(textures,0,-1200,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,-1500, 60, 40, true));
			obstacles.add(new Obstacle(textures,-1500, 20, 40, false));
			obstacles.add(new FadingObstacle(textures,-1800, 40, 40));
			obstacles.add(new Obstacle(textures,-2100, 50, 40, true));
			obstacles.add(new Obstacle(textures,-2400, 50, 40, true));
			obstacles.add(new FadingObstacle(textures,-2700, 60, 40, true));
			obstacles.add(new FadingObstacle(textures,-2700, 20, 40, false));
			levels.put(number,obstacles);
	      break;
		case 9:
			obstacles.clear();
			obstacles.add(new FadingObstacle(textures,-300, 60, 40, true));
			obstacles.add(new FadingObstacle(textures,-300, 20, 40, false));
			obstacles.add(new FadingObstacle(textures,-600, 60, 40, false));
			obstacles.add(new FadingObstacle(textures,-600, 20, 40, true));
			levels.put(number,obstacles);
	      break;
		case 10:
			obstacles.clear();
			obstacles.add(new FadingObstacle(textures,-300, 60, 40, true));
			obstacles.add(new FadingObstacle(textures,-300, 20, 40, false));
			obstacles.add(new Obstacle(textures,-600, 60, 40, false));
			obstacles.add(new Obstacle(textures,-600, 20, 40, true));
			obstacles.add(new FadingObstacle(textures,-900, 60, 40, false));
			obstacles.add(new FadingObstacle(textures,-900, 20, 40, true));
			obstacles.add(new FadingObstacle(textures,-1200, 60, 40, true));
			obstacles.add(new FadingObstacle(textures,-1200, 20, 40, false));
			levels.put(number,obstacles);
	      break;
		case 11:
			obstacles.clear();
			obstacles.add(new FadingRotatingObstacle(textures,-300,25,40,Math.PI,0.07));
			obstacles.add(new RotatingObstacle(textures,-1100,25,40,Math.PI,0.07));
			obstacles.add(new FadingRotatingObstacle(textures,-1900,25,40,Math.PI,-0.07));
			levels.put(number,obstacles);
	      break;
		case 12:
			obstacles.clear();
			obstacles.add(new FadingObstacle(textures,-300, 60, 40, true));
			obstacles.add(new Obstacle(textures,-300, 20, 40, false));
			obstacles.add(new FadingObstacle(textures,0,-600,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,0,-900,Main.WIDTH/2-20,40));
			obstacles.add(new FadingObstacle(textures,0,-1200,Main.WIDTH/2-20,40));
			obstacles.add(new Obstacle(textures,-1500, 60, 40, false));
			obstacles.add(new Obstacle(textures,-1500, 20, 40, true));
			obstacles.add(new FadingObstacle(textures,Main.WIDTH/2+20,-1800,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			obstacles.add(new Obstacle(textures,Main.WIDTH/2+20,-2100,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			obstacles.add(new FadingObstacle(textures,Main.WIDTH/2+20,-2400,(Main.WIDTH)-(Main.WIDTH/2+20),40));
			levels.put(number,obstacles);
	      break;
		case 13:
			obstacles.clear();
			obstacles.add(new FadingObstacle(textures,0,-300,Main.WIDTH/2-20,40));
			obstacles.add(new FadingObstacle(textures,0,-600,Main.WIDTH/2-20,40));
			obstacles.add(new FadingObstacle(textures,-900, 60, 40, false));
			obstacles.add(new FadingObstacle(textures,-900, 20, 40, true));
			obstacles.add(new FadingObstacle(textures,-1200, 40, 40));
			obstacles.add(new FadingObstacle(textures,-1500, 60, 40, true));
			obstacles.add(new FadingObstacle(textures,-1500, 20, 40, false));
			obstacles.add(new FadingObstacle(textures,-1800, 40, 40));
			obstacles.add(new FadingObstacle(textures,-2100, 50, 40, true));
			obstacles.add(new FadingObstacle(textures,-2400, 50, 40, true));
			obstacles.add(new FadingObstacle(textures,-2700, 60, 40, true));
			obstacles.add(new FadingObstacle(textures,-2700, 20, 40, false));
			levels.put(number,obstacles);
	      break;
	    default:
	    	obstacles.clear();
	    	return obstacles;
		}
		return obstacles;
	}

}
