package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Hammer;
import unsw.dungeon.Tile;
import unsw.dungeon.Wall;

public class HammerTest {
	@Test
	public void pickUpHammer() {
		Tile tile = new Tile(null, 0, 0);
		
		Hammer hammer =  new Hammer(tile);
		tile.addEntity(hammer);
		
		Player player = new Player(null, null);
		assertEquals(player.toolHeld(), null);
		
		tile.movedOn(player);
		assertEquals(player.toolHeld(), hammer);
		assertEquals(tile.getEntities().contains(hammer), false);
	}
	
	@Test
	public void hammerHits() {
		Player player = new Player(null, null);
		Hammer hammer = new Hammer(null);
		
		player.pickItem(hammer);
		assertEquals(hammer.getHits(), 1);
		
		player.useTool();
		assertEquals(hammer.getHits(), 0);
		assertEquals(player.toolHeld(), null);
	}
	
	@Test
	public void swapHammer() {
		Tile tile = new Tile(null, 0, 0);
		
		Player player = new Player(null, null);
		
		Hammer hammerA =  new Hammer(null);
		player.pickItem(hammerA);
		
		Hammer hammerB = new Hammer(tile);
		tile.addEntity(hammerB);
		
		tile.movedOn(player);
		assertEquals(player.toolHeld(), hammerB);
		assertEquals(tile.getEntities().contains(hammerA), true);
	}
	
	@Test
	public void hammerBreakWall() {
		Dungeon dungeon = new Dungeon(5, 5);
		
		Tile tile = dungeon.getTile(2, 2);
		
		Player player = new Player(null, null);
		Wall wall = new Wall(tile);
		tile.addEntity(wall);
		
		Hammer hammer = new Hammer(null);
		player.pickItem(hammer);
		
		tile.movedOn(player);
		assertEquals(tile.hasWall(), false);
	}
}
