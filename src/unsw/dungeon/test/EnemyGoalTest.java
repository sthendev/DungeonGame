package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import unsw.dungeon.*;
import org.junit.jupiter.api.Test;

public class EnemyGoalTest {
	
	@Test
	public void swordKillEnemy() {
		Dungeon dungeon = new Dungeon(10, 10);
		EnemiesGoal g= new EnemiesGoal();
		dungeon.setGoal(g);
		
		Tile tile = dungeon.getTile(2, 2);
		
		Player player = new Player(dungeon, dungeon.getTile(5, 5));
		Enemy enemy = new Enemy(dungeon, dungeon.getTile(6, 5), null);
		dungeon.addEnemy(enemy);
		dungeon.addEntity(enemy);
		tile.addEntity(enemy);

		assertEquals(dungeon.getGoal().satisfied(), false);
		Sword sword = new Sword(null);
		player.pickSword(sword);
		player.Move(1, 0);
		assertEquals(dungeon.getGoal().satisfied(), true);
	}

}
