package com.coding.sales.enity;

import java.math.BigDecimal;
import java.util.List;

public class MetalModels {
    String productName;
    String productNo;
    String unit;
    BigDecimal productPrice;
    String discountCards;
    String fullReduction;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getDiscountCards() {
        return discountCards;
    }

    public void setDiscountCards(String discountCards) {
        this.discountCards = discountCards;
    }

    public String getFullReduction() {
        return fullReduction;
    }

    public void setFullReduction(String fullReduction) {
        this.fullReduction = fullReduction;
    }
}
