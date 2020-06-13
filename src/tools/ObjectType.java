package tools;

import com.google.gson.annotations.SerializedName;

import entities.UserCharacter;
import entities.NPC;
import island.Access;
import island.GameObject;
import island.Location;
import items.Container;
import items.Item;
import items.Liquid;
import items.Tool;
import items.Text;

public enum ObjectType {
	@SerializedName("access")
	ACCESS(Access.class),

	@SerializedName("tool")
	TOOL(Tool.class),

	@SerializedName("item")
	ITEM(Item.class),

	@SerializedName("liquid")
	LIQUID(Liquid.class),

	@SerializedName("gameObject")
	GAMEOBJECT(GameObject.class),

	@SerializedName("usercharacter")
	USER_CHARACTER(UserCharacter.class),

	@SerializedName("npc")
	NPC(NPC.class),
	
	@SerializedName("container")
	CONTAINER(Container.class),

	@SerializedName("location")
	LOCATION(Location.class),
	
	@SerializedName("text")
	TEXT(Text.class);

	public final Class<?> clazz;

	ObjectType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public static ObjectType forName(String name) {
		ObjectType objectToReturn = null;
		for (ObjectType t : values())
			if (name.contentEquals(t.name().toLowerCase())) {
				objectToReturn = t;
				break;
			}

		return objectToReturn;
	}
}
