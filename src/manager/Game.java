package manager;

import java.util.HashMap;

import entities.NPC;
import entities.Player;
import island.Location;
import items.Access;

public class Game {
	private HashMap<String, NPC> entities;
	private HashMap<String, Location> locations;
	private Player character;

	public Game(GameManager gameManager, Player character, HashMap<String, Location> locations,
			HashMap<String, NPC> npcList) {
		this.locations = locations;
		this.character = character;
		this.character.linkToManager(gameManager);

		this.character.setLocation(locations.get(character.getInitialLocation()));
		this.entities = npcList;

		for (NPC npc : npcList.values()) {
			npc.linkToManager(gameManager);
			npc.setLocation(locations.get(npc.getInitialLocation()));
			locations.get(npc.getInitialLocation()).addEntity(npc);
		}
		linkLocations();
	}

	private void linkLocations() {
		for (Location location : locations.values()) {
			for (Access access : location.getAccesses().values()) {
				access.setDestination(locations.get(access.getDestinationName()));
			}
		}
	}

	public Player getCharacter() {
		return character;
	}

	public HashMap<String, NPC> getEntities() {
		return entities;
	}

	public HashMap<String, Location> getLocations() {
		return locations;
	}

}
