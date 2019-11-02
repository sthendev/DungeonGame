package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, dungeon.getTile(x, y));
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(dungeon.getTile(x, y));
            onLoad(wall);
            entity = wall;
            break;
        case "enemy":
        	Enemy enemy = new Enemy(dungeon, dungeon.getTile(x, y), new OffensiveEnemy());
        	dungeon.addEnemy(enemy);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "exit":
        	Exit exit = new Exit(dungeon.getTile(x, y));
        	onLoad(exit);
        	entity = exit;
        	break;
        case "sword":
        	Sword sword = new Sword(dungeon.getTile(x, y));
        	onLoad(sword);
        	entity = sword;
        	break;
        case "invincibility":
        	InvincibilityPotion potion = new InvincibilityPotion(dungeon.getTile(x, y));
        	onLoad(potion);
        	entity = potion;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon, dungeon.getTile(x, y));
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	FloorSwitch floorSwitch = new FloorSwitch(dungeon.getTile(x, y));
        	onLoad(floorSwitch);
        	entity = floorSwitch;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(dungeon.getTile(x, y));
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "key":
        	id = json.getInt("id");
        	Key key = new Key(id, dungeon.getTile(x, y));
        	onLoad(key);
        	entity = key;
        	break;
        case "door":
        	id = json.getInt("id");
        	Door door = new Door(id, dungeon.getTile(x, y));
        	onLoad(door);
        	entity = door;
        	break;
        case "portal":
        	id = json.getInt("id");
        	Portal portal = new Portal(id, dungeon.getTile(x, y));
        	onLoad(portal);
        	entity = portal;
        	break;
         // TODO Handle other possible entities
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(InvincibilityPotion potion);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(FloorSwitch floorSwitch);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Portal portal);

    // TODO Create additional abstract methods for the other entities

}
