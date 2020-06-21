package entities;

import java.util.HashMap;

import island.Location;
import items.Item;
import manager.GameManager;
import states.NPCNormal;
import tools.Gender;
import tools.MessageType;
import tools.NPCType;

public class NPC extends Entity {

	private NPCType npcType;
	private String idUnlocks;
	HashMap<String, String> chat;

	public NPC(GameManager gameManager, Gender gender, String name, String description, Location location,
			HashMap<String, Item> inventory, String initialLocation, NPCType type, String idUnlocks,
			HashMap<String, String> chat) {
		super(gameManager, gender, name, description, location, inventory, initialLocation);
		this.state = new NPCNormal(this);
		this.idUnlocks = idUnlocks;
		this.npcType = type;
		this.chat = chat;
	}

	public NPCType getType() {
		return npcType;
	}

	public String getChat(String message) {
		return chat.get(message);
	}

	public String getIdUnlocks() {
		return idUnlocks;
	}

	@Override
	public void onDeath(Attack attack) {
		super.onDeath(attack);
		if (idUnlocks != null) {
			location.getAccesses().get(idUnlocks).unlock();
			location.getAccesses().get(idUnlocks).open();
			gameManager.sendMessage(MessageType.EVENT, this, "Se libera el camino a "
					+ location.getAccesses().get(idUnlocks).getDestination().getSingularName());
		}

	}
}
