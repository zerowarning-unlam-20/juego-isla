package island;

import tools.Gender;

public abstract class GameObject {
	protected int id;
	protected Gender gender;
	protected String name;
	protected String description;
	
	public GameObject(int id, Gender gender, String name, String description) {
		this.id = id;
		this.gender = gender;
		this.name = name;
		this.description = description;
	}

	public GameObject() {
		name = "";
		description = "";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSingularName() {
		return gender.getSingular() + " " + name;
	}

	public String getOnlyName() {
		return gender.getOnly() + " " + name;
	}

	public String getTermination() {
		return gender.getTermination();
	}

	public String getLocationPrefix() {
		return gender.getLocation();
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return description;
	}
}
