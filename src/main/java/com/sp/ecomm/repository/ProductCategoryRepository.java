package com.sp.ecomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sp.ecomm.entity.ProductCategory;

@Repository
public interface ProductCategoryRepository 
        extends JpaRepository<ProductCategory, Integer> {
 
}
