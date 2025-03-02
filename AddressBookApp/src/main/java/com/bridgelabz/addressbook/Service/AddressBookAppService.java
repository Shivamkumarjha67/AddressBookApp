package com.bridgelabz.addressbook.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.addressbook.DTO.ContactDTO;
import com.bridgelabz.addressbook.Exception.AddressBookIdNotFoundException;
import com.bridgelabz.addressbook.Model.Contact;
import com.bridgelabz.addressbook.Repository.AddressRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
		
		log.info("Contact is added to address book, for {} {}.", contactDTO.getFirstName(), contactDTO.getLastName());
		
		addressRepository.save(contact);
	}

	public List<ContactDTO> getAllContacts() {
		List<Contact> contacts =  addressRepository.findAll();
		
		log.info("Name of people whose details are saved in the address book are: ");
		List<ContactDTO> contactDTOs  = new ArrayList<>();
		for(Contact contact : contacts) {
			ContactDTO contactDTO = new ContactDTO();
			
			contactDTO.setFirstName(contact.getFirstName());
			contactDTO.setLastName(contact.getLastName());
			contactDTO.setAddress(contact.getAddress());
			contactDTO.setEmail(contact.getEmail());
			contactDTO.setNotes(contact.getNotes());
			
			log.info("Name is : {}.", contact.getFirstName());
			
			contactDTOs.add(contactDTO);
		}
		
		return contactDTOs;
	}

	public ContactDTO getById(int id) throws AddressBookIdNotFoundException {
		Contact contact =  addressRepository.findById(id).orElseThrow(() -> new AddressBookIdNotFoundException("Id not found!"));
				
		ContactDTO contactDTO = new ContactDTO();
		contactDTO.setAddress(contact.getAddress());
		contactDTO.setEmail(contact.getEmail());
		contactDTO.setFirstName(contact.getFirstName());
		contactDTO.setLastName(contact.getLastName());
		contactDTO.setNotes(contact.getNotes());
		
		log.info("Name of people in {} id is {}.", id, contact.getFirstName());
		
		return contactDTO;
	}

	public String deleteById(int id) {
		if(addressRepository.existsById(id)) {
			addressRepository.deleteById(id);
			return "Contact deleted successfully!";
		} 
		
		throw new RuntimeException("Contact not found");
	}

	public String updateById(int id, ContactDTO contactDTO) {
		Contact existingContact = addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Given contact id not found!"));
		
		existingContact.setFirstName(contactDTO.getFirstName());
		existingContact.setLastName(contactDTO.getLastName());
		existingContact.setEmail(contactDTO.getEmail());
		existingContact.setAddress(contactDTO.getAddress());
		existingContact.setNotes(contactDTO.getNotes());
		
		addressRepository.save(existingContact);
		return "Updated successfully...";
	}
}
