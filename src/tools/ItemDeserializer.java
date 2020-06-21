package tools;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import items.Item;

public class ItemDeserializer implements JsonDeserializer<Item> {
	/**
	 * Esto es una clase que viene justamente de json, lo que hace es ver en que
	 * parte de la herencia esta para supuestamente usar los constructores pero es
	 * medio mentiroso. Lo que hace es agarrar absolutamente todos los atributos y
	 * los llena. Si un atributo no esta reflejado en json va nulo directamente.
	 */
	@Override
	public Item deserialize(JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		JsonElement jsonType = jsonObject.get("type");
		String type = jsonType.getAsString();

		return context.deserialize(jsonObject, ObjectType.forName(type).clazz);
	}
}