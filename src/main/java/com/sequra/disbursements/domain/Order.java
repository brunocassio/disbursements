package com.sequra.disbursements.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Merchant merchant;
    @ManyToOne
    private Shopper shopper;
    private Double amount;
    private Date createdAt;
    private Date completedAt;
    private Double disbursement;

    public Order(Long id, Merchant merchant_id, Shopper shopper_id, Double amount, Date createdAt, Date completedAt) {
        this.id = id;
        this.merchant = merchant_id;
        this.shopper = shopper_id;
        this.amount = amount;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Shopper getShopper() {
        return shopper;
    }

    public void setShopper(Shopper shopper) {
        this.shopper = shopper;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

    public Double getDisbursement() {
        return disbursement;
    }

    public void setDisbursement(Double disbursement) {
        this.disbursement = disbursement;
    }
}
