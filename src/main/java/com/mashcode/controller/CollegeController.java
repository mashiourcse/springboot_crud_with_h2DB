package com.mashcode.controller;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mashcode.entity.College;
import com.mashcode.repository.CollegeRepository;

@RestController
@RequestMapping("/college")
public class CollegeController {

	@Autowired
	CollegeRepository collegeRepository;
	
	@PostMapping("/save")
	public String saveCollege(@RequestBody College college) {
		
		collegeRepository.save(college);
		return college.toString() + " Data successfully inserted";
		
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<College>> getCollege(){
		
		List<College> collegeList = new ArrayList<College>();
		collegeRepository.findAll().forEach(collegeList::add);
		return new ResponseEntity<List<College>>(collegeList, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCollegeById( @PathVariable("id") long id) {
		
		collegeRepository.deleteById(id);
		
		return "College record with "+ id + " deleted successfully...." ;
		
	}
	
	@PutMapping("/put/{id}")
	public String updateCollegeById( @PathVariable("id") long id, @RequestBody College college) {
		
		Optional<College> collegeOptional = collegeRepository.findById(id);
		
		if(collegeOptional.isPresent()) {
			College clg = collegeOptional.get();
			college.setId(id);
			collegeRepository.save(college);
			return "College record with "+ id + " updated successfully....";
		}
		return "College record doesn't exist!" ;
		
	}
}
