package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;
import unsw.dungeon.InvincibilityPotion;
import unsw.dungeon.Tile;

public class PotionTest {
	@Test
	public void pickUpPotion() {
		Tile tile = new Tile(null, 0, 0);
		
		InvincibilityPotion potion =  new InvincibilityPotion(tile);
		tile.addEntity(potion);
		
		Player player = new Player(null, null);
		assertEquals(player.isInvincible(), false);
		
		tile.movedOn(player);
		assertEquals(player.isInvincible(), true);
		assertEquals(tile.getEntities().contains(potion), false);
	}
	
	@Test
	public void usePotion() {
		Player player = new Player(null, null);
		InvincibilityPotion potion = new InvincibilityPotion(null);
		
		player.pickPotion(potion);
		assertEquals(player.getInventory().getInvincibleTime(), 5);
		
		player.newTurn();
		assertEquals(player.getInventory().getInvincibleTime(), 4);
		
		for (int i = 0; i < 4; i++) {
			player.newTurn();
		}
		
		assertEquals(player.getInventory().getInvincibleTime(), 0);
		assertEquals(player.isInvincible(), false);
	}
	
	@Test
	public void pickUpAnother() {
		Player player = new Player(null, null);
		InvincibilityPotion potion = new InvincibilityPotion(null);
		InvincibilityPotion potion2 = new InvincibilityPotion(null);
		
		player.pickPotion(potion);
		
		for (int i = 0; i < 4; i++) {
			player.newTurn();
		}
		
		assertEquals(player.getInventory().getInvincibleTime(), 1);
		player.pickPotion(potion2);
		
		assertEquals(player.getInventory().getInvincibleTime(), 6);
	}
	
	@Test
	public void invincibleKillEnemy() {
		Dungeon dungeon = new Dungeon(5, 5);
		
		Tile tile = dungeon.getTile(2, 2);
		
		Player player = new Player(null, null);
		Enemy enemy = new Enemy(dungeon, null, null);
		dungeon.addEnemy(enemy);
		tile.addEntity(enemy);
		
		InvincibilityPotion potion = new InvincibilityPotion(null);
		player.pickPotion(potion);
		
		enemy.notifyComing(player);
		assertEquals(dungeon.getEnemies().contains(enemy), false);
	}
}
