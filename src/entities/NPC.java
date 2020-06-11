package entities;

import java.util.HashMap;
import java.util.List;

import island.Location;
import items.Item;
import manager.GameManager;
import states.NPCNormal;
import tools.Gender;
import tools.NPCType;

public class NPC extends Entity {
	private NPCType type;
	private int idUnlocks;
	HashMap<String, String> chat;

	public NPC(GameManager gameManager, int id, Gender gender, String name, String description, Location location,
			NPCType type, int idUnlocks) {
		super(gameManager, id, gender, name, description, location);
		this.state = new NPCNormal(this);
		this.type = type;
		this.idUnlocks = idUnlocks;
		this.chat = new HashMap<String, String>();
	}

	public NPC(GameManager gameManager, int id, Gender gender, String name, String description, Location location,
			NPCType type, int idUnlocks, HashMap<String, String> chat) {
		super(gameManager, id, gender, name, description, location);
		this.state = new NPCNormal(this);
		this.idUnlocks = idUnlocks;
		this.type = type;
		this.chat = chat;
	}

	public NPC(GameManager gameManager, int id, Gender gender, String name, String description, Location location,
			List<Item> inventory, NPCType type, int idUnlocks) {
		super(gameManager, id, gender, name, description, location, inventory);
		this.state = new NPCNormal(this);
		this.type = type;
		this.idUnlocks = idUnlocks;
	}

	public NPCType getType() {
		return type;
	}

	public String getChat(String message) {
		return chat.get(message);
	}

	public int getIdUnlocks() {
		return idUnlocks;
	}

	@Override
	public void onDeath(Attack attack) {
		super.onDeath(attack);
		if (idUnlocks > 0) {
			location.getAccesses().get(idUnlocks).unlock();
		}
	}
}
