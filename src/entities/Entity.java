package entities;

import java.util.LinkedList;

import states.State;

public class Entity extends GameObject {
	private LinkedList<State> states;

	
	
	public Entity(int id, String name, String description, boolean visible) {
		super(id, name, description, visible);
	}


	@Override
	public void recieveObject(GameObject object) {
		// TODO Auto-generated method stub
		
	}
}
