package com.engineering_bureau.corporate_service.entity.emploee;

import com.engineering_bureau.corporate_service.enumeration.PassportType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "passport_details")
public class PassportDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String series;

    private String number;

    private String address;

    @Enumerated(EnumType.STRING)
    private PassportType passportType;
}
