package manager;

import java.util.List;

import entities.UserCharacter;
import items.Location;

public class Game {
	private List<Location> locations;
	private UserCharacter character;

	public Game(UserCharacter character, List<Location> locations) {
		this.locations = locations;
		this.character = character;
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

}
