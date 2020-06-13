package tools;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import entities.Entity;
import entities.NPC;
import entities.UserCharacter;
import island.Access;
import island.GameObject;
import island.Location;
import items.Item;
import items.Text;
import manager.GameManager;
import states.NPCNormal;

public class GameObjectDeserializer<T> implements JsonDeserializer<T> {

	@Override
	public T deserialize(JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {

		JsonObject jsonObject = json.getAsJsonObject();

		JsonElement jsonType = jsonObject.get("type");
		String type = jsonType.getAsString();
		
		return context.deserialize(jsonObject, ObjectType.forName(type).clazz);

	}

}