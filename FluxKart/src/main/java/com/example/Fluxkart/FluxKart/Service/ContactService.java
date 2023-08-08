package com.example.Fluxkart.FluxKart.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Fluxkart.FluxKart.Repo.ContactRepo;
import com.example.Fluxkart.FluxKart.entity.Contact;
import com.example.Fluxkart.FluxKart.entity.ContactRequest;
import com.example.Fluxkart.FluxKart.entity.ContactResponse;
import com.example.Fluxkart.FluxKart.entity.LinkPrecedence;

@Service
public class ContactService {

	
	@Autowired
	private ContactRepo contactRepository;
	
	public ContactResponse identifyContact(ContactRequest request) {
		  String email = request.getEmail();
	        String phoneNumber = request.getPhoneNumber();
	        
	     // Retrieve existing contacts based on email or phoneNumber
	        List<Contact> existingContacts = contactRepository.findByEmailOrPhoneNumber(email, phoneNumber);

	        if (!existingContacts.isEmpty()) {
	        	  // Update existing contacts' linkPrecedence to PRIMARY and SECONDARY
	            Contact primaryContact = existingContacts.get(0);
	            primaryContact.setLinkPrecedence(LinkPrecedence.PRIMARY);
	            contactRepository.save(primaryContact);

	            for (int i = 1; i < existingContacts.size(); i++) {
	                Contact secondaryContact = existingContacts.get(i);
	                secondaryContact.setLinkPrecedence(LinkPrecedence.SECONDARY);
	                contactRepository.save(secondaryContact);
	            }
	         // Generate response for existing contacts
	            return getContactResponse(primaryContact, existingContacts);
	        } else {
	        	// Create a new contact if no existing contacts are found
	            Contact newContact = new Contact();
	            newContact.setEmail(email);
	            newContact.setPhoneNumber(phoneNumber);
	            newContact.setLinkPrecedence(LinkPrecedence.PRIMARY);
	            newContact.setCreatedAt(LocalDateTime.now());
	            newContact.setUpdatedAt(LocalDateTime.now());

	            contactRepository.save(newContact);
	            // Generate response for the newly created contact
	            return getContactResponse(newContact, new ArrayList<>());
	        }
	    }

	    private ContactResponse getContactResponse(Contact primaryContact, List<Contact> secondaryContacts) {
	        ContactResponse response = new ContactResponse();
	        response.setPrimaryContactId(primaryContact.getId());


	        Set<String> emails = new HashSet<>();
	        Set<String> phoneNumbers = new HashSet<>();
	        Set<Integer> secondaryContactIds = new HashSet<>();
	        
	        // Add primary contact's email and phoneNumber to the sets
	        emails.add(primaryContact.getEmail());
	        phoneNumbers.add(primaryContact.getPhoneNumber());

	        // Add secondary contacts' information to the sets
	        for (Contact secondaryContact : secondaryContacts) {
	            secondaryContactIds.add(secondaryContact.getId());
	            emails.add(secondaryContact.getEmail());
	            phoneNumbers.add(secondaryContact.getPhoneNumber());
	        }
	     // Set the response attributes
	        response.setEmails(emails);
	        response.setPhoneNumbers(phoneNumbers);
	        response.setSecondaryContactIds(secondaryContactIds);

	        return response;
	    }
	
	}
