package dataObjects;


public class MarkDataPrimitive {
	
	private int id;
	private String description;
	private float mark;
	private float weight;
	private int idCourse;
	private int idCategory;
	
	public MarkDataPrimitive() {
		
	}
	
	public MarkDataPrimitive(int id, String description, float mark, float weight, int idCourse, int idCategory) {
		this.setId(id);
		this.setDescription(description);
		this.setMark(mark);
		this.setWeight(weight);
		this.setIdCourse(idCourse);
		this.setIdCategory(idCategory);
	}
	
	public MarkDataPrimitive(MarkDataPrimitive markDataPrimitive) {
		this.setId(markDataPrimitive.getId());
		this.setDescription(markDataPrimitive.getDescription());
		this.setMark(markDataPrimitive.getMark());
		this.setWeight(markDataPrimitive.getWeight());
		this.setIdCourse(markDataPrimitive.getIdCourse());
		this.setIdCategory(markDataPrimitive.getIdCategory());
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getMark() {
		return mark;
	}
	public void setMark(float mark) {
		this.mark = mark;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public int getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(int idCourse) {
		this.idCourse = idCourse;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	

}
