package com.carlease.quotation.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "quotations")
public class Quotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Long vehicleId;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monthlyLeaseAmount;

    @Column(nullable = false)
    private Integer leaseTenureMonths;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal downPayment;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalLeaseAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private QuotationStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();

        if (status == null) {
            status = QuotationStatus.GENERATED;
        }
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public BigDecimal getMonthlyLeaseAmount() {
        return monthlyLeaseAmount;
    }

    public void setMonthlyLeaseAmount(BigDecimal monthlyLeaseAmount) {
        this.monthlyLeaseAmount = monthlyLeaseAmount;
    }

    public Integer getLeaseTenureMonths() {
        return leaseTenureMonths;
    }

    public void setLeaseTenureMonths(Integer leaseTenureMonths) {
        this.leaseTenureMonths = leaseTenureMonths;
    }

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    public BigDecimal getTotalLeaseAmount() {
        return totalLeaseAmount;
    }

    public void setTotalLeaseAmount(BigDecimal totalLeaseAmount) {
        this.totalLeaseAmount = totalLeaseAmount;
    }

    public QuotationStatus getStatus() {
        return status;
    }

    public void setStatus(QuotationStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
