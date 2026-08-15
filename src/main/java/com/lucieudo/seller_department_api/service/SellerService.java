package com.lucieudo.seller_department_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucieudo.seller_department_api.dto.SellerDTO;
import com.lucieudo.seller_department_api.entity.Department;
import com.lucieudo.seller_department_api.entity.Seller;
import com.lucieudo.seller_department_api.exception.ResourceNotFoundException;
import com.lucieudo.seller_department_api.repository.DepartmentRepository;
import com.lucieudo.seller_department_api.repository.SellerRepository;

@Service
public class SellerService {

	@Autowired
	private SellerRepository repository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<SellerDTO> findAll() {
		return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	public SellerDTO findById(Integer id) {
		Seller entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Seller not found with id: " + id));
		return toDTO(entity);
	}

	public SellerDTO save(SellerDTO dto) {
		Seller entity = toEntity(dto);
		return toDTO(repository.save(entity));
	}

	public void delete(Integer id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Seller not found with id: " + id));
		repository.deleteById(id);
	}

	private SellerDTO toDTO(Seller entity) {
		return new SellerDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getBirthDate(),
				entity.getBaseSalary(), entity.getDepartment() != null ? entity.getDepartment().getId() : null,
				entity.getDepartment() != null ? entity.getDepartment().getName() : null);
	}

	private Seller toEntity(SellerDTO dto) {
		Department department = departmentRepository.findById(dto.getDepartmentId()).orElseThrow(
				() -> new ResourceNotFoundException("Department not found with id: " + dto.getDepartmentId()));
		return new Seller(dto.getId(), dto.getName(), dto.getEmail(), dto.getBirthDate(), dto.getBaseSalary(),
				department);
	}
}