package unsw.dungeon;

public interface Goal {
	/**
	 * 
	 * @return whether or not goal is satisfied
	 */
	public boolean satisfied();
	
	/**
	 * 
	 * @param link entity is subject to this goal
	 */
	public void linkEntity(Entity entity);
	
	/**
	 * 
	 * @return string message associated with this goal
	 */
	public String message();
}
