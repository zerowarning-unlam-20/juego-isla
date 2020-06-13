package tools;

import com.google.gson.annotations.SerializedName;

public enum NPCType {
	@SerializedName("passive")
	PASSIVE("P"), 
	
	@SerializedName("aggressive")
	AGGRESSIVE("A"),
	
	@SerializedName("inanimated")
	INANIMATED("I");

	private final String value;

	NPCType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
