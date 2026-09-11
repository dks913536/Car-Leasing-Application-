package com.carlease.corporate.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
        name = "corporate_customers",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "corporate_company_name",
                        columnNames = "company_name"
                ),
                @UniqueConstraint(
                        name = "corporate_email",
                        columnNames = "contact_email"
                ),
                @UniqueConstraint(
                        name = "corporate_gst",
                        columnNames = "gst_number"
                )
        }
)
public class CorporateCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "company_name",
            nullable = false,
            length = 150
    )
    private String companyName;

    @Column(
            name = "contact_person",
            nullable = false,
            length = 100
    )
    private String contactPerson;

    @Column(
            name = "contact_email",
            nullable = false,
            length = 150
    )
    private String contactEmail;

    @Column(
            name = "contact_phone",
            nullable = false,
            length = 20
    )
    private String contactPhone;

    @Column(
            name = "gst_number",
            nullable = false,
            length = 20
    )
    private String gstNumber;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false, length = 10)
    private String pincode;

    @Column(nullable = false)
    private Integer fleetSize;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal annualTurnover;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CorporateStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();

        if (status == null) {
            status = CorporateStatus.ACTIVE;
        }
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Integer getFleetSize() {
        return fleetSize;
    }

    public void setFleetSize(Integer fleetSize) {
        this.fleetSize = fleetSize;
    }

    public BigDecimal getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(BigDecimal annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public CorporateStatus getStatus() {
        return status;
    }

    public void setStatus(CorporateStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
