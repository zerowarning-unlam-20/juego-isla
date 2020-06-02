package entities;

import java.util.List;

import items.Item;
import items.Location;
import states.Normal;
import tools.Gender;
import tools.NPCType;

public class NPC extends Entity {
	NPCType type;

	public NPC(int id, Gender gender, String name, String description, Location location, NPCType type) {
		super(id, gender, name, description, location);
		this.state = new Normal(this);
		this.type = type;
	}

	public NPC(int id, Gender gender, String name, String description, Location location, List<Item> inventory,
			NPCType type) {
		super(id, gender, name, description, location, inventory);
		this.state = new Normal(this);
		this.type = type;
	}

	public NPCType getType() {
		return type;
	}

}
