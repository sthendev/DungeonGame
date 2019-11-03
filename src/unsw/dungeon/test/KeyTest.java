package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class KeyTest {
	
	@Test
	public void keyTest() {

		Dungeon d = new Dungeon(20, 20);
		Player p = new Player(d, d.getTile(10, 10));
		d.addEntity(p);
		Key t = new Key(1, d.getTile(10, 11));
		d.addEntity(t);

		assertEquals(p.keyHeld(), null);
		p.Move(0, -1);
		assertEquals(p.keyHeld(), t);
		assertEquals(p.getCurrentTile().getEntities().contains(t), false);
	}
	
}
