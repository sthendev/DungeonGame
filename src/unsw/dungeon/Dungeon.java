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
    private List<Enemy> enemies;
    private Goal goal;
    private String state;
    private MovementStrategy offensiveStrategy;
    private MovementStrategy defensiveStrategy;


    public Dungeon(int width, int height, Goal goal) {
        this.width = width;
		this.height = height;
        this.enemies = new ArrayList<Enemy>();
        this.player = null;
        this.enemies = new ArrayList<>();
        this.offensiveStrategy = new OffensiveEnemy();
        this.defensiveStrategy = new DefensiveEnemy();
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
    
    public List<Entity> getAllEntities() {
    	List<Entity> entities = new ArrayList<>();
    	
    	for (int x = 0; x < width; x++) {
    		for (int y = 0; y < height; y++) {
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
    public int playerXPos() {
		return player.getX();
	}

    public int playerYPos() {
    	return player.getY();
	}
    
    public void moveEntity(Movable entity, Tile tile) {
    	if (entity.getCurrentTile() != null) entity.getCurrentTile().movedOff(entity);
    	tile.movedOn(entity);
    }

	public void addEntity(Entity entity) {
    	if (entity instanceof Portal) {
    		linkPortals((Portal) entity);
    	}
    	placeEntityTile(entity, board[entity.getY()][entity.getX()]);
    	linkToGoal(entity);
    }

    public void removeEntity(Entity e, int x, int y) {
		getTile(x, y).removeEntity(e);
	}

	public void addEnemy(Enemy e) {
    	getEnemies().add(e);
    }
    
    public void removeEnemy(Enemy e) {
    	getEnemies().remove(e);
	}
	
    public void setEnemyStrategy(MovementStrategy strategy) {
    	for (Enemy enemy : enemies) {
    		enemy.setStrategy(strategy);
    	}
    }
    
    public void linkToGoal(Entity entity) {
    	goal.linkEntity(entity);
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
    	entity.setCurrentTile(tile);
    }
    
    public double distToPlayer(Tile tile) {
    	return tile.distToTile(player.getCurrentTile());
    }
    
    public void playTurn() {
    	if (!goal.satisfied()) {
	    	List<Enemy> enemiesCopy = new ArrayList<>(enemies);
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
			System.out.println("no");
			if (player.isInvincible()) {
				setEnemyStrategy(defensiveStrategy);
				System.out.println("yes");
			} else {
				setEnemyStrategy(offensiveStrategy);
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
		System.out.println(state);
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

	public Player getPlayer() {
        return player;
    }

}
