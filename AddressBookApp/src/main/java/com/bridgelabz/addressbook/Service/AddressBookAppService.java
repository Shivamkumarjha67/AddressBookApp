package com.bridgelabz.addressbook.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.addressbook.Model.Contact;
import com.bridgelabz.addressbook.Repository.AddressRepository;

@Service
public class AddressBookAppService {
	@Autowired
	private AddressRepository addressRepository;
	
	public Contact saveContact(Contact contact) {
		return addressRepository.save(contact);
	}
}
