package com.coding.sales;

import com.coding.sales.enity.CustomerModels;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;
import com.coding.sales.service.CustomerService;
import com.coding.sales.service.MetalService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);

        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        //TODO: 请完成需求指定的功能

        OrderRepresentation result = null;
        String orderId = command.getOrderId();
        Date createTime = String2Date(command.getCreateTime());
        CustomerService customerService = new CustomerService();
        CustomerModels oldCustomerModel = customerService.getOldCustomerModel(command.getMemberId());
        String memberNo = oldCustomerModel.getMemberNo();
        String memberId = oldCustomerModel.getMemberId();
        String memberName = oldCustomerModel.getMemberName();
        String oldMemberType = oldCustomerModel.getMemberType();
        MetalService metalService = new MetalService();
        List<OrderItemRepresentation> items = metalService.getPayInfoList(command.getItems());
        BigDecimal totalPrice = new BigDecimal(0);
        for(OrderItemRepresentation item : items){
            totalPrice=totalPrice.add(item.getSubTotal());
        }
        BigDecimal totalDiscountPrice = new BigDecimal(0);
        BigDecimal receivables = new BigDecimal(0);
        List<DiscountItemRepresentation> discounts = new ArrayList<DiscountItemRepresentation>();
        List<DiscountItemRepresentation> discountsList = metalService.getDiscountList(command.getItems(),command.getDiscounts());
        for(DiscountItemRepresentation discount : discountsList){
            totalDiscountPrice=totalDiscountPrice.add(discount.getDiscount());
            receivables=receivables.add(discount.getActualPayment());
            if (discount.getDiscount().compareTo(BigDecimal.valueOf(0.00))!=0){
                discounts.add(discount);
            }
        }
        CustomerModels newMemberModel = customerService.getNewCustomerModel(oldCustomerModel,receivables);
        String newMemberType = newMemberModel.getMemberType();
        Integer memberPoints = Integer.valueOf(newMemberModel.getMemberNo());
        Integer memberPointsIncreased = Integer.valueOf(newMemberModel.getMemberNo())-Integer.valueOf(memberNo);
        List<String> discountCards = command.getDiscounts();
        List<PaymentRepresentation> payments = new ArrayList<PaymentRepresentation>();
        for (PaymentCommand paymentCommand:command.getPayments()){
            PaymentRepresentation paymentRepresentation = new PaymentRepresentation(paymentCommand.getType(),paymentCommand.getAmount());
            payments.add(paymentRepresentation);
        }
        result=new OrderRepresentation(orderId,createTime,memberId,memberName,oldMemberType,newMemberType,memberPointsIncreased,memberPoints,items,totalPrice,discounts,totalDiscountPrice,receivables,payments,discountCards);

        return result;
    }

    public Date String2Date(String dateString) {
        Date nowDate = new Date();
        if (dateString != null && !"".equals(dateString)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(dateString);
            } catch (ParseException e) {
                throw new RuntimeException("转换日期异常");
            }
        }
        return nowDate;
    }
}
