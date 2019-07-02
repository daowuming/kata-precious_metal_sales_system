package com.coding.sales.output;

import java.math.BigDecimal;

public class DiscountItemRepresentation {
    private String productNo;
    private String productName;
    private BigDecimal actualPayment;
    private BigDecimal discount;

    /**
     * 销售凭证中的优惠项
     * @param productNo 产品编号
     * @param productName 产品名称
     * @param actualPayment 优惠后金额
     * @param discount 优惠金额
     */
    public DiscountItemRepresentation(String productNo, String productName,BigDecimal actualPayment, BigDecimal discount) {
        this.productNo = productNo;
        this.productName = productName;
        this.actualPayment = actualPayment == null ? BigDecimal.ZERO : actualPayment;
        this.discount = discount == null ? BigDecimal.ZERO : discount;
    }

    public String getProductNo() {
        return productNo;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getActualPayment() {
        return actualPayment;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscountItemRepresentation that = (DiscountItemRepresentation) o;

        if (!productNo.equals(that.productNo)) return false;
        if (!productName.equals(that.productName)) return false;
        if (actualPayment.compareTo(that.actualPayment) != 0){
            return false;
        }
        return discount.compareTo(that.discount) == 0;
    }

    @Override
    public int hashCode() {
        int result = productNo.hashCode();
        result = 31 * result + productName.hashCode();
        result = 31 * result + actualPayment.hashCode();
        result = 31 * result + discount.hashCode();
        return result;
    }
}
