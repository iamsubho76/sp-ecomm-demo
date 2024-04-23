package com.sp.ecomm.repository;

import com.sp.ecomm.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository 
        extends JpaRepository<Brand, Integer> {
 
}
