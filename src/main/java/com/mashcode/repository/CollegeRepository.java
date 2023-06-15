package com.mashcode.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mashcode.entity.College;

@Repository
public interface CollegeRepository extends CrudRepository<College, Long>{
	
	
}
