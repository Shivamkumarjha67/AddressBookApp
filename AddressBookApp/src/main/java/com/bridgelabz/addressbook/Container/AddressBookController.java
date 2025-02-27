package com.bridgelabz.addressbook.Container;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.addressbook.DTO.ContactDTO;
import com.bridgelabz.addressbook.Model.Contact;
import com.bridgelabz.addressbook.Service.AddressBookAppService;

@RestController
@RequestMapping("/contact")
public class AddressBookController {
	// Used the autowired annotation for the dependency injection
	@Autowired
	private AddressBookAppService addressBookAppService;
	
	@PostMapping("/add")
	public ResponseEntity<String> getContactSaved(@RequestBody ContactDTO contact) {
	    System.out.println("Received Contact:");
	    System.out.println("First Name: " + contact.getFirstName());
	    System.out.println("Last Name: " + contact.getLastName());
	    System.out.println("Address: " + contact.getAddress());
	    System.out.println("Email: " + contact.getEmail());
	    System.out.println("Notes: " + contact.getNotes());
	    
	    try {
	    	 addressBookAppService.saveContact(contact);
	    	 return ResponseEntity.status(HttpStatus.CREATED).body("Contact created.");
	    } catch (Exception e) {
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("some error occured!");
	    }
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<ContactDTO>> getAllContacts() {
		List<ContactDTO> contacts = addressBookAppService.getAllContacts();
		return ResponseEntity.ok(contacts);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Contact> getById(@PathVariable int id) {
		try {
			Contact contact = addressBookAppService.getById(id);
			return ResponseEntity.ok(contact);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Contact> updateById(@PathVariable int id, @RequestBody Contact contact) {
		Contact updatedContact = addressBookAppService.updateById(id, contact);
		return ResponseEntity.ok(updatedContact);
	}
	
	@DeleteMapping("/delete/{id}") 
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		try {
			return ResponseEntity.ok(addressBookAppService.deleteById(id));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}