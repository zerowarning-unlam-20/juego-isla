package items.types;

import items.Item;
import items.properties.Holdable;
import items.properties.Readablel;
import tools.Gender;
import tools.Namber;

public class Text extends Item implements Holdable, Readablel {
	private String content;

	public Text(Gender gender, Namber number, String name, String description, String content, int price) {
		super(gender, number, name, description, price);
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
