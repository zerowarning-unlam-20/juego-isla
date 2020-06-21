package items;

import entities.Entity;
import states.Dead;
import states.Lost;
import states.Normal;

public class ItemEffect {
	private Double healthChange;
	private String stateName;

	public ItemEffect(Double healthChange, String stateChange) {
		this.healthChange = healthChange;
		this.stateName = stateChange;
	}

	public Double getHealthChange() {
		return healthChange;
	}

	public void apply(Entity character) {
		if (stateName != null) {
			if (stateName.contentEquals("dead")) {
				character.setState(new Dead(character));
			}
			if (stateName.contentEquals("normal")) {
				character.setState(new Normal(character));
			}
			if (stateName.contentEquals("lost")) {
				character.setState(new Lost(character));
			}
		}
		if (!character.getState().getClass().equals(Dead.class))
			character.setHealth(character.getHealth() + healthChange);
	}
}
