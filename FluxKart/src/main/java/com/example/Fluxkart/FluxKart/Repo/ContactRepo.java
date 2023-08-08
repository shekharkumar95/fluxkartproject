package com.example.Fluxkart.FluxKart.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Fluxkart.FluxKart.entity.Contact;



@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer> {

	// Retrieve existing contacts based on email or phoneNumber
	List<Contact> findByEmailOrPhoneNumber(String email, String phoneNumber);
	
	
}
