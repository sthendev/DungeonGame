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
    
    private void initializeBoard(int width, int height) {
    	this.board = new Tile[height][width];
    	for (int row = 0; row < height; row ++) {
    		for (int col = 0; col < width; col++) {
    			board[row][col] = new Tile(this, col, row);
    		}
    	}
    }
    
    public Tile getTile(int x, int y) {
    	if (x < 0 || x >= getWidth()
    		|| y < 0 || y >= getHeight()) 
    		return null;
    	return board[y][x];
    }
    
    public List<Entity> getAllEntities() {
    	List<Entity> entities = new ArrayList<>();
    	
    	for (int x = 0; x < getWidth(); x++) {
    		for (int y = 0; y < getHeight(); y++) {
    			entities.addAll(getTile(x, y).getEntities());
    		}
    	}
    	
    	return entities;
    }

    public void setPlayer(Player player) {
    	if (this.player != null) player.getInventory().removeObserver(this);
        this.player = player;
        player.getInventory().addObserver(this);
    }
    
    public void moveEntity(Movable entity, Tile tile) {
    	if (entity.getCurrentTile() != null) entity.getCurrentTile().movedOff(entity);
    	tile.movedOn(entity);
    }

	public void addEntity(Entity entity) {
    	if (entity instanceof Portal) {
    		if (getPortalCountById((Portal) entity) == 2) return;
    		linkPortals((Portal) entity);
    	}
    	placeEntityTile(entity, board[entity.getY()][entity.getX()]);
    	linkToGoal(entity);
    }


	public void addEnemy(Enemy e) {
    	getEnemies().add(e);
    }
    
    public void removeEnemy(Enemy e) {
    	getEnemies().remove(e);
	}
    
    public void addDoor(Door d) {
    	getDoors().add(d);
    }
    
    public void removeDoor(Door d) {
    	getDoors().remove(d);
    }
	
    public void setEnemyStrategy(MovementStrategy strategy) {
    	for (Enemy enemy : getEnemies()) {
    		enemy.setStrategy(strategy);
    	}
    }
    
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
    
    public void linkToGoal(Entity entity) {
    	getGoal().linkEntity(entity);
    }
    
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
    
    public void placeEntityTile(Entity entity, Tile tile) {
    	tile.placeEntity(entity);
    }
    
    public double distToPlayer(Tile tile) {
    	return tile.distToTile(getPlayer().getCurrentTile());
    }
    
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
    
    //To-do
    public void endGame(boolean end) {
    	if (end == true) {
    		System.out.println("complete");
    	} else {
    		System.out.println("fail");
    	}
    	this.gameOver = true;
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
	
	public boolean gameOver() {
		return gameOver;
	}

}
