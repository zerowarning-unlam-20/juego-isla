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
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entities.NPC;
import entities.UserCharacter;
import island.GameObject;
import island.Location;
import items.Item;

public class WorldLoader {

	public static List<Location> loadLocations(String folder) throws IOException, FileNotFoundException {
		File file = new File(folder + "/zones.json");
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
		List<Location> results = new ArrayList<>();
		for (GameObject go : objects) {
			results.add((Location) go);
		}
		return results;
	}

	public static List<NPC> loadEntities(String folder) throws IOException, FileNotFoundException {
		File file = new File(folder + "/NPCs.json");
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
		List<NPC> results = new ArrayList<>();
		for (GameObject go : objects) {
			results.add((NPC) go);
		}
		return results;
	}

	public static UserCharacter loadCharacter(String folder) throws IOException, FileNotFoundException {
		File file = new File(folder + "/character.json");
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
		return (UserCharacter) gson.fromJson(result, GameObject.class);
	}

	public static String loadInitialMessage(String folder) throws IOException {
		File file = new File(folder + "/initialMessage.start");
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

}
