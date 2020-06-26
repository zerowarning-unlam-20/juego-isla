package island;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entities.AggresiveListener;
import entities.Entity;
import entities.NPC;
import entities.Player;
import items.Access;
import items.Item;
import states.Dead;
import tools.Gender;
import tools.NPCType;

public class Location extends GameObject {
	private boolean visible;

	private HashMap<String, Area> areas;
	private Area lastAccessed;

	private HashMap<String, Access> accesses;

	private HashMap<String, Entity> entities;

	public Location(Gender gender, String name, String description, boolean visible, HashMap<String, Area> areas,
			HashMap<String, Access> accesses) {
		super(gender, name, description);
		this.areas = areas;
		this.visible = visible;
		this.entities = new HashMap<String, Entity>();
		this.accesses = accesses;
	}

	@Override
	public String getDescription() {
		return description;
	}

	public String lookAccesses() {
		String acc = "";
		String message = "";
		for (Access access : accesses.values()) {
			acc += access.getDescription() + ", ";
		}
		if (acc.contains(", ")) {
			acc = acc.substring(0, acc.length() - 2);
			acc += ".\n";
		}
		if (!acc.isEmpty()) {
			message += "Se ve: " + acc;
		}
		return message;
	}

	public String lookAround() { // Barrido de location
		String ent = "";
		String message = "";

		for (Entity entity : entities.values()) {
			if (!entity.getClass().equals(Player.class))
				ent += entity.getDescription() + ", ";
		}
		if (ent.contains(", ")) {
			ent = ent.substring(0, ent.length() - 2);
			ent += ".\n";
		}
		message += ent;
		for (Item item : getAllItems()) {
			message += item.getDescription() + ", ";
		}
		if (message.length() > 2 && message.substring(message.length() - 2, message.length()).contains(", "))
			message = message.substring(0, message.length() - 2);

		return message;
	}

	public Item getItemFromAreas(String name) {
		Item result = null;
		for (Area area : areas.values()) {
			result = area.getItem(name);
			if (result != null) {
				lastAccessed = area;
				break;
			}
		}
		return result;
	}

	public List<Item> getAllItems() {
		ArrayList<Item> result = new ArrayList<>();
		for (Area area : areas.values()) {
			result.addAll(area.getItems().values());
		}
		return result;
	}

	public Area getArea(String name) {
		return areas.get(name);
	}

	public Area getLastArea() {
		return lastAccessed;
	}

	public Access getAccess(String name) {
		return accesses.get(name);
	}

	public void addAccess(Access access) {
		accesses.put(access.getDestinationName().toLowerCase(), access);
	}

	public boolean addLink(Location other) {
		Access access = accesses.get(other.name);
		if (access != null) {
			access.setDestination(other);
			Access b = access.getDestination().getAccess(this.name);
			if (b != null)
				b.setDestination(this);
			return true;
		}
		return false;
	}

	public HashMap<String, Entity> getEntities() {
		return entities;
	}

	public boolean isVisible() {
		return visible;
	}

	public void addEntity(Entity entity) {
		entities.put(entity.name.toLowerCase(), entity);

		// Exclusivo para NPCs, comportamiento
		if (entity instanceof NPC && !entity.getState().getClass().equals(Dead.class)) {
			NPC npc = (NPC) entity;
			if (npc.getType().equals(NPCType.AGGRESSIVE)) {
				npc.setEntityListener(new AggresiveListener(npc));
			}
		}

		// Le avisamos a todos los que estan quien entró

		for (Entity ent : entities.values()) {
			if (ent != entity && ent instanceof NPC && !ent.getState().getClass().equals(Dead.class)) {
				if (((NPC) ent).getEntityListener() != null) {
					((NPC) ent).getEntityListener().onEntityAppeared(entity);
				}
			}
		}
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity.name);
		for (Entity ent : entities.values()) {
			if (ent != entity && ent instanceof NPC && !ent.getState().getClass().equals(Dead.class)) {
				if (((NPC) ent).getEntityListener() != null) {
					((NPC) ent).getEntityListener().onEntityDisappeared(entity);
				}
			}
		}
	}

	public HashMap<String, Access> getAccesses() {
		return accesses;
	}

	public void clearLastArea() {
		lastAccessed = null;
	}
}
