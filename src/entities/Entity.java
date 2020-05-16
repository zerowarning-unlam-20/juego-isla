package entities;

import java.util.List;

import states.State;

public abstract class Entity extends GameObject {
	protected List<State> states;

	public Entity(int id, String name, String description, boolean visible) {
		super(id, name, description, visible);
	}

	@Override
	public abstract void recieveObject(GameObject object);

	public void addState(State state) {
		if (!getStates().contains(state)) {
			getStates().add(state);
		}
	}

	public void removeState(State state) {
		if (getStates().contains(state)) {
			getStates().remove(state);
		}
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	public abstract String drink(String itemName);
	public abstract String goTo(String destination);
	public abstract String grab(String itemName);
	public abstract String look(String itemName);
	public abstract String open(String itemName);
	public abstract String unlock(String itemName);
	
}
