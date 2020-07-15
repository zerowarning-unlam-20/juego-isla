package events;

import java.util.ArrayList;

import entities.Player;
import items.Effect;
import items.Item;
import manager.GameManager;

public class PlayerEvent extends Event {

	private Effect effect;
	private ArrayList<Item> addedItems;

	public PlayerEvent(String name, String nextEvent, GameManager gameManager, boolean allowNormalAction, Effect effect,
			ArrayList<Item> addedItems) {
		super(name, nextEvent, gameManager, allowNormalAction);
		this.effect = effect;
		this.addedItems = addedItems;
	}

	@Override
	public void execute() {
		Player player = gameManager.getGame().getCharacter();
		if (addedItems != null) {
			for (Item item : addedItems) {
				player.addItem(item);
			}
		}
		if (effect != null) {
			effect.apply(player);
		}
		gameManager.getGame().pullTrigger(nextEvent);
	}

	@Override
	public void linkManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
}
