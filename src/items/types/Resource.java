package items.types;

import items.Item;
import items.properties.Holdable;
import tools.Gender;
import tools.ResourceType;

public class Resource extends Item implements Holdable {
	private ResourceType resourceType;

	public Resource(Gender gender, String name, String description, ResourceType resourceType) {
		super(gender, name, description);
		this.resourceType = resourceType;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

}
