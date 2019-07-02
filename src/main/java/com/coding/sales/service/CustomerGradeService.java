package com.coding.sales.service;

import com.coding.sales.enity.CustomerGradeModels;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerGradeService {
    List<CustomerGradeModels> custoemrGrades = new ArrayList<CustomerGradeModels>();

    {
        CustomerGradeModels customerGradeOne = new CustomerGradeModels();
        customerGradeOne.setMemberType("普卡");
        customerGradeOne.setBasicValue(BigDecimal.valueOf(1));
        customerGradeOne.setMinValue(BigDecimal.valueOf(0));
        customerGradeOne.setMaxValue(BigDecimal.valueOf(10000));
        custoemrGrades.add(customerGradeOne);
        CustomerGradeModels customerGradeTwo = new CustomerGradeModels();
        customerGradeTwo.setMemberType("金卡");
        customerGradeTwo.setBasicValue(BigDecimal.valueOf(1.5));
        customerGradeTwo.setMinValue(BigDecimal.valueOf(10000));
        customerGradeTwo.setMaxValue(BigDecimal.valueOf(50000));
        custoemrGrades.add(customerGradeTwo);
        CustomerGradeModels customerGradeThree = new CustomerGradeModels();
        customerGradeThree.setMemberType("白金卡");
        customerGradeThree.setBasicValue(BigDecimal.valueOf(1.8));
        customerGradeThree.setMinValue(BigDecimal.valueOf(50000));
        customerGradeThree.setMaxValue(BigDecimal.valueOf(100000));
        custoemrGrades.add(customerGradeThree);
        CustomerGradeModels customerGradeFour = new CustomerGradeModels();
        customerGradeFour.setMemberType("钻石卡");
        customerGradeFour.setBasicValue(BigDecimal.valueOf(2));
        customerGradeFour.setMinValue(BigDecimal.valueOf(100000));
        custoemrGrades.add(customerGradeFour);
    }

    public List<CustomerGradeModels> getCustoemrGrades() {
        return custoemrGrades;
    }

    public CustomerGradeModels getCustoemrGrade(String memberType) {
        CustomerGradeModels retGrade = new CustomerGradeModels();
        for (CustomerGradeModels grade : custoemrGrades) {
            if (grade.getMemberType().equals(memberType)) {
                return grade;
            }
        }
        return retGrade;
    }

    public String getMemberTypeByMemberNo(String memberNO) {
        String memberType = "";
        BigDecimal decimal_memberno = new BigDecimal(memberNO);
        for (CustomerGradeModels grade : custoemrGrades) {
            BigDecimal minValue = grade.getMinValue();
            BigDecimal maxValue = grade.getMaxValue();
            if (decimal_memberno.compareTo(minValue) >= 0 && decimal_memberno.compareTo(maxValue) < 0) {
                return grade.getMemberType();
            }
        }
        return memberType;
    }
}
