package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Player;
import unsw.dungeon.Enemy;
import unsw.dungeon.Boulder;
import unsw.dungeon.Key;

public class DoorTest {
	
	@Test
	public void closedDoorTest() {
		Door door  = new Door(0, null, null);
		
		Key wrongKey = new Key(1, null);
		
		Player playerNoKey = new Player(null, null);
		Player playerWrongKey = new Player(null, null);
		playerWrongKey.pickItem(wrongKey);
		Enemy enemy =  new Enemy(null, null, null);
		Boulder boulder = new Boulder(null, null);
	
		assertEquals(door.isBlocking(playerNoKey), true);
		assertEquals(door.isBlocking(playerWrongKey), true);
		assertEquals(door.isBlocking(enemy), true);
		assertEquals(door.isBlocking(boulder), true);
	}
	
	@Test
	public void openDoorTest() {
		Door door = new Door(0, null, null);
		
		Key rightKey = new Key(0, null);
		Player playerRightKey = new Player(null, null);
		playerRightKey.pickItem(rightKey);
		
		door.notifyComing(playerRightKey);
		assertEquals(door.isBlocking(playerRightKey), false);
		assertEquals(door.isOpened(), true);
		assertEquals(door.isTransparent(), true);
		
		Key wrongKey = new Key(1, null);
		
		Player playerNoKey = new Player(null, null);
		Player playerWrongKey = new Player(null, null);
		playerWrongKey.pickItem(wrongKey);
		Enemy enemy =  new Enemy(null, null, null);
		Boulder boulder = new Boulder(null, null);

		assertEquals(door.isBlocking(playerNoKey), false);
		assertEquals(door.isBlocking(playerWrongKey), false);
		assertEquals(door.isBlocking(enemy), false);
		assertEquals(door.isBlocking(boulder), false);
	}
}
