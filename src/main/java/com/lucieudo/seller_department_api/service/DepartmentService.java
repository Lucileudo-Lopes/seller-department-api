package com.lucieudo.seller_department_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucieudo.seller_department_api.entity.Department;
import com.lucieudo.seller_department_api.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;
	
	
	public List<Department> findAll(){
		return repository.findAll();

	}
	
	public Department findById(Integer id) {
		return repository.findById(id).orElseThrow(()-> new RuntimeException("Department not found with id" + id));
	}
	
	public Department save(Department obj) {
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
}

