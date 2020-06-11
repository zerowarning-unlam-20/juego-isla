package manager;

import java.util.HashMap;
import java.util.List;

import entities.UserCharacter;
import island.Location;

public class Game {
	private List<Location> locations;
	private static HashMap<String, Location> locationsMap = new HashMap<>();
	private UserCharacter character;

	public Game(UserCharacter character, List<Location> locations) {
		this.locations = locations;
		this.character = character;
		linkLocations();
		locationsMap.clear();
		for (Location l : locations) {
			locationsMap.put(l.getName().toLowerCase(), l);
		}
	}

	public static Location getLocation(String name) {
		return locationsMap.get(name);
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

}
