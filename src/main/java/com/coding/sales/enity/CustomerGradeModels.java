package com.coding.sales.enity;

import java.math.BigDecimal;

public class CustomerGradeModels {
    String memberType;
    BigDecimal basicValue;
    BigDecimal minValue;
    BigDecimal maxValue;

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public BigDecimal getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(BigDecimal basicValue) {
        this.basicValue = basicValue;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }
}
