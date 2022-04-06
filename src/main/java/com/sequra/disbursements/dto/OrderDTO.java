package com.sequra.disbursements.dto;

public class OrderDTO {

    private String id;
    private String merchant_id;
    private String shopper_id;
    private String amount;
    private String createdAt;
    private String completedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getShopper_id() {
        return shopper_id;
    }

    public void setShopper_id(String shopper_id) {
        this.shopper_id = shopper_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }
}
