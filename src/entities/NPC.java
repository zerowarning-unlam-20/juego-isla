package entities;

import java.util.HashMap;

import island.Location;
import items.Inventory;
import manager.GameManager;
import states.NPCNormal;
import states.State;
import tools.Gender;
import tools.NPCType;
import tools.Namber;

public class NPC extends Entity {

	private NPCType npcType;
	HashMap<String, String> chat;
	protected EntityListener entityListener;

	public NPC(GameManager gameManager, Gender gender, Namber number, String name, String description,
			Location location, Inventory inventory, String initialLocation, NPCType type,
			HashMap<String, String> chat) {
		super(gameManager, gender, number, name, description, location, inventory, initialLocation);
		this.state = new NPCNormal(this);
		this.npcType = type;
		this.chat = chat;
	}

	public NPC(GameManager gameManager, Gender gender, Namber number, String name, String description,
			Location location, Inventory inventory, String initialLocation, State state, NPCType type,
			HashMap<String, String> chat) {
		super(gameManager, gender, number, name, description, location, inventory, initialLocation);
		this.state = state;
		this.npcType = type;
		this.chat = chat;
	}

	public EntityListener getEntityListener() {
		return entityListener;
	}

	public void setEntityListener(EntityListener listener) {
		entityListener = listener;
	}

	public NPCType getType() {
		return npcType;
	}

	public String getChat(String message) {
		return chat.get(message);
	}

	@Override
	public void onDeath(Attack attack) {
		if (entityListener != null) {
			entityListener.onEntityDisappeared(null);
			entityListener = null;
		}
		super.onDeath(attack);
		gameManager.getGame().pullTrigger("_dead", this);
		this.location.removeEntity(this);
		this.location = null;
	}

	public String getBestWeapon() {
		return inventory.getBestWeapon();
	}

}
