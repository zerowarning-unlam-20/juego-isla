package tools;

import com.google.gson.annotations.SerializedName;

public enum ItemType {
	@SerializedName("K")
	KEY("K"),
	@SerializedName("R")
	RESOURCE("R"),
	@SerializedName("I")
	INFORMATION("I"),
	@SerializedName("E")
	EDIBLE("E"),
	@SerializedName("W")
	WEAPON("W"),
	@SerializedName("T")
	TOOL("T"),
	@SerializedName("B")
	BLUEPRINT("B"), 
	@SerializedName("C")
	CONSUMABLE("C");

	private final String value;

	ItemType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
