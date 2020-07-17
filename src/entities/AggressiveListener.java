package entities;

import states.Dead;

public class AggressiveListener implements EntityListener, Runnable {
	private NPC npc;
	private Player player;
	private Thread thread;
	private int turn;
	boolean playerAppeared;

	public AggressiveListener(NPC npc) {
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
		try {
			while (thread != null && !thread.isInterrupted() && !npc.getState().getClass().equals(Dead.class)
					&& !player.getState().getClass().equals(Dead.class) && npc.getLocation() != null
					&& npc.getLocation().getEntities().get(player.getName().toLowerCase()) != null) {
				int newTurn = npc.getGameManager().getTurn();
				if (turn != newTurn) {
					if (playerAppeared && turn != newTurn) {
						npc.attack(npc.getBestWeapon(), player.getName().toLowerCase());
					}
					playerAppeared = true;
					turn = npc.getGameManager().getTurn();
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
		if (thread != null && thread.isInterrupted())
			thread = null;
		playerAppeared = false;
	}

}