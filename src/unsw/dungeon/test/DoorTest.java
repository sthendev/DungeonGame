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
	public void initialDoorTest() {
		Door door = new Door(0, null);
		
		assertEquals(door.isOpened(), false);
		assertEquals(door.isTransparent(), false);
	}
	
	@Test
	public void rightKeyTest() {
		Door door = new Door(0, null);
		
		Key rightKey = new Key(0, null);
		assertEquals(door.isRight(rightKey), true);
		Key wrongKey = new Key(1, null);
		assertEquals(door.isRight(wrongKey), false);
	}
	
	@Test
	public void closedDoorTest() {
		Door door  = new Door(0, null);
		
		Key wrongKey = new Key(1, null);
		
		Player playerNoKey = new Player(null, null);
		Player playerWrongKey = new Player(null, null);
		playerWrongKey.pickKey(wrongKey);
		Enemy enemy =  new Enemy(null, null, null);
		Boulder boulder = new Boulder(null, null);
	
		assertEquals(door.isBlocking(playerNoKey), true);
		assertEquals(door.isBlocking(playerWrongKey), true);
		assertEquals(door.isBlocking(enemy), true);
		assertEquals(door.isBlocking(boulder), true);
	}
	
	@Test
	public void playerComingTest() {
		Door door = new Door(0, null);
		
		Player playerWrongKey = new Player(null, null);
		Key wrongKey = new Key(1, null);
		playerWrongKey.pickKey(wrongKey);
		
		Player playerRightKey = new Player(null, null);
		Key rightKey = new Key(0, null);
		playerRightKey.pickKey(rightKey);
		
		door.notifyComing(playerWrongKey);
		assertEquals(door.isOpened(), false);
		assertEquals(playerWrongKey.keyHeld(), wrongKey);
		
		
		door.notifyComing(playerRightKey);
		assertEquals(door.isOpened(), true);
		assertEquals(playerRightKey.keyHeld(), null);
	}
	
	@Test
	public void openDoorTest() {
		Door door = new Door(0, null);
		
		Key rightKey = new Key(0, null);
		Player playerRightKey = new Player(null, null);
		playerRightKey.pickKey(rightKey);
		
		assertEquals(door.isBlocking(playerRightKey), false);
		assertEquals(door.isOpened(), true);
		assertEquals(door.isTransparent(), true);
		
		Key wrongKey = new Key(1, null);
		
		Player playerNoKey = new Player(null, null);
		Player playerWrongKey = new Player(null, null);
		playerWrongKey.pickKey(wrongKey);
		Enemy enemy =  new Enemy(null, null, null);
		Boulder boulder = new Boulder(null, null);

		assertEquals(door.isBlocking(playerNoKey), false);
		assertEquals(door.isBlocking(playerWrongKey), false);
		assertEquals(door.isBlocking(enemy), false);
		assertEquals(door.isBlocking(boulder), false);
	}
}
