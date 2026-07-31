package com.lucieudo.seller_department_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucieudo.seller_department_api.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
