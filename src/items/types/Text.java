package items.types;

import items.Item;
import items.properties.Holdable;
import items.properties.Readablel;
import tools.Gender;

public class Text extends Item implements Holdable, Readablel {
	private String content;

	public Text(Gender gender, String name, String description, String content) {
		super(gender, name, description);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String read(boolean visible) {
		return visible ? content : "No se ve nada";
	}

}
