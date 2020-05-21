package entities;

import items.Location;
import states.Normal;
import tools.Gender;

public class NPC extends Entity {

	public NPC(int id, Gender gender, String name, String description, boolean visible, Location location) {
		super(id, gender, name, description, location);
		this.state = new Normal(this);
	}

	@Override
	public void recieveDamage(Double damage) {
		// TODO Auto-generated method stub
		
	}
}
