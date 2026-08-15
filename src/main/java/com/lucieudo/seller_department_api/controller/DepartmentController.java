package com.lucieudo.seller_department_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucieudo.seller_department_api.dto.DepartmentDTO;
import com.lucieudo.seller_department_api.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService service;

	@GetMapping
	public ResponseEntity<List<DepartmentDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<DepartmentDTO> save(@RequestBody @Valid DepartmentDTO dto) {
		return ResponseEntity.status(201).body(service.save(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DepartmentDTO> update(@PathVariable Integer id, @RequestBody @Valid DepartmentDTO dto) {
		dto.setId(id);
		return ResponseEntity.ok(service.save(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
