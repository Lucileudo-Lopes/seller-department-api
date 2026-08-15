package com.lucieudo.seller_department_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucieudo.seller_department_api.dto.DepartmentDTO;
import com.lucieudo.seller_department_api.entity.Department;
import com.lucieudo.seller_department_api.exception.ResourceNotFoundException;
import com.lucieudo.seller_department_api.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;

	public List<DepartmentDTO> findAll() {
		return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());

	}

	public DepartmentDTO findById(Integer id) {
		Department entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
		return toDTO(entity);
	}

	public DepartmentDTO save(DepartmentDTO dto) {
		Department entity = toEntity(dto);
		return toDTO(repository.save(entity));
	}

	public void delete(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
		repository.deleteById(id);

	}

	private DepartmentDTO toDTO(Department entity) {
		return new DepartmentDTO(entity.getId(), entity.getName());
	}

	private Department toEntity(DepartmentDTO dto) {
		return new Department(dto.getId(), dto.getName());
	}

}
