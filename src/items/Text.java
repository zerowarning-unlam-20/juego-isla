package items;

import tools.Gender;
import tools.ItemType;

public class Text extends Item {
	private String content;

	public Text(int id, Gender gender, String name, String description, String content) {
		super(id, gender, name, description, ItemType.INFORMATION);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
