package com.coding.sales;

import com.coding.sales.enity.CustomerModels;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.service.CustomerService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        OrderRepresentation result = null;
        String orderId = command.getOrderId();
        Date createTime = String2Date(command.getCreateTime());
        CustomerService customerService = new CustomerService();
        CustomerModels oldCustomerModel = customerService.getOldCustomerModel(command.getMemberId());
        String memberNo = oldCustomerModel.getMemberNo();
        String memberName = oldCustomerModel.getMemberName();
        String oldMemberType = oldCustomerModel.getMemberType();
        System.out.print(orderId+"..."+createTime+"..."+memberNo+"...."+memberName+"...."+oldMemberType);

        //TODO: 请完成需求指定的功能

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
