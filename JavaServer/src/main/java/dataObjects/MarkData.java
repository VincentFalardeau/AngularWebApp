package dataObjects;

import java.math.BigDecimal;

public class MarkData {
	
	private Integer id;
	private String description;
	private BigDecimal mark;
	private BigDecimal weight;
	private Integer idCourse;
	private Integer idCategory;
	
	public MarkData(Integer id, String description, float mark, float weight, Integer idCourse, Integer idCategory) {
		this.setId(id);
		this.setDescription(description);
		this.setMark(BigDecimal.valueOf(mark));
		this.setWeight(BigDecimal.valueOf(weight));
		this.setIdCourse(idCourse);
		this.setIdCategory(idCategory);
	}
	
	//Constructor for when preparing a mark
	public MarkData(MarkWrapper mark) {
		this.setId(mark.getId());
		this.setDescription(mark.getDescription());
		this.setMark(BigDecimal.valueOf(mark.getMark()));
		this.setWeight(BigDecimal.valueOf(mark.getWeight()));
		this.setIdCourse(mark.getCourse().getId());
		this.setIdCategory(mark.getCategory().getId());
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getMark() {
		return mark;
	}
	public void setMark(BigDecimal mark) {
		this.mark = mark;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public Integer getIdCourse() {
		return idCourse;
	}
	public void setIdCourse(Integer idCourse) {
		this.idCourse = idCourse;
	}
	public Integer getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	

}
