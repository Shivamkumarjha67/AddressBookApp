package com.bridgelabz.addressbook.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.addressbook.DTO.ContactDTO;
import com.bridgelabz.addressbook.Model.Contact;
import com.bridgelabz.addressbook.Repository.AddressRepository;

@Service
public class AddressBookAppService {
	@Autowired
	private AddressRepository addressRepository;
	
	public void saveContact(ContactDTO contactDTO) {
		Contact contact = new Contact();
		contact.setFirstName(contactDTO.getFirstName());
		contact.setLastName(contactDTO.getLastName());
		contact.setEmail(contactDTO.getEmail());
		contact.setAddress(contactDTO.getAddress());
		contact.setNotes(contactDTO.getNotes());
		
		addressRepository.save(contact);
	}

	public List<Contact> getAllContacts() {
		return addressRepository.findAll();
	}

	public Contact getById(int id) {
		return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Contact id not found!"));
	}

	public Contact updateById(int id, Contact contact) {
		return addressRepository.findById(id).map(existingContact -> {
			existingContact.setFirstName(contact.getFirstName());
			existingContact.setLastName(contact.getLastName());
			existingContact.setEmail(contact.getEmail());
			existingContact.setAddress(contact.getAddress());
			existingContact.setNotes(contact.getNotes());
			
			return addressRepository.save(existingContact);
		}).orElseThrow(() -> new RuntimeException("Given contact id not found!"));
	}

	public String deleteById(int id) {
		if(addressRepository.existsById(id)) {
			addressRepository.deleteById(id);
			return "Contact deleted successfully!";
		} 
		
		throw new RuntimeException("Contact not found");
	}
}
