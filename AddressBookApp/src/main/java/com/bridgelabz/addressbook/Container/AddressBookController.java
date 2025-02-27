package com.bridgelabz.addressbook.Container;

import java.util.ArrayList;
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
import com.bridgelabz.addressbook.Service.AddressBookAppService;

@RestController
@RequestMapping("/contact")
public class AddressBookController {
	// Used the Autowired annotation for the dependency injection
	@Autowired
	private AddressBookAppService addressBookAppService;
	
	List<ContactDTO> allContactsContactDTOs = new ArrayList<>();
	
	@PostMapping("/add")
	public ResponseEntity<String> getContactSaved(@RequestBody ContactDTO contact) {
	    try {
	    	 addressBookAppService.saveContact(contact);
	    	 allContactsContactDTOs.add(contact);
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
	public ResponseEntity<ContactDTO> getById(@PathVariable int id) {
		try {
			ContactDTO contactDTO = addressBookAppService.getById(id);
			return ResponseEntity.ok(contactDTO);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateById(@PathVariable int id, @RequestBody ContactDTO contactDTO) {
		try {
			String message = addressBookAppService.updateById(id, contactDTO); 
			
			for(int i=0; i<allContactsContactDTOs.size(); i++) {
				if(allContactsContactDTOs.get(i).getId() == id) {
					allContactsContactDTOs.set(i, contactDTO);
				}
			}
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);
		} catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}") 
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		try {
			for(int i=0; i<allContactsContactDTOs.size(); i++) {
				if(allContactsContactDTOs.get(i).getId() == id) {
					allContactsContactDTOs.remove(i);
				}
			}
			
			return ResponseEntity.ok(addressBookAppService.deleteById(id));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}