package items.properties;

import entities.Entity;

public interface Usable {
	public boolean use();
	public boolean use(Entity entity);
}
