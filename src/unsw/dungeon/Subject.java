package unsw.dungeon;

public interface Subject {
	/**
	 * add specifed observer to subject
	 * @param o
	 */
	public void addObserver(Observer o);
	/**
	 * remove specified observe from subject
	 * @param o
	 */
	public void removeObserver(Observer o);
	/**
	 * notify observers of change in subject
	 */
	public void notifyObservers();
}
