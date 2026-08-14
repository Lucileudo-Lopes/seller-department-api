package com.lucieudo.seller_department_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucieudo.seller_department_api.entity.Seller;
import com.lucieudo.seller_department_api.exception.ResourceNotFoundException;
import com.lucieudo.seller_department_api.repository.SellerRepository;

@Service
public class SellerService {

	@Autowired
	private SellerRepository repository;

	public List<Seller> findAll() {
		return repository.findAll();
	}

	public Seller findById(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Seller not found with id: " + id));

	}

	public Seller save(Seller obj) {
		return repository.save(obj);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
