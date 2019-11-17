package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
public class PlayerTest {
	
	@Test
	public void playerTest() {

		Dungeon d = new Dungeon(20, 20);
		Player p = new Player(d, d.getTile(10, 10));
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		d.addEntity(p);
		Wall w = new Wall(d.getTile(11, 10));
		d.addEntity(w);
		Wall w1 = new Wall(d.getTile(12, 10));
		d.addEntity(w1);
		Door door = new Door(1, d.getTile(11, 11), null);
		d.addEntity(door);
    	Boulder b = new Boulder(d, d.getTile(11, 9));
    	d.addEntity(b);
    	Boulder b1 = new Boulder(d, d.getTile(12, 9));
    	d.addEntity(b1);
    	
    	//can't move through walls
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 10);
	}
	
	@Test
	public void closedDoor() {
		Dungeon d = new Dungeon(20, 20);
		Player p = new Player(d, d.getTile(10, 10));
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		d.addEntity(p);
		Wall w = new Wall(d.getTile(11, 10));
		d.addEntity(w);
		Wall w1 = new Wall(d.getTile(12, 10));
		d.addEntity(w1);
		Door door = new Door(1, d.getTile(11, 11), null);
		d.addEntity(door);
    	Boulder b = new Boulder(d, d.getTile(11, 9));
    	d.addEntity(b);
    	Boulder b1 = new Boulder(d, d.getTile(12, 9));
    	d.addEntity(b1);
    	
    	//can't move through walls
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 10);
		//can't move through close doors
    	p.Move(0, -1);
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 11);
	}
	
	@Test
	public void boulderWall() {
		Dungeon d = new Dungeon(20, 20);
		Player p = new Player(d, d.getTile(10, 10));
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		d.addEntity(p);
		Wall w = new Wall(d.getTile(11, 10));
		d.addEntity(w);
		Wall w1 = new Wall(d.getTile(12, 10));
		d.addEntity(w1);
		Door door = new Door(1, d.getTile(11, 11), null);
		d.addEntity(door);
    	Boulder b = new Boulder(d, d.getTile(11, 9));
    	d.addEntity(b);
    	Boulder b1 = new Boulder(d, d.getTile(12, 9));
    	d.addEntity(b1);
    	
    	//can't move through walls
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 10);
		//can't move through close doors
    	p.Move(0, -1);
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 11);

		//can't move through boulders in front of a wall
    	p.Move(0, 1);
    	p.Move(0, 1);
    	p.Move(0, 1);
    	p.Move(1, 0);
    	p.Move(0, -1);
		assertEquals(p.getX(), 11);
		assertEquals(p.getY(), 8);
	}
	
	@Test
	public void doubleBoulder() {
		Dungeon d = new Dungeon(20, 20);
		Player p = new Player(d, d.getTile(10, 10));
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		d.addEntity(p);
		Wall w = new Wall(d.getTile(11, 10));
		d.addEntity(w);
		Wall w1 = new Wall(d.getTile(12, 10));
		d.addEntity(w1);
		Door door = new Door(1, d.getTile(11, 11), null);
		d.addEntity(door);
    	Boulder b = new Boulder(d, d.getTile(11, 9));
    	d.addEntity(b);
    	Boulder b1 = new Boulder(d, d.getTile(12, 9));
    	d.addEntity(b1);
    	
    	//can't move through walls
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 10);
		//can't move through close doors
    	p.Move(0, -1);
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 11);

		//can't move through boulders in front of a wall
    	p.Move(0, 1);
    	p.Move(0, 1);
    	p.Move(0, 1);
    	p.Move(1, 0);
    	p.Move(0, -1);
		assertEquals(p.getX(), 11);
		assertEquals(p.getY(), 8);

		//can't move through boulders in front of a boulder
    	p.Move(-1, 0);
    	p.Move(0, -1);
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 9);
	}
	
}
