package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Boulder;
import unsw.dungeon.SwitchesGoal;

public class SwitchesGoalTest {
	@Test
	public void switchesGoalTest() {
		Dungeon d = new Dungeon(20, 20);
		SwitchesGoal g = new SwitchesGoal();
		d.setGoal(g);
		FloorSwitch f = new FloorSwitch(d.getTile(10, 10));
		d.addEntity(f);
		
		Boulder b = new Boulder(d, d.getTile(10,11));
		d.addEntity(b);

		assertEquals(d.gameOver(), false);
		d.getTile(10,10).movedOn(b);
		assertEquals(d.getGoal().satisfied(), true);
	}
}
