package tools;

import com.google.gson.annotations.SerializedName;

import entities.NPC;
import entities.Player;
import events.AccessEvent;
import events.EndEvent;
import events.PlayerEvent;
import events.StoryEvent;
import island.Area;
import island.GameObject;
import island.Location;
import items.Access;
import items.Effect;
import items.Item;
import items.types.Blueprint;
import items.types.Bottle;
import items.types.Box;
import items.types.Food;
import items.types.Liquid;
import items.types.MapItem;
import items.types.Resource;
import items.types.Source;
import items.types.Text;
import items.types.Weapon;

/**
 * 
 * Importantísimo para el deserializer de json, A partir del valor "tipo" que
 * tiene que estar bien escrito, consigue que clase es para despues instanciarla
 *
 */

public enum ObjectType {
	@SerializedName("source")
	SOURCE(Source.class),

	@SerializedName("box")
	BOX(Box.class),

	@SerializedName("resource")
	RESOURCE(Resource.class),

	@SerializedName("single_container")
	SINGLE_CONTAINER(Bottle.class),

	@SerializedName("access")
	ACCESS(Access.class),

	@SerializedName("map_item")
	MAP_ITEM(MapItem.class),

	@SerializedName("item_effect")
	ITEM_EFFECT(Effect.class),

	@SerializedName("item")
	ITEM(Item.class),

	@SerializedName("weapon")
	WEAPON(Weapon.class),

	@SerializedName("blueprint")
	BLUEPRINT(Blueprint.class),

	@SerializedName("food")
	FOOD(Food.class),

	@SerializedName("area")
	AREA(Area.class),

	@SerializedName("liquid")
	LIQUID(Liquid.class),

	@SerializedName("gameObject")
	GAMEOBJECT(GameObject.class),

	@SerializedName("player")
	PLAYER(Player.class),

	@SerializedName("npc")
	NPC(NPC.class),

	@SerializedName("location")
	LOCATION(Location.class),

	@SerializedName("text")
	TEXT(Text.class),

	@SerializedName("access_event")
	ACCESS_EVENT(AccessEvent.class),

	@SerializedName("story_event")
	STORY_EVENT(StoryEvent.class),

	@SerializedName("player_event")
	PLAYER_EVENT(PlayerEvent.class),

	@SerializedName("end_event")
	END_EVENT(EndEvent.class);

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
