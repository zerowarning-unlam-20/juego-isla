package tools;

import com.google.gson.annotations.SerializedName;

public enum DamageType {
	@SerializedName("h")
	HACK("H"),
	@SerializedName("s")
	SLASH("S"),
	@SerializedName("b")
	BLUNT("B"),
	@SerializedName("s")
	SHOT("S");

	private final String value;

	DamageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
