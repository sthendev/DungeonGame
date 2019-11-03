package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Wall;
import unsw.dungeon.Player;
import unsw.dungeon.Enemy;
import unsw.dungeon.Boulder;
import unsw.dungeon.OffensiveEnemy;


public class WallTest {
	@Test
	public void wallTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		
		Wall wall = new Wall(dungeon.getTile(2, 2));
		
		Player player = new Player(dungeon, dungeon.getTile(1, 2));
		Enemy enemy =  new Enemy(dungeon, dungeon.getTile(1, 2), new OffensiveEnemy());
		Boulder boulder = new Boulder(dungeon, dungeon.getTile(1, 2));
		
		// isBlocking
		assertEquals(wall.isBlocking(player), true);
		assertEquals(wall.isBlocking(enemy), true);
		assertEquals(wall.isBlocking(boulder), true);
		
		// is Transparent
		assertEquals(wall.isTransparent(), false);
		
	}
}
