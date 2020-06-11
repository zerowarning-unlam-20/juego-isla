package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class WordBuilder {
	HashMap<String, String> words;

	public WordBuilder(String path) {
		words = new HashMap<>();
		if (path != null && !path.isEmpty()) {
			File file = new File(path);
			Scanner reader = null;
			try {
				reader = new Scanner(file);
				while (reader.hasNext()) {
					words.put(reader.next(), reader.next());
				}
				reader.close();
			} catch (FileNotFoundException e) {
				System.out.println("File input error: " + e.getMessage());
			}
		}
	}

	public String getWord(String from) {
		return words.get(from);
	}
}
