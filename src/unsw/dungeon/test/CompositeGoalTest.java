package unsw.dungeon.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

public class CompositeGoalTest {
	
	@Test
	public void andGoalTest() {
		DummyGoal goalA = new DummyGoal();
		DummyGoal goalB = new DummyGoal();
		CompositeGoal andGoal = new AndGoal();
		andGoal.addGoal(goalA);
		andGoal.addGoal(goalB);
		
		assertEquals(andGoal.satisfied(), false);
		
		goalA.setSatisfied();
		
		assertEquals(andGoal.satisfied(), false);
		
		goalB.setSatisfied();
		
		assertEquals(andGoal.satisfied(), true);
	}
	
	@Test
	public void orGoalTest() {
		DummyGoal goalA = new DummyGoal();
		DummyGoal goalB = new DummyGoal();
		CompositeGoal orGoal = new OrGoal();
		orGoal.addGoal(goalA);
		orGoal.addGoal(goalB);
		
		assertEquals(orGoal.satisfied(), false);
		
		goalA.setSatisfied();
		
		assertEquals(orGoal.satisfied(), true);
		
		goalA.setUnsatisfied();
		goalB.setSatisfied();
		
		assertEquals(orGoal.satisfied(), true);
	}
	
	@Test
	public void complexGoalTest() {
		DummyGoal goalA = new DummyGoal();
		DummyGoal goalB = new DummyGoal();
		DummyGoal goalC = new DummyGoal();
		DummyGoal goalD = new DummyGoal();
		
		CompositeGoal orGoal1 = new OrGoal();
		orGoal1.addGoal(goalA);
		orGoal1.addGoal(goalB);
		CompositeGoal orGoal2 = new OrGoal();
		orGoal2.addGoal(goalC);
		orGoal2.addGoal(goalD);
		
		CompositeGoal rootAndGoal = new AndGoal();
		rootAndGoal.addGoal(orGoal1);
		rootAndGoal.addGoal(orGoal2);
		
		assertEquals(rootAndGoal.satisfied(), false);
		
		goalA.setSatisfied();
		
		assertEquals(rootAndGoal.satisfied(), false);
		
		goalB.setSatisfied();
		
		assertEquals(rootAndGoal.satisfied(), false);
		
		goalC.setSatisfied();
		
		assertEquals(rootAndGoal.satisfied(), true);
	}
}
