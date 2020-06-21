package manager;

import java.util.HashMap;

public class WordManager {
	private static HashMap<String, String> synonyms = new HashMap<>();

	public static HashMap<String, String> getSynonyms() {
		return synonyms;
	}

	public static void addSynonym(String key, String value) {
		synonyms.put(key, value);
	}

	public static String getSynonym(String key) {
		return synonyms.get(key);
	}

}
