package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WordBuilder {
	HashMap<String, String> words;

	public WordBuilder(String path) throws IOException {
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

		Gson gson = new Gson();
		words = gson.fromJson(result, new TypeToken<HashMap<String, String>>() {
		}.getType());
	}

	public String getWord(String from) {
		return words.get(from);
	}
}
