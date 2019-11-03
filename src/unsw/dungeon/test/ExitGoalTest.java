package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.Player;

public class ExitGoalTest {
	@Test
	public void exitgoalTest() {

		Dungeon d = new Dungeon(20, 20);
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		Player p = new Player(d, d.getTile(10, 10));
		d.setPlayer(p);
		d.addEntity(p);
		Exit e = new Exit(d.getTile(11, 10));
		d.addEntity(e);

		assertEquals(d.gameOver(), false);
		p.Move(1, 0);
		assertEquals(d.getGoal().satisfied(), true);
	}
}
