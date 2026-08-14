package com.lucieudo.seller_department_api.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO {

	private Integer id;

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Email must be valid")
	private String email;

	@NotNull(message = "Birth date is required")
	private LocalDate birthDate;

	@NotNull(message = "Base salary is required")
	private Double baseSalary;

	@NotNull(message = "Department is required")
	private Integer departmentId;

	private String departmentName;
}
