package tools;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import entities.Entity;
import entities.NPC;
import entities.UserCharacter;
import island.GameObject;
import states.Dead;
import states.Lost;
import states.NPCNormal;
import states.Normal;

public class GameObjectDeserializer implements JsonDeserializer<GameObject> {

	@Override
	public GameObject deserialize(JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		JsonElement jsonType = jsonObject.get("type");
		String type = jsonType.getAsString();
		if (type.contentEquals("NPC")) {
			Entity character = context.deserialize(jsonObject, NPC.class);
			JsonElement stateName = jsonObject.get("stateName");
			String sn = stateName.getAsString();
			switch (sn) {
			case "normal":
				character.setState(new NPCNormal((NPC)character));
				break;
			case "lost":
				character.setState(new Lost(character));
				break;
			case "dead":
				character.setState(new Dead(character));
				break;
			}
			return character;
		} else if (type.contentEquals("userCharacter")) {
			Entity character = context.deserialize(jsonObject, UserCharacter.class);
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