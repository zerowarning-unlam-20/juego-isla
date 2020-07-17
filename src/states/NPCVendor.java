package states;

import entities.AggressiveListener;
import entities.Attack;
import entities.NPC;
import entities.Player;
import items.Item;
import items.properties.Holdable;
import items.types.Liquid;
import items.types.Resource;
import items.types.Source;
import tools.MessageType;
import tools.ResourceType;

public class NPCVendor implements State {
	private NPC character;

	public NPCVendor(NPC character) {
		this.character = character;
	}

	@Override
	public boolean open(String objectName) {
		return false;
	}

	@Override
	public boolean unlock(String toUnlockName, String keyName) {
		return false;
	}

	@Override
	public boolean look(String name) {
		return false;
	}

	@Override
	public boolean goTo(String locationName) {
		return false;
	}

	@Override
	public boolean grab(String name) {
		return false;
	}

	@Override
	public boolean grab(String sourceName, String itemName) {
		Item src = character.getLocation().getItemFromAreas(sourceName);

		String message = "No hay nada para agarrar.";
		boolean result = false;
		if (!(src instanceof Holdable)) {
			Source source = (Source) src;
			if (!(source.getContent() instanceof Liquid)) {
				result = source.giveItems(character);
			} else
				message = "Necesito un recipiente o algo para agarrarlo.";
		}
		character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
		return result;
	}

	@Override
	public boolean grab(String itemName, String sourceName, String containerName) {
		return false;
	}

	@Override
	public boolean lookAround() {
		return false;
	}

	@Override
	public boolean lookInventory() {
		return false;
	}

	@Override
	public boolean attack(String weaponName, String targetName) {
		return false;
	}

	@Override
	public State receiveAttack(Attack attack) {
		Double modifier = character.getWeaknessModifier(attack.getDamageType());
		Double totalDamage = 0d;
		State newState = this;
		if (modifier != null)
			totalDamage = modifier * attack.getDamage();
		else
			totalDamage = attack.getDamage();
		if (character.getHealth() - totalDamage < 0)
			character.setHealth(0d);
		else
			character.setHealth(character.getHealth() - totalDamage);

		if (character.getHealth() <= 0) {
			character.getGameManager().sendMessage(MessageType.EVENT, character.getName(),
					"Cayó " + character.getSingularName());
			character.onDeath(attack);
			newState = new Dead(character);
		} else {
			character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), "Ahora vas a ver");
			character.setEntityListener(new AggressiveListener(character));
			character.getEntityListener().onEntityAppeared(attack.getAttacker());
			newState = new NPCNormal(character);
		}
		return newState;
	}

	@Override
	public boolean talk(String otherName, String message) {
		character.getGameManager().sendMessage(MessageType.CHARACTER, character.getName(), message);
		return true;
	}

	@Override
	public boolean listen(String otherName, String message) {
		String answer = character.getChat(message);
		if (answer == null) {
			answer = character.getChat("ddefaultt");
		}
		if (message.contains("comprar")) {
			answer = buyItem(message.replace("comprar", "").trim());
		}
		talk(otherName.toLowerCase(), answer);
		return answer != null;
	}

	// Por ahora tengo en cuenta que hay un solo item currency, sino me vuelvo loco
	private String buyItem(String message) {
		String result = "";
		boolean buyed = false;
		if (message.isEmpty()) {
			return character.getInventory().showItemsToSell();
		}
		Player player = character.getGameManager().getGame().getCharacter();

		Item searchedItem = character.getInventory().getItem(message.toLowerCase());
		if (searchedItem != null && searchedItem.getValue() >= 0) {
			for (Item item : player.getInventory().getItems()) {
				if (item instanceof Resource && ((Resource) item).getResourceType().equals(ResourceType.CURRENCY)
						&& ((Resource) item).getQuantity() >= searchedItem.getValue()) {
					player.getInventory().removeResource(item.getName(), searchedItem.getValue());
					player.addItem(searchedItem);
					result = "gracias por comprar! Tu compra salió $" + searchedItem.getValue();
					buyed = true;
					break;
				}
			}
			if (!buyed) {
				result = "no tenes con que pagarme";
			}
		} else
			result = "te lo debo";
		return result;
	}

	@Override
	public boolean use(String itemName) {
		return false;
	}

	@Override
	public boolean read(String itemName) {
		return false;
	}

	@Override
	public boolean inspect(String itemName) {
		return false;
	}

	@Override
	public void lookState() {
	}

	@Override
	public State drink(String name, String dispenserName) {
		return this;
	}

	@Override
	public boolean drop(String item) {
		return false;
	}

	@Override
	public State eat(String name) {
		return this;
	}
}
