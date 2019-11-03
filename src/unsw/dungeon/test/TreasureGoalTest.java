package unsw.dungeon.test;
import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TreasureGoalTest {

	@Test
	public void treasureTest() {

		Dungeon d = new Dungeon(20, 20);
		Player p = new Player(d, d.getTile(10, 10));
		TreasureGoal g = new TreasureGoal();
		d.setGoal(g);
        d.setPlayer(p);
		d.addEntity(p);
		Treasure t = new Treasure(d.getTile(10, 11));
		d.addEntity(t);

		assertEquals(d.getGoal().satisfied(), false);
		p.Move(0, -1);
		assertEquals(d.getGoal().satisfied(), true);
	}
}
