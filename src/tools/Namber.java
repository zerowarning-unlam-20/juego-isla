package tools;

import com.google.gson.annotations.SerializedName;

public enum Namber {
	@SerializedName("S")
	S("S"), @SerializedName("P")
	P("P");

	private String value;

	Namber(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public void setNamber(String valor) {
		this.value = valor;
	}

}