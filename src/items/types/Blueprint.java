package items.types;

import java.util.HashMap;
import java.util.Map;

import entities.Entity;
import items.Inventory;
import items.Item;
import items.properties.Holdable;
import items.properties.Readablel;
import items.properties.Usable;
import tools.Gender;
import tools.MessageType;
import tools.Namber;
import tools.ResourceType;

public class Blueprint extends Item implements Usable, Readablel, Holdable {
	private HashMap<ResourceType, Integer> requirements;
	private Item result;

	public Blueprint(Gender gender, Namber number, String name, String description, int price,
			HashMap<ResourceType, Integer> requirements, Item produces) {
		super(gender, number, name, description, price);
		this.requirements = requirements;
		this.result = produces;
	}

	public String getRequirements() {
		String result = "Se necesita: ";
		for (Map.Entry<ResourceType, Integer> entry : requirements.entrySet()) {
			result += entry.getKey().getValue() + ": x" + entry.getValue() + "\n";
		}
		return result;
	}

	public String getCreatedItemName() {
		return result.getName();
	}

	@Override
	public boolean use(Entity entity) {
		if (entity.getLocation().isVisible()) {
			Item result = this.produce(entity.getInventory());
			if (result != null) {
				entity.addItem(result);
				entity.removeItem(this);
				entity.getGameManager().sendMessage(MessageType.EVENT, entity.getName(),
						"Ahora tenes: " + result.getDescription());
				entity.getGameManager().getGame().pullTrigger("_use_" + this.name, entity);
			} else {
				entity.getGameManager().sendMessage(MessageType.EVENT, entity.getName(),
						"No tenes los elementos necesarios");
			}
		} else
			entity.getGameManager().sendMessage(MessageType.EVENT, entity.getName(), "No se ve nada");
		return result != null;
	}

	@Override
	public String read(boolean visible) {
		return visible ? getRequirements() : "No se ve nada";
	}

	/*
	 * si (elemento + suma <= requerido) elemento <-- Marcar para eliminar sumar al
	 * total sino restar cantidad del recurso
	 */

	private Item produce(Inventory inventory) {
		HashMap<ResourceType, Integer> mapCount = new HashMap<>();
		mapCount.putAll(requirements);
		HashMap<String, Integer> usedItems = new HashMap<>();

		for (Item item : inventory.getItems()) {
			if (item instanceof Resource) {
				Resource res = (Resource) item;
				int quantity = 0;
				int lastValue = 0;
				if (requirements.get(res.getResourceType()) != null) { // El elemento actual es de un tipo necesitado
					if (mapCount.get(res.getResourceType()) - res.getQuantity() > 0) {
						quantity = res.getQuantity();
						mapCount.put(res.getResourceType(), mapCount.get(res.getResourceType()) - quantity);
						usedItems.put(res.getName(), quantity);
					} else {
						lastValue = mapCount.get(res.getResourceType());
						mapCount.put(res.getResourceType(), 0);
						quantity = Math.abs(lastValue - res.getQuantity());
						usedItems.put(res.getName().toLowerCase(), lastValue);
					}
				}
			}
		}

		boolean completed = true;
		for (int value : mapCount.values()) {
			if (value != 0) {
				completed = false;
				break;
			}
		}

		// En el caso que funcione todo se saca lo necesario
		if (completed) {
			inventory.removeResources(usedItems);
			return result;
		}
		return null;
	}

}
