package com.coding.sales.service;

import com.coding.sales.enity.CustomerGradeModels;
import com.coding.sales.enity.CustomerModels;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    List<CustomerModels> customers = new ArrayList<CustomerModels>();

    {
        CustomerModels customerOne = new CustomerModels();
        customerOne.setMemberId("6236609999");
        customerOne.setMemberName("马丁");
        customerOne.setMemberType("普卡");
        customerOne.setMemberNo("9860");
        customers.add(customerOne);
        CustomerModels customerTwo = new CustomerModels();
        customerTwo.setMemberId("6630009999");
        customerTwo.setMemberName("王立");
        customerTwo.setMemberType("金卡");
        customerTwo.setMemberNo("48860");
        customers.add(customerTwo);
        CustomerModels customerThree = new CustomerModels();
        customerThree.setMemberId("8230009999");
        customerThree.setMemberName("李想");
        customerThree.setMemberType("白金卡");
        customerThree.setMemberNo("98860");
        customers.add(customerThree);
        CustomerModels customerFour = new CustomerModels();
        customerFour.setMemberId("9230009999");
        customerFour.setMemberName("张三");
        customerFour.setMemberType("钻石卡");
        customerFour.setMemberNo("198860");
        customers.add(customerFour);
    }

    public List<CustomerModels> getCustomers() {
        return customers;
    }

    public CustomerModels getOldCustomerModel(String memberId) {
        CustomerModels customerModel = new CustomerModels();
        for (CustomerModels customer : customers) {
            if (customer.getMemberId().equals(memberId)) {
                return customer;
            }
        }
        return customerModel;
    }

    public CustomerModels getNewCustomerModel(CustomerModels customer,BigDecimal money) {
        CustomerModels newCustomerModel = new CustomerModels();
        CustomerGradeService gradeService = new CustomerGradeService();
        CustomerGradeModels gradeModel = gradeService.getCustoemrGrade(customer.getMemberType());

        BigDecimal addMemberNo = gradeModel.getBasicValue().multiply(money.setScale(0,BigDecimal.ROUND_DOWN));
        String newMemberNo = new BigDecimal(customer.getMemberNo()).add(addMemberNo).toString();
        String newMemberType = gradeService.getMemberTypeByMemberNo(newMemberNo);
        newCustomerModel.setMemberNo(newMemberNo);
        newCustomerModel.setMemberType(newMemberType);
        return newCustomerModel;
    }
}
