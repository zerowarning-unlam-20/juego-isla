package tools;

import com.google.gson.annotations.SerializedName;

public enum Gender {
	@SerializedName("M")
	M("M"), @SerializedName("F")
	F("F");

	private final String value;
	private final String singular;
	private final String plural;
	private final String only;
	private final String gender;
	private final String termination;
	private final String location;

	Gender(String value) {
		this.value = value;
		singular = (value == "M") ? "el" : "la";
		plural = (value == "M") ? "los" : "las";
		only = (value == "M") ? "un" : "una";
		gender = (value == "M") ? "el" : "ella";
		termination = (value == "M") ? "o" : "a";
		location = (value == "M") ? "l" : " la";
	}

	public String getSingular() {
		return singular;
	}

	public String getPlural() {
		return plural;
	}

	public String getOnly() {
		return only;
	}

	public String getGender() {
		return gender;
	}

	public String getValue() {
		return value;
	}

	public String getTermination() {
		return termination;
	}

	public String getLocation() {
		return location;
	}
}