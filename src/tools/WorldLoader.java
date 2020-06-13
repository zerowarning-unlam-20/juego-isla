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
import java.util.Arrays;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entities.Entity;
import island.GameObject;
import island.Location;

public class WorldLoader {
	private List<Location> locations;
	private List<Entity> entities;

	public WorldLoader() {
	}

	public void loadLocations(String path) throws IOException, FileNotFoundException {
		File file = new File(path);
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
		gsonBuilder.registerTypeAdapter(GameObject.class, new GameObjectDeserializer<GameObject>());
		Gson gson = gsonBuilder.create();
		locations = Arrays.asList(gson.fromJson(result, Location[].class));
	}

	public void loadEntities() throws IOException, FileNotFoundException {
		File file = new File("entities.json");
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
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		gsonBuilder.registerTypeAdapter(Entity.class, new GameObjectDeserializer<GameObject>());
		Gson gson = gsonBuilder.create();
		locations = Arrays.asList(gson.fromJson(result, Location[].class));
	}
	
	public List<Location> getLocations() {
		return locations;
	}

	public List<Entity> getEntities() {
		return entities;
	}
	
	
}

/*
 * File file = new File(".json"); InputStream is; try { is = new
 * FileInputStream(file);
 * 
 * Writer writer = new StringWriter(); char[] buffer = new char[1024];
 * 
 * Reader reader = new BufferedReader(new InputStreamReader(is)); int n; while
 * ((n = reader.read(buffer)) != -1) { writer.write(buffer, 0, n); }
 * 
 * String result = writer.toString();
 * 
 * JsonArray jsonObject = new JsonArray(); jsonObject.add(result); Gson gson =
 * new Gson(); List<Location> locations = Arrays.asList(gson.fromJson(result,
 * Location[].class)); this.locations = locations; } catch
 * (FileNotFoundException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
 * block e.printStackTrace(); }
 */
