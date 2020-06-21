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
import items.Item;
import states.Dead;
import states.Lost;
import states.Normal;

public class ItemDeserializer implements JsonDeserializer<Item> {

	@Override
	public Item deserialize(JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		JsonElement jsonType = jsonObject.get("type");
		String type = jsonType.getAsString();

		return context.deserialize(jsonObject, ObjectType.forName(type).clazz);
	}
}