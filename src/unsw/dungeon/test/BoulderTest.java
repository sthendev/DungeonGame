package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;
public class BoulderTest {
	
	
	
	
	@Test
	public void boulderTest() {

		Dungeon d = new Dungeon(20, 20);
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		Player p = new Player(d, d.getTile(10, 11));
        d.setPlayer(p);
		d.addEntity(p);
		Wall w = new Wall(d.getTile(11, 10));
		d.addEntity(w);
		Wall w1 = new Wall(d.getTile(12, 10));
		d.addEntity(w1);
		Door door = new Door(1, d.getTile(11, 11), d);
		d.addEntity(door);
    	Boulder b = new Boulder(d, d.getTile(11, 9));
    	d.addEntity(b);
    	Boulder b1 = new Boulder(d, d.getTile(12, 9));
    	d.addEntity(b1);
    	Boulder b3 = new Boulder(d, d.getTile(12, 8));
    	d.addEntity(b3);
    	Boulder b4 = new Boulder(d, d.getTile(12, 11));
    	d.addEntity(b4);


    	Portal portal = new Portal(1, d.getTile(13, 8));
    	d.addEntity(portal);
    	Portal r = new Portal(1, d.getTile(15, 8));
    	d.addEntity(r);
    	
		
		assertEquals(b.getX(), 11);
		assertEquals(b.getY(), 9);
		assertEquals(b1.getX(), 12);
		assertEquals(b1.getY(), 9);
		assertEquals(b3.getX(), 12);
		assertEquals(b3.getY(), 8);
		assertEquals(b4.getX(), 12);
		assertEquals(b4.getY(), 11);
	}
	
	@Test
	public void boulderWallTest() {
		Dungeon d = new Dungeon(20, 20);
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		Player p = new Player(d, d.getTile(10, 11));
        d.setPlayer(p);
		d.addEntity(p);
		Wall w = new Wall(d.getTile(11, 10));
		d.addEntity(w);
		Wall w1 = new Wall(d.getTile(12, 10));
		d.addEntity(w1);
		Door door = new Door(1, d.getTile(11, 11), d);
		d.addEntity(door);
    	Boulder b = new Boulder(d, d.getTile(11, 9));
    	d.addEntity(b);
    	Boulder b1 = new Boulder(d, d.getTile(12, 9));
    	d.addEntity(b1);
    	Boulder b3 = new Boulder(d, d.getTile(12, 8));
    	d.addEntity(b3);
    	Boulder b4 = new Boulder(d, d.getTile(12, 11));
    	d.addEntity(b4);


    	Portal portal = new Portal(1, d.getTile(13, 8));
    	d.addEntity(portal);
    	Portal r = new Portal(1, d.getTile(15, 8));
    	d.addEntity(r);
    	
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
	public void boulderDoubleTest() {
		//can't move through boulders in front of a boulder
		Dungeon d = new Dungeon(20, 20);
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		Player p = new Player(d, d.getTile(10, 11));
        d.setPlayer(p);
		d.addEntity(p);
		Wall w = new Wall(d.getTile(11, 10));
		d.addEntity(w);
		Wall w1 = new Wall(d.getTile(12, 10));
		d.addEntity(w1);
		Door door = new Door(1, d.getTile(11, 11), d);
		d.addEntity(door);
    	Boulder b = new Boulder(d, d.getTile(11, 9));
    	d.addEntity(b);
    	Boulder b1 = new Boulder(d, d.getTile(12, 9));
    	d.addEntity(b1);
    	Boulder b3 = new Boulder(d, d.getTile(12, 8));
    	d.addEntity(b3);
    	Boulder b4 = new Boulder(d, d.getTile(12, 11));
    	d.addEntity(b4);


    	Portal portal = new Portal(1, d.getTile(13, 8));
    	d.addEntity(portal);
    	Portal r = new Portal(1, d.getTile(15, 8));
    	d.addEntity(r);
    	
		//can't move through boulders in front of a wall
    	p.Move(0, 1);
    	p.Move(0, 1);
    	p.Move(0, 1);
    	p.Move(1, 0);
    	p.Move(0, -1);
		assertEquals(p.getX(), 11);
		assertEquals(p.getY(), 8);
		
    	p.Move(-1, 0);
    	p.Move(0, -1);
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 9);
	}
	
	@Test
	public void boulderPortalTest() {

		//can't move through boulders in front of a portal
		Dungeon d = new Dungeon(20, 20);
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		Player p = new Player(d, d.getTile(10, 11));
        d.setPlayer(p);
		d.addEntity(p);
		Wall w = new Wall(d.getTile(11, 10));
		d.addEntity(w);
		Wall w1 = new Wall(d.getTile(12, 10));
		d.addEntity(w1);
		Door door = new Door(1, d.getTile(11, 11), d);
		d.addEntity(door);
    	Boulder b = new Boulder(d, d.getTile(11, 9));
    	d.addEntity(b);
    	Boulder b1 = new Boulder(d, d.getTile(12, 9));
    	d.addEntity(b1);
    	Boulder b3 = new Boulder(d, d.getTile(12, 8));
    	d.addEntity(b3);
    	Boulder b4 = new Boulder(d, d.getTile(12, 11));
    	d.addEntity(b4);


    	Portal portal = new Portal(1, d.getTile(13, 8));
    	d.addEntity(portal);
    	Portal r = new Portal(1, d.getTile(15, 8));
    	d.addEntity(r);
    	
		//can't move through boulders in front of a wall
    	p.Move(0, 1);
    	p.Move(0, 1);
    	p.Move(0, 1);
    	p.Move(1, 0);
    	p.Move(0, -1);
		assertEquals(p.getX(), 11);
		assertEquals(p.getY(), 8);
		
    	p.Move(-1, 0);
    	p.Move(0, -1);
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 9);
    	p.Move(0, 1);
    	p.Move(1, 0);
    	p.Move(1, 0);
		assertEquals(p.getX(), 11);
		assertEquals(p.getY(), 8);
	}
	
	@Test
	public void boulderDoorTest() {
		Dungeon d = new Dungeon(20, 20);
		ExitGoal g = new ExitGoal();
		d.setGoal(g);
		Player p = new Player(d, d.getTile(10, 11));
        d.setPlayer(p);
		d.addEntity(p);
		Wall w = new Wall(d.getTile(11, 10));
		d.addEntity(w);
		Wall w1 = new Wall(d.getTile(12, 10));
		d.addEntity(w1);
		Door door = new Door(1, d.getTile(11, 11), d);
		d.addEntity(door);
    	Boulder b = new Boulder(d, d.getTile(11, 9));
    	d.addEntity(b);
    	Boulder b1 = new Boulder(d, d.getTile(12, 9));
    	d.addEntity(b1);
    	Boulder b3 = new Boulder(d, d.getTile(12, 8));
    	d.addEntity(b3);
    	Boulder b4 = new Boulder(d, d.getTile(12, 11));
    	d.addEntity(b4);


    	Portal portal = new Portal(1, d.getTile(13, 8));
    	d.addEntity(portal);
    	Portal r = new Portal(1, d.getTile(15, 8));
    	d.addEntity(r);
    	
		//can't move through boulders in front of a wall
    	p.Move(0, 1);
    	p.Move(0, 1);
    	p.Move(0, 1);
    	p.Move(1, 0);
    	p.Move(0, -1);
		assertEquals(p.getX(), 11);
		assertEquals(p.getY(), 8);
		
    	p.Move(-1, 0);
    	p.Move(0, -1);
    	p.Move(1, 0);
		assertEquals(p.getX(), 10);
		assertEquals(p.getY(), 9);
    	p.Move(0, 1);
    	p.Move(1, 0);
    	p.Move(1, 0);
		assertEquals(p.getX(), 11);
		assertEquals(p.getY(), 8);

		//can't move through boulders in front of a closed door
    	p.Move(-1, 0);
    	p.Move(0, -1);
    	p.Move(0, -1);
    	p.Move(0, -1);
    	p.Move(0, -1);
    	p.Move(1, 0);
    	p.Move(1, 0);
    	p.Move(1, 0);
    	p.Move(0, 1);
    	p.Move(-1, 0);
		assertEquals(p.getX(), 13);
		assertEquals(p.getY(), 11);
	}
	
}
