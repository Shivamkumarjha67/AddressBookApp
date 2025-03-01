package com.bridgelabz.addressbook.DTO;

import lombok.Getter;
import lombok.Setter;

// Added the getter and setter in DTO using Lombok library
@Getter
@Setter
public class ContactDTO {
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String notes;
}
