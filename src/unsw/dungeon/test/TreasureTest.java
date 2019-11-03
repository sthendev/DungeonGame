package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class TreasureTest {
	
	@Test
	public void treasureTest() {

		Dungeon d = new Dungeon(20, 20);
		Player p = new Player(d, d.getTile(10, 10));
		d.addEntity(p);
		Treasure t = new Treasure(d.getTile(10, 11));
		d.addEntity(t);

		assertEquals(p.getInventory().getTreasure().size(), 0);
		p.Move(0, -1);
		assertEquals(p.getInventory().getTreasure().size(), 1);
		assertEquals(p.getCurrentTile().getEntities().contains(t), false);
	}
	
}