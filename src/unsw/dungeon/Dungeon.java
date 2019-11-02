/**
 *
 */
package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

import java.awt.geom.Point2D;

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
	private Tile[][] board;
    private Player player;
    private Goal goal;
    private ArrayList<Enemy> enemies;
    private String state;

    public Dungeon(int width, int height, Goal goal) {
        this.width = width;
		this.height = height;
        this.enemies = new ArrayList<Enemy>();
        this.player = null;
        this.goal = goal;
        this.state = "unfinished";
        initializeBoard(width, height);
    }
    
    private void initializeBoard(int width, int height) {
    	this.board = new Tile[height][width];
    	for (int row = 0; row < height; row ++) {
    		for (int col = 0; col < width; col++) {
    			board[row][col] = new Tile(this, col, row);
    		}
    	}
    }
    
    public Tile getTile(int x, int y) {
    	if (x < 0 || x >= width
    		|| y < 0 || y >= height) 
    		return null;
    	return board[y][x];
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
    
    public void moveEntity(Movable entity, Tile tile) {
    	entity.getCurrentTile().movedOff(entity);
    	tile.movedOn(entity);
    }
    
    public void addEntity(Entity entity, int x, int y) {
    	Tile tile = getTile(x, y);
    	tile.placeEntity(entity);
    	entity.setCurrentTile(tile);
    }
    
    public void removeEntity(Entity e, int x, int y) {
    	getTile(x, y).removeEntity(e);
    }
    
    public List<Tile> getTilesToPlayer(Entity entity) {
    	List<Tile> tiles = new ArrayList<>();
    	
    	int entityX = entity.getX();
    	int entityY = entity.getY();
    	int playerX = player.getX();
    	int playerY = player.getY();
    	
    	if (entityX == playerX) {
    		if (entityY < playerY) {
    			for (int i = entityY + 1; i <= playerY; i++) {
    				tiles.add(board[i][entityX]);
    			}
    		} else if (entityY > playerY) {
    			for (int i = entityY - 1; i >= playerY; i--) {
    				tiles.add(board[i][entityX]);
    			}
    		}
    	} else if (entityY == playerY) {
    		if (entityX < playerX) {
    			for (int i = entityX + 1; i <= playerX; i++) {
    				tiles.add(board[entityY][i]);
    			}
    		} else if (entityX > playerX) {
    			for (int i = entityX - 1; i >= playerX; i--) {
    				tiles.add(board[entityY][i]);
    			}
    		}
    	}
    	
    	return tiles;
    }
    
    public double distToPlayer(Tile tile) {
    	return Point2D.distance((double) tile.getX(), (double) tile.getY(),
    			(double) player.getX(), (double) player.getY());
    }
    
    public void playTurn() {
    	List<Enemy> enemiesCopy = new ArrayList<>(enemies);
    	for (Enemy enemy : enemiesCopy) {
    		enemy.move();
    	}
    }
    public void update(Subject s) {
    	Entity e = (Entity) s;
		List<Entity> onSquare = e.entityOverlapped();
    	if (e instanceof Player) {
    		Player p = (Player) e;
    		if (p.getStillExists() == true) {
    			// First interact with enemies if there's any
    			checkCoincide();
    			// Then interact with other props
	    		for (Entity en : onSquare) {
	    			if (!(en instanceof Enemy)) {
	    				en.notifyComing(p);
	    			}
	    		}
	    		if (checkGoal()) {
	    			endGame(true);
	    		}
	    		if (this.getEnemies().size() > 0) {
		    		moveEnemy(); //Enemies move after the player in one go
		    		enemyInteraction(); //Then interact with entities
	    		}
    		} else if (p.getStillExists() == false) {
    			endGame(false);
    		}
    	}
    }
    
  //Check if player meets enemy after a change in the game
    public void checkCoincide() {
    	for (Enemy enemy : getEnemies()) {
			if (enemy.getX() == player.getX() && enemy.getY() == player.getY()) {
				enemy.notifyComing(player);
			}
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
    
    //All enemies interact with props on the square they stand on after moving
    public void enemyInteraction() {
		// First interact with player if there's any
    	checkCoincide();
		// Then interact with other props
    	for (Enemy e : getEnemies()) {
    		List<Entity> onSquare = e.entityOverlapped();
    		for (Entity en : onSquare) {
    			if (!(en instanceof Player)) {
        			en.notifyComing(e);
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

	public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
