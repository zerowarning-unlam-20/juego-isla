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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameObject other = (GameObject) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (gender != other.gender)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
