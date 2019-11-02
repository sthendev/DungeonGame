package unsw.dungeon;

public interface Goal {
	public boolean satisfied();
	public void linkEntity(Entity entity);
}
