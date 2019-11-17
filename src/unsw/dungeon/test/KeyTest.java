package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class KeyTest {
	
	@Test
	public void keyTest() {

		Dungeon d = new Dungeon(20, 20);
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		Player p = new Player(d, d.getTile(10, 10));
		d.setPlayer(p);
		d.addEntity(p);
		Key t = new Key(1, d.getTile(10, 11));
		d.addEntity(t);

		assertEquals(p.keyHeld(), null);
		p.Move(0, -1);
		assertEquals(p.keyHeld(), t);
		assertEquals(p.getCurrentTile().getEntities().contains(t), false);
	}
	
	@Test
	public void dropKey() {
		Dungeon d = new Dungeon(20, 20);
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		Player p = new Player(d, d.getTile(10, 10));
		d.setPlayer(p);
		d.addEntity(p);
		Key t = new Key(1, d.getTile(10, 11));
		Key t1 = new Key(1, d.getTile(10, 12));
		d.addEntity(t);
		d.addEntity(t1);

		assertEquals(p.keyHeld(), null);
		p.Move(0, -1);
		assertEquals(p.keyHeld(), t);
		assertEquals(p.getCurrentTile().getEntities().contains(t), false);

		p.Move(0, -1);
		assertEquals(p.keyHeld(), t1);
		assertEquals(p.getCurrentTile().getEntities().contains(t1), false);
		assertEquals(p.getCurrentTile().getEntities().contains(t), true);
	}
	
}
