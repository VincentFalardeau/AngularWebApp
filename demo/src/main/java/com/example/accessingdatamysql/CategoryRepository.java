package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;
import com.example.accessingdatamysql.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{
		
}