package tools;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import entities.Entity;
import entities.NPC;
import entities.Player;
import island.GameObject;
import states.Dead;
import states.Lost;
import states.NPCNormal;
import states.NPCVendor;
import states.Normal;

public class GameObjectDeserializer implements JsonDeserializer<GameObject> {
	/**
	 * Esto es una clase que viene justamente de json, lo que hace es ver en que
	 * parte de la herencia esta para supuestamente usar los constructores pero es
	 * medio mentiroso. Lo que hace es agarrar absolutamente todos los atributos y
	 * los llena. Si un atributo no esta reflejado en json va nulo directamente.
	 */

	@Override
	public GameObject deserialize(JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		JsonElement jsonType = jsonObject.get("type");
		String type = jsonType.getAsString();
		if (ObjectType.valueOf(type.toUpperCase()).equals(ObjectType.NPC)) {
			NPC character = context.deserialize(jsonObject, NPC.class);
			JsonElement stateName = jsonObject.get("stateName");
			String sn = stateName.getAsString();
			switch (sn) {
			case "vendor":
				character.setState(new NPCVendor(character));
				break;
			case "normal":
				character.setState(new NPCNormal(character));
				break;
			case "lost":
				character.setState(new Lost(character));
				break;
			case "dead":
				character.setState(new Dead(character));
				break;
			}
			return character;
		} else if (ObjectType.valueOf(type.toUpperCase()).equals(ObjectType.PLAYER)) {
			Entity character = context.deserialize(jsonObject, Player.class);
			JsonElement stateName = jsonObject.get("stateName");
			String sn = stateName.getAsString();
			switch (sn) {
			case "normal":
				character.setState(new Normal(character));
				break;
			case "lost":
				character.setState(new Lost(character));
				break;
			case "dead":
				character.setState(new Dead(character));
				break;
			}
			return character;

		}
		return context.deserialize(jsonObject, ObjectType.forName(type).clazz);
	}
}