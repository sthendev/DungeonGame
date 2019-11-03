package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class ExitTest {
	
	@Test
	public void exitTest() {

		Dungeon d = new Dungeon(20, 20);
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		Player p = new Player(d, d.getTile(10, 10));
		d.addEntity(p);
		Exit e = new Exit(d.getTile(11, 0));
		d.addEntity(e);

		assertEquals(e.playerIsHere(), false);
		p.Move(1, 0);
		assertEquals(e.playerIsHere(), true);
	}
	
}
