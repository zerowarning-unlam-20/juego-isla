package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entities.NPC;
import entities.Player;
import events.Event;
import island.GameObject;
import island.Location;
import items.Item;

public class WorldLoader {

	private String path;

	public WorldLoader(String path) {
		this.path = path;
	}

	public HashMap<String, Location> loadLocations() throws IOException, FileNotFoundException {
		File file = new File("games/" + path + "/zones.json");
		InputStream is;

		is = new FileInputStream(file);

		Writer writer = new StringWriter();
		char[] buffer = new char[1024];

		Reader reader = new BufferedReader(new InputStreamReader(is));
		int n;
		while ((n = reader.read(buffer)) != -1) {
			writer.write(buffer, 0, n);
		}
		reader.close();
		String result = writer.toString();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(GameObject.class, new GameObjectDeserializer());
		gsonBuilder.registerTypeAdapter(Item.class, new ItemDeserializer());
		Gson gson = gsonBuilder.create();
		List<GameObject> objects = Arrays.asList(gson.fromJson(result, GameObject[].class));

		HashMap<String, Location> results = new HashMap<String, Location>();
		for (GameObject go : objects) {
			results.put(go.getName().toLowerCase(), (Location) go);
		}
		return results;
	}

	public HashMap<String, NPC> loadEntities() throws IOException, FileNotFoundException {
		File file = new File("games/" + path + "/NPCs.json");
		InputStream is;

		is = new FileInputStream(file);

		Writer writer = new StringWriter();
		char[] buffer = new char[1024];

		Reader reader = new BufferedReader(new InputStreamReader(is));
		int n;
		while ((n = reader.read(buffer)) != -1) {
			writer.write(buffer, 0, n);
		}
		reader.close();
		String result = writer.toString();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(GameObject.class, new GameObjectDeserializer());
		gsonBuilder.registerTypeAdapter(Item.class, new ItemDeserializer());
		Gson gson = gsonBuilder.create();
		List<GameObject> objects = Arrays.asList(gson.fromJson(result, GameObject[].class));
		HashMap<String, NPC> results = new HashMap<String, NPC>();
		for (GameObject go : objects) {
			results.put(go.getName().toLowerCase(), (NPC) go);
		}
		return results;
	}

	public HashMap<String, Event> loadEvents() throws IOException, FileNotFoundException {
		File file = new File("games/" + path + "/events.json");
		InputStream is;

		is = new FileInputStream(file);

		Writer writer = new StringWriter();
		char[] buffer = new char[1024];

		Reader reader = new BufferedReader(new InputStreamReader(is));
		int n;
		while ((n = reader.read(buffer)) != -1) {
			writer.write(buffer, 0, n);
		}
		reader.close();
		String result = writer.toString();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Event.class, new EventDeserializer());
		Gson gson = gsonBuilder.create();
		List<Event> events = Arrays.asList(gson.fromJson(result, Event[].class));
		HashMap<String, Event> results = new HashMap<>();
		for (Event evt : events) {
			results.put(evt.getName(), evt);
		}
		return results;
	}

	public Player loadCharacter() throws IOException, FileNotFoundException {
		File file = new File("games/" + path + "/character.json");
		InputStream is;

		is = new FileInputStream(file);

		Writer writer = new StringWriter();
		char[] buffer = new char[1024];

		Reader reader = new BufferedReader(new InputStreamReader(is));
		int n;
		while ((n = reader.read(buffer)) != -1) {
			writer.write(buffer, 0, n);
		}
		reader.close();
		String result = writer.toString();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(GameObject.class, new GameObjectDeserializer());
		gsonBuilder.registerTypeAdapter(Item.class, new ItemDeserializer());
		Gson gson = gsonBuilder.create();
		return (Player) gson.fromJson(result, GameObject.class);
	}

	public String loadInitialMessage() throws IOException {
		File file = new File("games/" + path + "/initialMessage.start");
		InputStream is;

		is = new FileInputStream(file);

		Writer writer = new StringWriter();
		char[] buffer = new char[1024];

		Reader reader = new BufferedReader(new InputStreamReader(is));
		int n;
		while ((n = reader.read(buffer)) != -1) {
			writer.write(buffer, 0, n);
		}
		reader.close();
		return writer.toString();
	}

	public static String getHelpCommands() throws IOException {
		File file = new File("commands.help");
		InputStream is;

		is = new FileInputStream(file);

		Writer writer = new StringWriter();
		char[] buffer = new char[1024];

		Reader reader = new BufferedReader(new InputStreamReader(is));
		int n;
		while ((n = reader.read(buffer)) != -1) {
			writer.write(buffer, 0, n);
		}
		reader.close();
		return writer.toString();
	}

	public static List<String> listFolders(final File folder) {
		ArrayList<String> folders = new ArrayList<>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.listFiles().length > 0)
                folders.add(fileEntry.getName());
		}
		return folders;
	}

}
