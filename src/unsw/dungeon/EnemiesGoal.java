package unsw.dungeon;

public class EnemiesGoal implements Goal, Observer {
	
	private String name;
	private int aliveEnemies;
	
	public EnemiesGoal() {
		this.name = "enemies";
		this.aliveEnemies = 0;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public void update(Subject obj) {
		if (obj instanceof Enemy) {
			Enemy enemy = (Enemy) obj;
			if (!enemy.getStillExists()) {
				this.aliveEnemies--;
				enemy.removeObserver(this);
			}
		}	
	}

	@Override
	public boolean satisfied() {
		return aliveEnemies == 0;
	}
	
	@Override
	public void linkEntity(Entity entity) {
		if (entity instanceof Enemy) {
			entity.addObserver(this);
			this.aliveEnemies++;
		}
	}
	
	@Override
	public String message() {
		String message = "";
		
		message += satisfied() ? "\u2611 " : "\u2610 ";
		message += "Kill all the enemies";
		
		return message;
	}
	
}
