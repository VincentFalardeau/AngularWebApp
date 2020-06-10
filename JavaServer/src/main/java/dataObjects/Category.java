package dataObjects;

public class Category {
	
	private int idCategory;
	private String description;
	
	public Category(int idCategory, String description) {
		this.setIdCategory(idCategory);
		this.setDescription(description);
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
