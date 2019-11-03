package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Wall;
import unsw.dungeon.Player;
import unsw.dungeon.Enemy;
import unsw.dungeon.Boulder;


public class WallTest {
	@Test
	public void testWall() {
		Wall wall = new Wall(null);
		
		Player player = new Player(null, null);
		Enemy enemy =  new Enemy(null, null, null);
		Boulder boulder = new Boulder(null, null);
		
		// isBlocking
		assertEquals(wall.isBlocking(player), true);
		assertEquals(wall.isBlocking(enemy), true);
		assertEquals(wall.isBlocking(boulder), true);
		
		// is Transparent
		assertEquals(wall.isTransparent(), false);
		
	}
}
