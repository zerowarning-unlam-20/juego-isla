package items;

import entities.Entity;
import states.Dead;
import states.Lost;
import states.Normal;
import states.State;

public class Effect {
	private Double healthChange;
	private String stateName;
	// Cuando se consume un item el estado del jugador puede cambiar
	// Basicamente la brujula, mejor excusa imposible. No tiene sentido meter esto
	// para matar al jugador, o si? Creo que si jajajaj. Sale consumible veneno.

	public Effect(Double healthChange, String stateChange) {
		this.healthChange = healthChange;
		this.stateName = stateChange;
	}

	public Double getHealthChange() {
		return healthChange;
	}

	public State apply(Entity character) {
		State resultState = character.getState();
		if (stateName != null) {
			if (stateName.contentEquals("dead")) {
				resultState = new Dead(character);
			}
			if (stateName.contentEquals("normal")) {
				resultState = new Normal(character);
			}
			if (stateName.contentEquals("lost")) {
				resultState = new Lost(character);
			}
		}

		if (!character.getState().getClass().equals(Dead.class)) {
			if (character.getHealth() + healthChange > character.getBaseHealth()) {
				character.setHealth(character.getBaseHealth());
			} else if (character.getHealth() + healthChange <= 0) {
				character.setState(new Dead(character));
			} else
				character.setHealth(character.getHealth() + healthChange);

		}
		return resultState;
	}
}
