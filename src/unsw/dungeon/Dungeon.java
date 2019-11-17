/**
 *
 */
package unsw.dungeon;

import java.util.List;
import java.util.ArrayList;

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
    private List<Enemy> enemies;
    private List<Door> doors;
    private Goal goal;
    private MovementStrategy offensiveStrategy;
    private MovementStrategy defensiveStrategy;
    private MovementStrategy idleStrategy;
    private boolean gameOver;
    private DungeonController controller;

    public Dungeon(int width, int height) {
        this.width = width;
		this.height = height;
        this.enemies = new ArrayList<Enemy>();
        this.player = null;
        this.enemies = new ArrayList<>();
        this.doors = new ArrayList<>();
        this.offensiveStrategy = new OffensiveEnemy();
        this.defensiveStrategy = new DefensiveEnemy();
        this.idleStrategy = new IdleEnemy();
        this.gameOver = false;
        initializeBoard(width, height);
    }
    
    /**
     * initialize dungeon with specified width and height
     * @param width
     * @param height
     */
    private void initializeBoard(int width, int height) {
    	this.board = new Tile[height][width];
    	for (int row = 0; row < height; row ++) {
    		for (int col = 0; col < width; col++) {
    			board[row][col] = new Tile(this, col, row);
    		}
    	}
    }
    
    /**
     * inform dungeon of associated UI controller
     * @param controller
     */
    public void setController(DungeonController controller) {
    	this.controller = controller;
    }
    
    /**
     * 
     * @param x
     * @param y
     * @return Tile object at specified x and y
     */
    public Tile getTile(int x, int y) {
    	if (x < 0 || x >= getWidth()
    		|| y < 0 || y >= getHeight()) 
    		return null;
    	return board[y][x];
    }
    
    /**
     * 
     * @return all entities in dungeon
     */
    public List<Entity> getAllEntities() {
    	List<Entity> entities = new ArrayList<>();
    	
    	for (int x = 0; x < getWidth(); x++) {
    		for (int y = 0; y < getHeight(); y++) {
    			entities.addAll(getTile(x, y).getEntities());
    		}
    	}
    	
    	return entities;
    }
    
    /**
     * inform dungeon of player
     * @param player
     */
    public void setPlayer(Player player) {
    	if (this.player != null) player.getInventory().removeObserver(this);
        this.player = player;
        player.getInventory().addObserver(this);
    }
    
    /**
     * move specified Movable entity onto specified Tile object
     * @param entity
     * @param tile
     */
    public void moveEntity(Movable entity, Tile tile) {
    	if (entity.getCurrentTile() != null) entity.getCurrentTile().movedOff(entity);
    	tile.movedOn(entity);
    }
    
    /**
     * add specified entity to dungeon
     * @param entity
     */
	public void addEntity(Entity entity) {
    	if (entity instanceof Portal) {
    		if (getPortalCountById((Portal) entity) == 2) return;
    		linkPortals((Portal) entity);
    	}
    	placeEntityTile(entity, board[entity.getY()][entity.getX()]);
    	linkToGoal(entity);
    }

	/**
	 * inform dungeon of specified enemy
	 * @param e
	 */
	public void addEnemy(Enemy e) {
    	getEnemies().add(e);
    }
    
	/**
	 * remove specified enemy from dungeon's known enemies
	 * @param e
	 */
    public void removeEnemy(Enemy e) {
    	getEnemies().remove(e);
	}
    
    /**
     * inform dungeon of specified door
     * @param d
     */
    public void addDoor(Door d) {
    	getDoors().add(d);
    }
    
    /**
     * remove specified enemy from dungeon's known doors
     * @param d
     */
    public void removeDoor(Door d) {
    	getDoors().remove(d);
    }
	
    /**
     * apply specify enemy movement strategy to enemies
     * @param strategy
     */
    public void setEnemyStrategy(MovementStrategy strategy) {
    	for (Enemy enemy : getEnemies()) {
    		enemy.setStrategy(strategy);
    	}
    }
    
    /**
     * 
     * @param portal
     * @return count of portals with matching id as specified portal
     */
    public int getPortalCountById(Portal portal) {
    	int count = 0;
    	for (Entity e : getAllEntities()) {
			if (e instanceof Portal) {
				Portal otherPortal = (Portal) e;
				if (otherPortal.getId() == portal.getId()) {
					count++;
				}
			}
		}
    	return count;
    }
    
    /**
     * link entity to appropriate goal
     * @param entity
     */
    public void linkToGoal(Entity entity) {
    	getGoal().linkEntity(entity);
    }
    
    /**
     * link to portal to matching portal in dungeon
     * @param portal
     */
    public void linkPortals(Portal portal) {
		for (Entity e : getAllEntities()) {
			if (e instanceof Portal) {
				Portal otherPortal = (Portal) e;
				if (otherPortal.getId() == portal.getId()) {
					otherPortal.setLinkedPortal(portal);
					portal.setLinkedPortal(otherPortal);
					break;
				}
			}
		}
    }
    
    /**
     * place specified entity on specified tile
     * @param entity
     * @param tile
     */
    public void placeEntityTile(Entity entity, Tile tile) {
    	tile.placeEntity(entity);
    }
    
    /**
     * 
     * @param tile
     * @return return distance from specified tile to player position
     */
    public double distToPlayer(Tile tile) {
    	return tile.distToTile(getPlayer().getCurrentTile());
    }
    
    /**
     * highlight matching door of specified key
     * @param key
     */
    public void highlightDoor(Key key) {
    	for (Door door : getDoors()) {
    		if (key == null) {
    			door.setKeyHeld(false);
    			continue;
    		}
    		if (door.isRight(key)) {
    			door.setKeyHeld(true);
    		} else {
    			door.setKeyHeld(false);
    		}
    	}
    }
    
    /**
     * process enemy moves
     */
    public void playTurn() {
    	if (!getGoal().satisfied()) {
	    	List<Enemy> enemiesCopy = new ArrayList<>(getEnemies());
	    	for (Enemy enemy : enemiesCopy) {
	    		enemy.move();
	    	}
    	} else {
    		endGame(true);
    	}
	}
	
	@Override
    public void update(Subject s) {
    	if (s instanceof Inventory) {
    		if (getPlayer().isFreezing()) {
				setEnemyStrategy(idleStrategy);
				return;
			}
			if (getPlayer().isInvincible()) {
				setEnemyStrategy(defensiveStrategy);
				return;
			}
			setEnemyStrategy(offensiveStrategy);
		}
    }
    
    /**
     * finish game
     * @param end
     */
    public void endGame(boolean end) {
    	this.gameOver = true;
    	controller.handleKeyPress(null);
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

	public List<Enemy> getEnemies() {
		return enemies;
	}
	
	public List<Door> getDoors() {
		return doors;
	}

	public Player getPlayer() {
        return player;
    }
	
	/**
	 * 
	 * @return game over status
	 */
	public boolean gameOver() {
		return gameOver;
	}

}
