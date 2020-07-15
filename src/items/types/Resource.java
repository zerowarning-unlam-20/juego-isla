package items.types;

import items.Item;
import items.properties.Holdable;
import tools.Gender;
import tools.ResourceType;

public class Resource extends Item implements Holdable {
	private ResourceType resourceType;
	private int quantity;

	public Resource(Gender gender, String name, String description, int price, ResourceType resourceType,
			int quantity) {
		super(gender, name, description, price);
		this.resourceType = resourceType;
		this.quantity = quantity;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
