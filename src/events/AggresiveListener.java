package events;

import entities.Entity;
import entities.NPC;
import entities.Player;
import items.types.Weapon;
import states.Dead;

public class AggresiveListener implements EntityListener, Runnable {
	private NPC npc;
	private Player player;
	private Thread thread;
	private int turn;
	boolean playerAppeared;

	public AggresiveListener(NPC npc) {
		this.npc = npc;
		playerAppeared = false;
	}

	@Override
	public void onEntityAppeared(Entity entity) {
		if (entity instanceof Player) {
			player = (Player) entity;
			thread = new Thread(this);
			turn = npc.getGameManager().getTurn();
			thread.start();
		}
	}

	// NPC esta vivo?
	// Jugador esta vivo?
	// Jugador esta en el mismo lugar?
	// Mandale
	@Override
	public void run() {
		while (!thread.isInterrupted() && !npc.getState().getClass().equals(Dead.class)
				&& npc.getLocation().getEntities().get(player.getName()) != null
				&& !player.getState().getClass().equals(Dead.class)) {
			int newTurn = npc.getGameManager().getTurn();
			if (turn != newTurn) {
				if (playerAppeared && turn != newTurn) {
					Weapon weapon = (Weapon) npc.getInventory().get(npc.getWeaponName());
					npc.attack(weapon, player);
				}
				playerAppeared = true;
				turn = npc.getGameManager().getTurn();
			}
		}
	}

	@Override
	public void onEntityDisappeared(Entity entity) {
		if (entity == null) {
			if (thread != null)
				thread.interrupt();
		}
		if (entity instanceof Player)
			if (thread != null) {
				thread.interrupt();
			}
		playerAppeared = false;
	}

}
