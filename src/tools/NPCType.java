package tools;

import com.google.gson.annotations.SerializedName;

public enum NPCType {
	@SerializedName("passive")
	PASSIVE("passive"), 
	
	@SerializedName("aggressive")
	AGGRESSIVE("aggresive"),
	
	@SerializedName("inanimated")
	INANIMATED("inanimated");

	private final String value;

	NPCType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
