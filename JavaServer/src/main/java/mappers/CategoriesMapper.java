package mappers;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;

import dataObjects.Category;

public interface CategoriesMapper {
	
	@MapKey("idCategory")
	@Select("SELECT * FROM Category")
	Map<Integer, Category> selectCategories();

}
