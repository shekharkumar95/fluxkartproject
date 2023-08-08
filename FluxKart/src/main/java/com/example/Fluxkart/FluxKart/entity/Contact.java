package com.example.Fluxkart.FluxKart.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "contact")
public class Contact {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 	@Column(name = "id")
	    private Integer id;

		@Column(name = "phonenumber")
	    private String phoneNumber;
	    
		@Column(name = "email")
	    private String email;

		 // Another contact linked to this contact
		@Column(name = "linkedcontact")
	    private Integer  linkedContact;
		
		// Indicates whether this contact is primary or secondary
	    @Enumerated(EnumType.STRING)
		@Column(name = "linkprecedence")
	    private LinkPrecedence linkPrecedence;

	    // Timestamp when this contact was created
		@Column(name = "createdat")
	    private LocalDateTime createdAt;
	    
		// Timestamp when this contact was last updated
		@Column(name = "updatedat")
	    private LocalDateTime updatedAt;
	    
		// Timestamp when this contact was soft-deleted
		@Column(name = "deletedat")
	    private LocalDateTime deletedAt;
	
}
