package tools;

import entities.UserCharacter;
import island.Access;
import island.GameObject;
import island.Location;
import items.Container;
import items.Item;
import items.Liquid;
import items.Tool;

public enum ObjectType {
	ACCESS(Access.class), TOOL(Tool.class), ITEM(Item.class), LIQUID(Liquid.class),
	GAMEOBJECT(GameObject.class), CHARACTER(UserCharacter.class), CONTAINER(Container.class), LOCATION(Location.class);

	public final Class<?> clazz;

	ObjectType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public static ObjectType forName(String name) {
		ObjectType objectToReturn = null;
		
		for (ObjectType t : values())
			if (name.equals(t.name())) {
				objectToReturn = t;
				break;
			}

		return objectToReturn;
	}
}
