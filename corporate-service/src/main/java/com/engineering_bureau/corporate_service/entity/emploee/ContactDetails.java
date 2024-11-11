package com.engineering_bureau.corporate_service.entity.emploee;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contact_details")
public class ContactDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String telegram;

    @Column(name = "personal_phone")
    private String personalPhone;

    @Column(name = "corporate_phone")
    private String corporatePhone;

}
