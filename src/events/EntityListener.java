package events;

import entities.Entity;

public interface EntityListener {
	public void onEntityAppeared(Entity entity);

	public void onEntityDisappeared(Entity entity);
}