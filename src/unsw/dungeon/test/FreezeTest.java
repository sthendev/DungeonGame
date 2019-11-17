package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;
import unsw.dungeon.*;

import org.junit.jupiter.api.Test;

class FreezeTest {

	public void pickUpPotion() {
		Dungeon dungeon = new Dungeon(5, 5);
		Goal goal = new EnemiesGoal();
		dungeon.setGoal(goal);
		
		Enemy enemy = new Enemy(dungeon, dungeon.getTile(0, 0), new OffensiveEnemy());
		dungeon.addEnemy(enemy);
		dungeon.addEntity(enemy);
		

		Enemy enemy1 = new Enemy(dungeon, dungeon.getTile(0, 1), new OffensiveEnemy());
		dungeon.addEnemy(enemy1);
		dungeon.addEntity(enemy1);
		
		Player player = new Player(dungeon, dungeon.getTile(2, 2));
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		
		
		FreezePotion potion =  new FreezePotion(dungeon.getTile(3, 2));
		dungeon.getTile(3, 2).addEntity(potion);
		
		assertEquals(player.isFreezing(), false);
		
		
		dungeon.getTile(3, 2).movedOn(player);
		assertEquals(player.isFreezing(), true);
		assertEquals(dungeon.getTile(3, 2).getEntities().contains(potion), false);
		assertEquals(enemy.getCurrentTile(), dungeon.getTile(0, 0));
		assertEquals(enemy1.getCurrentTile(), dungeon.getTile(0, 1));
		
	}
	
	@Test
	public void usePotion() {

		Dungeon dungeon = new Dungeon(5, 5);
		Goal goal = new EnemiesGoal();
		dungeon.setGoal(goal);
		
		Enemy enemy = new Enemy(dungeon, dungeon.getTile(0, 0), new OffensiveEnemy());
		dungeon.addEnemy(enemy);
		dungeon.addEntity(enemy);
		

		Enemy enemy1 = new Enemy(dungeon, dungeon.getTile(0, 1), new OffensiveEnemy());
		dungeon.addEnemy(enemy1);
		dungeon.addEntity(enemy1);
		
		Player player = new Player(dungeon, dungeon.getTile(2, 2));
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		
		
		FreezePotion potion =  new FreezePotion(dungeon.getTile(3, 2));
		dungeon.getTile(3, 2).addEntity(potion);
		
		assertEquals(player.isFreezing(), false);

		dungeon.getTile(3, 2).movedOn(player);
		assertEquals(player.isFreezing(), true);
		assertEquals(player.getInventory().getFreezePotion().getMoves(), 5);
		
		
		player.newTurn();
		assertEquals(player.getInventory().getFreezePotion().getMoves(), 4);
		
		for (int i = 0; i < 4; i++) {
			player.newTurn();
		}
		
		assertEquals(player.getInventory().getFreezePotion(), null);
		assertEquals(player.isFreezing(), false);
		assertEquals(enemy.getStrategy() instanceof OffensiveEnemy, true);

		assertEquals(enemy1.getStrategy() instanceof OffensiveEnemy, true);
	}
	
	@Test
	public void pickUpAnother() {
		Player player = new Player(null, null);
		FreezePotion potion =  new FreezePotion(null);
		FreezePotion potion1 =  new FreezePotion(null);
		
		player.pickItem(potion);
		
		for (int i = 0; i < 4; i++) {
			player.newTurn();
		}
		
		assertEquals(player.getInventory().getFreezePotion().getMoves(), 1);
		player.pickItem(potion1);
		
		assertEquals(player.getInventory().getFreezePotion().getMoves(), 6);
	}

}
