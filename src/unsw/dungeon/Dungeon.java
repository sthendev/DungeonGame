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
    private OffensiveEnemy offensiveStrategy;
    private DefensiveEnemy defensiveStrategy;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.player = null;
        this.enemies = new ArrayList<>();
        this.offensiveStrategy = new OffensiveEnemy();
        this.defensiveStrategy = new DefensiveEnemy();
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
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
    	if (this.player != null) player.removeObserver(this);
        this.player = player;
        player.addObserver(this);
    }
    
    public void addEnemy(Enemy enemy) {
    	enemies.add(enemy);
    }
    
    public void killEnemy(Enemy enemy) {
    	enemies.remove(enemy);
    }
    
    public void setEnemyStrategy(MovementStrategy strategy) {
    	for (Enemy enemy : enemies) {
    		enemy.setStrategy(strategy);
    	}
    }

    public void addEntity(Entity entity) {
    	linkEntityTile(entity, board[entity.getY()][entity.getX()]);
    }
    
    public void moveEntity(Movable entity, Tile tile) {
    	entity.getPosition().movedOff(entity);
    	tile.movedOn(entity);
    }
    
    public void linkEntityTile(Entity entity, Tile tile) {
    	tile.placeEntity(entity);
    	entity.setPosition(tile);
    }
    
    public Tile getTile(int x, int y) {
    	if (x < 0 || x >= width
    		|| y < 0 || y >= height) 
    		return null;
    	return board[y][x];
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
    
    public void endGame() {
    	System.out.println("Game over");
    }

	@Override
	public void update(Subject obj) {
		if (obj instanceof Player) {
			Player player = (Player) obj;
			if (player.isInvincible()) {
				setEnemyStrategy(defensiveStrategy);
			} else {
				setEnemyStrategy(offensiveStrategy);
			}
		}
		
	}

}
