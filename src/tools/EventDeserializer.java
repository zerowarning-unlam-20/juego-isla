package tools;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import events.Event;

public class EventDeserializer implements JsonDeserializer<Event> {
	@Override
	public Event deserialize(JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();
		JsonElement jsonType = jsonObject.get("type");
		String type = jsonType.getAsString();
		return context.deserialize(jsonObject, ObjectType.forName(type).clazz);
	}
}
