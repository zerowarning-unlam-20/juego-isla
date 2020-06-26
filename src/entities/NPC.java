package entities;

import java.util.HashMap;

import island.Location;
import items.Item;
import manager.GameManager;
import states.NPCNormal;
import tools.Gender;
import tools.NPCType;

public class NPC extends Entity {

	private NPCType npcType;
	private String weaponName;
	HashMap<String, String> chat;
	protected EntityListener entityListener;

	public NPC(GameManager gameManager, Gender gender, String name, String description, Location location,
			HashMap<String, Item> inventory, String initialLocation, NPCType type, HashMap<String, String> chat,
			String weaponName) {
		super(gameManager, gender, name, description, location, inventory, initialLocation);
		this.state = new NPCNormal(this);
		this.npcType = type;
		this.chat = chat;
		this.weaponName = weaponName;
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
		gameManager.getGame().pullTrigger(this.getClass().getName() + "_" + this.name + "_" + "dead");
	}

	public String getWeaponName() {
		return weaponName;
	}

}
