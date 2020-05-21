package items;

import island.GameObject;
import tools.Gender;
import tools.ItemType;

public class Tool extends Item {

	private ItemType damages;

	public Tool(int id, Gender gender, String name, String description, ItemType damages) {
		super(id, gender, name, description, ItemType.UNBREAKABLE);
		this.damages = damages;
	}

	@Override
	public void use(Item objective) {
		objective.recieveObject(this);
	}

	@Override
	public boolean recieveObject(GameObject object) {
		return false;
	}

	@Override
	public void recieveDamage(Double damage) {
		// TODO Auto-generated method stub

	}

	public ItemType getDamages() {
		return damages;
	}

}
