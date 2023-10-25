package com.kutluay.ProductManagment.repository;

import com.kutluay.ProductManagment.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {

}
