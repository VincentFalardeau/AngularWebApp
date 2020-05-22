package dataObjects;

public class Category {
	private int id;
	private String description;
	
	public Category(int id, String description) {
		this.setId(id);
		this.setDescription(description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
