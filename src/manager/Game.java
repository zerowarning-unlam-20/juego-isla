package manager;

import java.util.HashMap;

import entities.NPC;
import entities.Player;
import events.Event;
import island.Location;
import items.Access;
import tools.Gender;

public class Game {
	private HashMap<String, NPC> entities;
	private HashMap<String, Location> locations;
	private HashMap<String, Access> accesses;
	private HashMap<String, Event> events;
	private Player character;

	// Cuando se construye game asignar el nombre del jugador que vino del gson

	public Game(GameManager gameManager, Player character, HashMap<String, Location> locations,
			HashMap<String, NPC> npcList, HashMap<String, Event> events) {
		accesses = new HashMap<>();
		this.events = events;
		this.locations = locations;
		this.character = character;
		this.character.linkToManager(gameManager);

		this.character.setLocation(locations.get(character.getInitialLocation()));
		locations.get(character.getInitialLocation()).addEntity(character);
		this.entities = npcList;

		for (NPC npc : npcList.values()) {
			npc.linkToManager(gameManager);
			npc.setLocation(locations.get(npc.getInitialLocation()));
			locations.get(npc.getInitialLocation()).addEntity(npc);
		}
		linkLocations();

		linkTriggers(gameManager);
	}

	public Game(GameManager gameManager, String name, Gender gender, Player character,
			HashMap<String, Location> locations, HashMap<String, NPC> npcList, HashMap<String, Event> events) {

		accesses = new HashMap<>();
		this.events = events;
		this.locations = locations;
		this.character = new Player(character, name, gender);
		this.character.linkToManager(gameManager);

		this.character.setLocation(locations.get(character.getInitialLocation()));
		locations.get(character.getInitialLocation()).addEntity(character);
		this.entities = npcList;

		for (NPC npc : npcList.values()) {
			npc.linkToManager(gameManager);
			npc.setLocation(locations.get(npc.getInitialLocation()));
			locations.get(npc.getInitialLocation()).addEntity(npc);
		}
		linkLocations();

		linkTriggers(gameManager);
	}

	private void linkTriggers(GameManager gameManager) {
		if (events == null) {
			events = new HashMap<>();
			return;
		}
		for (Event event : events.values()) {
			event.linkManager(gameManager);
		}
	}

	public boolean pullTrigger(String name) {
		Event event = events.get(name);
		if (event != null) {
			event.execute();
			events.remove(name);
			return true;
		}
		return false;
	}

	private void linkLocations() {
		for (Location location : locations.values()) {
			for (Access access : location.getAccesses().values()) {
				accesses.put(access.getDestinationName().toLowerCase(), access);
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

	public HashMap<String, Event> getEvents() {
		return events;
	}

	public HashMap<String, Access> getAccesses() {
		return accesses;
	}

}
