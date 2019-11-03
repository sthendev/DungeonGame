package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import unsw.dungeon.*;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;

public class EnemyGoalTest {
	
	@Test
	public void swordKillEnemy() {
		Dungeon dungeon = new Dungeon(5, 5);
		EnemiesGoal g= new EnemiesGoal();
		dungeon.setGoal(g);
		
		Tile tile = dungeon.getTile(2, 2);
		
		Player player = new Player(null, null);
		Enemy enemy = new Enemy(dungeon, null, null);
		dungeon.addEnemy(enemy);
		tile.addEntity(enemy);
		
		Sword sword = new Sword(null);
		player.pickSword(sword);
		
		enemy.notifyComing(player);
		assertEquals(dungeon.getGoal().satisfied(), true);
	}

}
