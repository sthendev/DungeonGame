package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
public class SwitchTest {
	
	@Test
	public void switchTest() {

		Dungeon d = new Dungeon(20, 20);
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		Player p = new Player(d, d.getTile(10, 11));
        d.setPlayer(p);
		d.addEntity(p);
		FloorSwitch s = new FloorSwitch(d.getTile(11, 11));
    	d.addEntity(s);
    	Boulder b = new Boulder(d, d.getTile(12, 11));
    	d.addEntity(b);
		FloorSwitch s1 = new FloorSwitch(d.getTile(13, 11));
    	d.addEntity(s1);

    	//Player can move on to a switch
    	p.Move(1, 0);
		assertEquals(p.getX(), 11);
		assertEquals(p.getY(), 11);
		assertEquals(s.isTriggered(), false);
		
    	p.Move(1, 0);
		assertEquals(p.getX(), 12);
		assertEquals(p.getY(), 11);
		assertEquals(s1.isTriggered(), true);
		
    	p.Move(1, 0);
		assertEquals(p.getX(), 13);
		assertEquals(p.getY(), 11);
		assertEquals(s1.isTriggered(), false);
	}
	
}
