package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Goal;
import unsw.dungeon.AndGoal;
import unsw.dungeon.Entity;
import unsw.dungeon.Tile;
import unsw.dungeon.Portal;
import unsw.dungeon.Player;

public class PortalTest {
	
	@Test
	public void playerMoveOnPortal() {
		Tile tileA = new Tile(null, 0, 0);
		Tile tileB = new Tile(null, 1,1);
		
		Portal portalA = new Portal(0, tileA);
		Portal portalB = new Portal(0, tileB);
		portalA.setLinkedPortal(portalB);
		portalB.setLinkedPortal(portalA);
		
		Player player = new Player(null, null);
		
		portalA.notifyComing(player);
		assertEquals(player.getCurrentTile(), tileB);
	}
	
	@Test
	public void playerAlreadyOnPortal() {
		Tile tileA = new Tile(null, 0, 0);
		Tile tileB = new Tile(null, 1,1);
		
		Portal portalA = new Portal(0, tileA);
		Portal portalB = new Portal(0, tileB);
		portalA.setLinkedPortal(portalB);
		portalB.setLinkedPortal(portalA);
		
		Player player = new Player(null, null);
		player.setCurrentTile(tileB);
		
		assertEquals(player.getCurrentTile(), tileB);
	}
	
	@Test
	public void onlyTwoPortalsWithMatchingId() {
		Dungeon dungeon = new Dungeon(5, 5);
		Goal goal = new AndGoal();
		dungeon.setGoal(goal);
		
		Tile tileA = dungeon.getTile(0, 0);
		Tile tileB = dungeon.getTile(1, 1);
		Tile tileC = dungeon.getTile(2, 2);
		Tile tileD = dungeon.getTile(3, 3);
		Tile tileE = dungeon.getTile(4, 4);
		
		Portal portalA = new Portal(0, tileA);
		Portal portalB = new Portal(0, tileB);
		Portal portalC = new Portal(0, tileC);
		Portal portalD = new Portal(1, tileD);
		Portal portalE = new Portal(1, tileE);
		
		dungeon.addEntity(portalA);
		dungeon.addEntity(portalB);
		dungeon.addEntity(portalC);
		dungeon.addEntity(portalD);
		dungeon.addEntity(portalE);
		
		List<Entity> allEntities = dungeon.getAllEntities();
		
		assertEquals(allEntities.size(), 4);
		assertEquals(allEntities.contains(portalC), false);
		
	}
}
