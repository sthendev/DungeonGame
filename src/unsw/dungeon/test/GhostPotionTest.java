package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.GhostPotion;
import unsw.dungeon.Player;
import unsw.dungeon.Tile;
import unsw.dungeon.Wall;

public class GhostPotionTest {
	@Test
	public void playerCantWalkThroughWalls() {
		Dungeon dungeon = new Dungeon(5, 5);
		
		Tile tileA = dungeon.getTile(2, 2);
		Tile tileB = dungeon.getTile(3, 2);
		
		Player player = new Player(dungeon, tileA);
		Wall wall = new Wall(tileB);
		
		assertEquals(wall.isBlocking(player), true);
	}
	
	@Test
	public void playerCanWalkThroughWallsAsGhost() {
		Dungeon dungeon = new Dungeon(5, 5);
		
		Tile tileA = dungeon.getTile(2, 2);
		Tile tileB = dungeon.getTile(3, 2);
		
		Player player = new Player(dungeon, tileA);
		
		GhostPotion potion = new GhostPotion(null);
		player.pickItem(potion);
		
		Wall wall = new Wall(tileB);
		
		assertEquals(wall.isBlocking(player), false);
	}
	
	@Test
	public void playerCanOnlyWalkThroughOneWallsAsGhost() {
		Dungeon dungeon = new Dungeon(5, 5);
		
		Tile tileA = dungeon.getTile(2, 2);
		Tile tileB = dungeon.getTile(3, 2);
		Tile tileC = dungeon.getTile(4, 2);
		
		Player player = new Player(dungeon, tileA);
		player.setCurrentTile(tileA);
		
		GhostPotion potion = new GhostPotion(null);
		player.pickItem(potion);
		
		Wall wallB = new Wall(tileB);
		tileB.addEntity(wallB);
		
		Wall wallC = new Wall(tileC);
		tileC.addEntity(wallC);
		
		assertEquals(wallB.isBlocking(player), true);
	}
}
