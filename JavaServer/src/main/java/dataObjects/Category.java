package dataObjects;

public class Category {
	
	private Integer id;
	private String description;
	
	public Category(Integer id, String description) {
		this.setId(id);
		this.setDescription(description);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
