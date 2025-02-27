package com.bridgelabz.addressbook.Container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.addressbook.Model.Contact;
import com.bridgelabz.addressbook.Service.AddressBookAppService;

@RestController
@RequestMapping("/contact")
public class AddressBookController {
	@Autowired
	private AddressBookAppService addressBookAppService;
	
	@PostMapping("/add")
	public ResponseEntity<Contact> getContactSaved(@RequestBody Contact contact) {
	    System.out.println("Received Contact: " + contact);
	    Contact savedContact = addressBookAppService.saveContact(contact);
	    return ResponseEntity.ok(savedContact);
	}

}