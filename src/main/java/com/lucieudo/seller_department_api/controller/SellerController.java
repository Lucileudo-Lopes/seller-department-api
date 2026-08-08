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

import com.lucieudo.seller_department_api.entity.Seller;
import com.lucieudo.seller_department_api.service.SellerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sellers")
public class SellerController {

	@Autowired
	private SellerService service;

	@GetMapping
	public ResponseEntity<List<Seller>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Seller> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<Seller> save(@RequestBody @Valid Seller obj) {
		return ResponseEntity.status(201).body(service.save(obj));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Seller> update(@PathVariable Integer id, @RequestBody @Valid Seller obj) {
		obj.setId(id);
		return ResponseEntity.ok(service.save(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
