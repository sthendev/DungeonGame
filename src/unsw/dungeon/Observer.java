package unsw.dungeon;

public interface Observer {
	/**
	 * process change in subject
	 * @param s
	 */
	public void update(Subject s);
}
