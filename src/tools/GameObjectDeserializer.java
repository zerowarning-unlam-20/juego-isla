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

import entities.NPC;
import entities.UserCharacter;
import island.Access;
import island.GameObject;
import island.Location;
import items.Item;
import items.Text;
import manager.GameManager;
import states.NPCNormal;

public class GameObjectDeserializer implements JsonDeserializer<GameObject> {

	@Override
	public GameObject deserialize(JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {

		JsonObject jsonObject = json.getAsJsonObject();

		JsonElement jsonType = jsonObject.get("type");
		String type = jsonType.getAsString();

		GameObject typeModel = null;

		int id = jsonObject.get("id").getAsInt();
		String name = jsonObject.get("name").getAsString();
		String description = jsonObject.get("description").getAsString();
		String gender = jsonObject.get("gender").getAsString();

		switch (type) {
		case "access":
			typeModel = new Access(jsonObject.get("id").getAsInt(),
					Gender.valueOf(jsonObject.get("gender").getAsString()), jsonObject.get("name").getAsString(),
					jsonObject.get("description").getAsString(), jsonObject.get("locked").getAsBoolean(),
					jsonObject.get("opened").getAsBoolean(), jsonObject.get("destination").getAsInt(),
					jsonObject.get("idKey").getAsInt());
			break;
		case "location":
			// Access
			Gson gsonDeserializer = new GsonBuilder().registerTypeAdapter(Access.class, new GameObjectDeserializer())
					.registerTypeAdapter(Item.class, new GameObjectDeserializer()).create();

			JsonElement accessArray = jsonObject.get("accesses");
			List<Access> accesses = Arrays.asList(gsonDeserializer.fromJson(accessArray, Access[].class));
			// Items
			JsonElement itemsArray = jsonObject.get("items");
			List<Item> items = Arrays.asList(gsonDeserializer.fromJson(itemsArray, Item[].class));
			if (items == null)
				items = new ArrayList<Item>();
			if (accesses == null)
				accesses = new ArrayList<Access>();
			boolean visible = jsonObject.get("visible").getAsBoolean();
			typeModel = new Location(id, Gender.valueOf(gender), name, description, visible, items, accesses);
			break;
		case "text":
			String content = jsonObject.get("content").getAsString();
			typeModel = new Text(id, Gender.valueOf(gender), name, description, content);
			break;
		case "entity":
			Double health = jsonObject.get("health").getAsDouble();
			boolean isUser = jsonObject.get("user").getAsBoolean();
			int initialLocation = jsonObject.get("initialLocation").getAsInt();
			if (isUser) {
				Gson gsonCharItems = new GsonBuilder().registerTypeAdapter(Access.class, new GameObjectDeserializer())
						.registerTypeAdapter(Item.class, new GameObjectDeserializer()).create();
				JsonElement userItems = jsonObject.get("items");
				List<Item> uItems = Arrays.asList(gsonCharItems.fromJson(userItems, Item[].class));
				if (uItems == null)
					uItems = new ArrayList<Item>();
				typeModel = new UserCharacter(null, id, Gender.valueOf(gender), name, description, uItems,
						initialLocation);
			} else {
				Gson gsonCharItems = new GsonBuilder().registerTypeAdapter(Access.class, new GameObjectDeserializer())
						.registerTypeAdapter(Item.class, new GameObjectDeserializer()).create();
				JsonElement userItems = jsonObject.get("items");
				String npcType = jsonObject.get("npcType").getAsString();
				int idUnlocks = jsonObject.get("idUnlocks").getAsInt();
				List<Item> uItems = Arrays.asList(gsonCharItems.fromJson(userItems, Item[].class));
				if (uItems == null)
					uItems = new ArrayList<Item>();
				typeModel = new NPC(null, id, Gender.valueOf(gender), name, description, null, uItems,
						NPCType.valueOf(npcType), idUnlocks, initialLocation);
			}
		}

		return typeModel;
	}

}