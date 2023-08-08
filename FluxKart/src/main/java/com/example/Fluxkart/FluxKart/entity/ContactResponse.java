package com.example.Fluxkart.FluxKart.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ContactResponse {
	
	private Integer primaryContactId;
	
	
	private Set<String> emails ;
 private    Set<String> phoneNumbers ;
   private   Set<Integer> secondaryContactIds;
	

}
