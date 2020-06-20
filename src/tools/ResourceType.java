package tools;

import com.google.gson.annotations.SerializedName;

public enum ResourceType {
	@SerializedName("wood")
	WOOD("wood"), 
	@SerializedName("rope")
	ROPE("rope"), 
	@SerializedName("stone")
	STONE("stone");

	private final String value;

	ResourceType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}