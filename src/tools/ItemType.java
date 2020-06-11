package tools;

import com.google.gson.annotations.SerializedName;

public enum ItemType {
	@SerializedName("k")
	KEY("K"),
	@SerializedName("r")
	RESOURCE("R"),
	@SerializedName("i")
	INFORMATION("I"),
	@SerializedName("e")
	EDIBLE("E"),
	@SerializedName("w")
	WEAPON("W"),
	@SerializedName("t")
	TOOL("T"),
	@SerializedName("b")
	BLUEPRINT("B");

	private final String value;

	ItemType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
