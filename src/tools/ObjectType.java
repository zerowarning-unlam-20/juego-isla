package tools;

import entities.Access;
import entities.Food;
import entities.Tool;
import entities.Item;
import entities.Liquid;
import entities.GameObject;
import entities.UserCharacter;
import entities.Container;
import entities.Location;

public enum ObjectType {
	ACCESS(Access.class), FOOD(Food.class), TOOL(Tool.class), ITEM(Item.class), LIQUID(Liquid.class),
	GAMEOBJECT(GameObject.class), CHARACTER(UserCharacter.class), CONTAINER(Container.class), LOCATION(Location.class);

	public final Class<?> clazz;

	ObjectType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public static ObjectType forName(String name) {
		for (ObjectType t : values())
			if (name.equals(t.name()))
				return t;

		return null;
	}
}
