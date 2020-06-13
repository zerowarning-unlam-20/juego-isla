package manager;

import java.util.HashMap;
import java.util.List;

import entities.Entity;
import entities.NPC;
import entities.UserCharacter;
import island.Location;

public class Game {
	private List<Location> locations;
	private HashMap<Integer, Entity> entities;
	private HashMap<Integer, Location> locationIds = new HashMap<>();
	private HashMap<String, Location> locationsMap = new HashMap<>();
	private UserCharacter character;

	public Game(UserCharacter character, List<Location> locations) {
		this.locations = locations;
		this.character = character;
		linkLocations();
		locationsMap.clear();
		for (Location l : locations) {
			locationsMap.put(l.getName().toLowerCase(), l);
			locationIds.put(l.getId(), l);
		}
	}

	public Game(GameManager gameManager, UserCharacter character, List<Location> locations, List<NPC> NPClist) {
		this.locations = locations;
		this.character = character;
		this.character.linkToManager(gameManager);
		
		for (Location l : locations) {
			locationsMap.put(l.getName().toLowerCase(), l);
			locationIds.put(l.getId(), l);
		}
		
		this.character.setLocation(this.locationIds.get(character.getInitialLocation()));
		this.entities = new HashMap<Integer, Entity>();
		for (NPC e : NPClist) {
			e.linkToManager(gameManager);
			this.entities.put(e.getId(), e);
			e.setLocation(this.locationIds.get(e.getInitialLocation()));
		}
		linkLocations();
	}

	private void linkLocations() {
		for (Location location : getLocations()) {
			for (Location otherLocation : getLocations()) {
				if (location != otherLocation) {
					location.addLink(otherLocation);
				}
			}
		}
	}

	public List<Location> getLocations() {
		return locations;
	}

	public UserCharacter getCharacter() {
		return character;
	}

	public Location getLocationFromString(String lugar) {
		return locationsMap.get(lugar);
	}

}
