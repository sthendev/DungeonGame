/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon implements Observer {

    private int width, height;
    private ArrayList<ArrayList<ArrayList<Entity>>> entities;
    private Player player;
    private Goal goal;
    private ArrayList<Enemy> enemies;
    private ArrayList<Switch> switches;
    private String state;

    public Dungeon(int width, int height, Goal goal) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<ArrayList<ArrayList<Entity>>>();
        this.enemies = new ArrayList<Enemy>();
        this.player = null;
        this.goal = goal;
        this.switches = new ArrayList<Switch>();
        this.state = "unfinished";
    }
    
    public ArrayList<ArrayList<ArrayList<Entity>>> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<ArrayList<ArrayList<Entity>>> entities) {
		this.entities = entities;
	}

	public int playerXPos() {
    	return player.getX();
    }
    
    public int playerYPos() {
    	return player.getY();
    }
    
    public void addEnemy(Enemy e) {
    	getEnemies().add(e);
    }
    
    public void removeEnemy(Enemy e) {
    	getEnemies().remove(e);
    }
    
    public void addSwitch(Switch s) {
    	getSwitches().add(s);
    }
    
    public void removeSwitch(Switch s) {
    	getSwitches().remove(s);
    }
    
    public void addEntity(Entity entity, int x, int y) {
    	getEntities().get(x).get(y).add(entity);
    }
    
    public void removeEntity(Entity entity, int x, int y) {
    	getEntities().get(x).get(y).remove(entity);
    }
    
    public ArrayList<Entity> getTile(int x, int y) {
    	return getEntities().get(x).get(y);
    }
    
    public boolean canMove(Entity e, int x, int y) {
    	if (getTile(x, y).canMove(e)) {
			return true;
		}
    	return false;
    }

    public void update(Subject s) {
    	Entity e = (Entity) s;
    	int x = e.getX();
    	int y = e.getY();
		ArrayList<Entity> onSquare = getEntities().get(x).get(y);
    	if (e instanceof Player) {
    		Player p = (Player) e;
    		if (p.getState().equals("alive")) {
    			// First interact with enemies if there's any
    			checkCoincide();
    			// Then update status of all floor switches
    			updateSwitches();
    			// Then interact with other props
	    		for (Entity en : onSquare) {
	    			if (!(en instanceof Enemy || en instanceof Switch)) {
		    			en.handleInteraction(p);
	    			}
	    		}
	    		if (checkGoal()) {
	    			endGame(true);
	    		}
	    		if (this.getEnemies().size() > 0) {
		    		moveEnemy(); //Enemies move after the player in one go
		    		enemyInteraction(); //Then interact with entities
	    		}
    		} else if (p.getState().equals("dead")) {
    			endGame(false);
    		}
    	} else if (e instanceof Enemy) {
    		Enemy en = (Enemy) e;
    		if (en.getState().equals("dead")) {
    			getEnemies().remove(en);
    			removeEntity(en, en.getX(), en.getY());
    		}
    	}
    }
    
    public void updateSwitches() {
    	for (Switch s : getSwitches()) {
    		s.checkState();
    	}
    }
    
    public boolean checkGoal() {
    	return goal.accomplished();
    }
    
    //Move all enemies after player executes a move
    public void moveEnemy() {
		for (Enemy e : getEnemies()) {
			e.move();
		}
    }
    
    //Check if player meets enemy after a change in the game
    public void checkCoincide() {
    	for (Enemy enemy : getEnemies()) {
			if (enemy.getX() == player.getX() && enemy.getY() == player.getY()) {
				enemy.handleInteraction(player);
			}
		}
    }
    
    //All enemies interact with props on the square they stand on after moving
    public void enemyInteraction() {
		// First interact with player if there's any
    	checkCoincide();
		// Then interact with other props
    	for (Enemy e : getEnemies()) {
    		int x = e.getX();
        	int y = e.getY();
    		ArrayList<Entity> onSquare = getEntities().get(x).get(y);
    		for (Entity en : onSquare) {
    			if (!(en instanceof Player)) {
        			en.handleInteraction(e);
    			}
    		}
		}
    }
    
    //To-do
    public void endGame(boolean end) {
    	if (end == true) {
    		setState("complete");
    	} else {
    		setState("fail");
    	}
    }

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

	public Goal getGoal() {
		return goal;
	}

	public void setGoal(Goal goal) {
		this.goal = goal;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public ArrayList<Switch> getSwitches() {
		return switches;
	}

	public void setSwitches(ArrayList<Switch> switches) {
		this.switches = switches;
	}
}
