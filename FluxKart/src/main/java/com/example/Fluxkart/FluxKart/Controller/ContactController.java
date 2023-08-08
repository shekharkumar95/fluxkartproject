package com.example.Fluxkart.FluxKart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Fluxkart.FluxKart.Service.ContactService;
import com.example.Fluxkart.FluxKart.entity.ContactRequest;
import com.example.Fluxkart.FluxKart.entity.ContactResponse;

@RestController()
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping("/identify")
	public ResponseEntity<ContactResponse> handelindentify( @RequestBody ContactRequest contactRequest )
	{
		// Call the contactService to identify and process the contact
		ContactResponse response =contactService.identifyContact(contactRequest);
		return ResponseEntity.ok(response);
	
	}
}