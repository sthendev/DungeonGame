package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Tile;
import unsw.dungeon.Sword;
import unsw.dungeon.Player;
import unsw.dungeon.Enemy;

public class SwordTest {
	@Test
	public void pickUpSword() {
		Tile tile = new Tile(null, 0, 0);
		
		Sword sword =  new Sword(tile);
		tile.addEntity(sword);
		
		Player player = new Player(null, null);
		assertEquals(player.toolHeld(), null);
		
		tile.movedOn(player);
		assertEquals(player.toolHeld(), sword);
		assertEquals(tile.getEntities().contains(sword), false);
	}
	
	@Test
	public void swordHits() {
		Player player = new Player(null, null);
		Sword sword = new Sword(null);
		
		player.pickItem(sword);
		assertEquals(sword.getHits(), 5);
		
		player.useTool();
		assertEquals(sword.getHits(), 4);
		
		for (int i = 0; i < 4; i++) {
			player.useTool();
		}
		
		assertEquals(sword.getHits(), 0);
		assertEquals(player.toolHeld(), null);
	}
	
	@Test
	public void swapSword() {
		Tile tile = new Tile(null, 0, 0);
		
		Player player = new Player(null, null);
		
		Sword swordA =  new Sword(null);
		player.pickItem(swordA);
		player.useTool();
		
		Sword swordB = new Sword(tile);
		tile.addEntity(swordB);
		
		tile.movedOn(player);
		assertEquals(player.toolHeld(), swordB);
		assertEquals(tile.getEntities().contains(swordA), true);
		assertEquals(swordA.getHits(), 4);
	}
	
	@Test
	public void swordKillEnemy() {
		Dungeon dungeon = new Dungeon(5, 5);
		
		Tile tile = dungeon.getTile(2, 2);
		
		Player player = new Player(null, null);
		Enemy enemy = new Enemy(dungeon, null, null);
		dungeon.addEnemy(enemy);
		tile.addEntity(enemy);
		
		Sword sword = new Sword(null);
		player.pickItem(sword);
		
		enemy.notifyComing(player);
		assertEquals(dungeon.getEnemies().contains(enemy), false);
	}
}
