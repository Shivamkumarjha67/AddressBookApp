package com.bridgelabz.addressbook.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

// Added the getter and setter in DTO using Lombok library
@Getter
@Setter
public class ContactDTO {
	private int id;
	@NotEmpty(message = "First name field can not left empty.")
	@Pattern(regexp = "[A-Z][a-z\\s]{3,}$", message = "Invalid first name.")
	private String firstName;
	@NotEmpty(message = "Last name field can not left empty.")
	@Pattern(regexp = "[A-Z][a-z\\s]{3,}$", message = "Invalid last name.")
	private String lastName;
	private String address;
	private String email;
	private String notes;
}
