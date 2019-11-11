package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class EnemyTest {
	
	@Test
	public void moveTowardsPlayer() { 
		Dungeon dungeon = new Dungeon(5, 5);
		Goal goal = new EnemiesGoal();
		dungeon.setGoal(goal);
		
		Enemy enemy = new Enemy(dungeon, dungeon.getTile(0, 0), new OffensiveEnemy());
		dungeon.addEnemy(enemy);
		dungeon.addEntity(enemy);
		
		Player player = new Player(dungeon, dungeon.getTile(2, 2));
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		
		dungeon.playTurn();
		assertEquals(enemy.getCurrentTile(), dungeon.getTile(0, 1));
	}
	
	@Test
	public void moveAwayFromPlayer() { 
		Dungeon dungeon = new Dungeon(5, 5);
		Goal goal = new EnemiesGoal();
		dungeon.setGoal(goal);
		
		Enemy enemy = new Enemy(dungeon, dungeon.getTile(2, 1), new OffensiveEnemy());
		dungeon.addEnemy(enemy);
		dungeon.addEntity(enemy);
		
		Player player = new Player(dungeon, dungeon.getTile(2, 2));
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		player.pickItem(new InvincibilityPotion(null));
		
		dungeon.playTurn();
		assertEquals(enemy.getCurrentTile(), dungeon.getTile(2, 0));
	}
	
	@Test
	public void enemyBlocked() {
		Enemy enemy = new Enemy(null, null, null);
		
		assertEquals((new Wall(null)).isBlocking(enemy), true);
		assertEquals((new Exit(null)).isBlocking(enemy), true);
		assertEquals((new Wall(null)).isBlocking(enemy), true);
		assertEquals((new Treasure(null)).isBlocking(enemy), false);
		assertEquals((new Key(0, null)).isBlocking(enemy), false);
		assertEquals((new Sword(null)).isBlocking(enemy), false);
		assertEquals((new Boulder(null, null)).isBlocking(enemy), true);
		assertEquals((new FloorSwitch(null)).isBlocking(enemy), false);
		assertEquals((new Portal(0, null)).isBlocking(enemy), true);
		assertEquals((new InvincibilityPotion(null)).isBlocking(enemy), false);
	}
	
	@Test
	public void enemyInteractions() {
		Tile tile = new Tile(null, 5, 5);
		Treasure treasure = new Treasure(null);
		Key key = new Key(0, null);
		Sword sword = new Sword(null);
		InvincibilityPotion potion = new InvincibilityPotion(null);
		
		tile.addEntity(treasure);
		tile.addEntity(key);
		tile.addEntity(sword);
		tile.addEntity(potion);
		
		Enemy enemy = new Enemy(null, null, null);
		tile.movedOn(enemy);
		
		assertEquals(tile.getEntities().contains(treasure), true);
		assertEquals(tile.getEntities().contains(key), true);
		assertEquals(tile.getEntities().contains(sword), true);
		assertEquals(tile.getEntities().contains(potion), true);
		
	}
}
