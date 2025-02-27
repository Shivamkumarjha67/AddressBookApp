package com.bridgelabz.addressbook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.addressbook.Model.Contact;

public interface AddressRepository extends JpaRepository<Contact, Integer> {

}
