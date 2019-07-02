package com.coding.sales.service;

import com.coding.sales.enity.FullReductionModels;
import com.coding.sales.enity.MetalModels;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetalService {

    static Map<String, Integer> discountMap = new HashMap<String, Integer>();
    static {
        discountMap.put("9折券",90);
        discountMap.put("95折券",95);
    }
    List<MetalModels> metalList = new ArrayList<MetalModels>();
    {
        FullReductionModels fullReductionModel1= new FullReductionModels();
        fullReductionModel1.setAllMoney(3000);
        fullReductionModel1.setReduceMoney(350);
        FullReductionModels fullReductionModel2= new FullReductionModels();
        fullReductionModel2.setAllMoney(2000);
        fullReductionModel2.setReduceMoney(30);
        FullReductionModels fullReductionModel3= new FullReductionModels();
        fullReductionModel3.setAllMoney(1000);
        fullReductionModel3.setReduceMoney(10);
        List<FullReductionModels> fullReduction1= new ArrayList<FullReductionModels>();
        fullReduction1.add(fullReductionModel3);
        List<FullReductionModels> fullReduction2= new ArrayList<FullReductionModels>();
        fullReduction1.add(fullReductionModel3);
        fullReduction1.add(fullReductionModel2);
        List<FullReductionModels> fullReduction3= new ArrayList<FullReductionModels>();
        fullReduction1.add(fullReductionModel1);
        fullReduction1.add(fullReductionModel2);
        fullReduction1.add(fullReductionModel3);
        MetalModels metalModel1 = new MetalModels();
        metalModel1.setProductNo("001001");
        metalModel1.setProductName("世园会五十国钱币册");
        metalModel1.setProductPrice(BigDecimal.valueOf(998.00));
        metalModel1.setUnit("册");
        metalModel1.setDiscountCards("");
        metalList.add(metalModel1);
        MetalModels metalModel2 = new MetalModels();
        metalModel2.setProductNo("001002");
        metalModel2.setProductName("2019北京世园会纪念银章大全40g");
        metalModel2.setProductPrice(BigDecimal.valueOf(1380.00));
        metalModel2.setUnit("盒");
        metalModel2.setDiscountCards("9折券");
        metalList.add(metalModel2);
        MetalModels metalModel3 = new MetalModels();
        metalModel3.setProductNo("002001");
        metalModel3.setProductName("守扩之羽比翼双飞4.8g");
        metalModel3.setProductPrice(BigDecimal.valueOf(1080.00));
        metalModel3.setUnit("条");
        metalModel3.setDiscountCards("95折券");
        metalModel3.setFullReduction("threePieces");
        metalList.add(metalModel3);
        MetalModels metalModel4 = new MetalModels();
        metalModel4.setProductNo("002002");
        metalModel4.setProductName("中国经典钱币套装");
        metalModel4.setProductPrice(BigDecimal.valueOf(998.00));
        metalModel4.setUnit("套");
        metalModel4.setDiscountCards("");
        metalModel4.setFullReduction("three");
        metalList.add(metalModel4);
        MetalModels metalModel5 = new MetalModels();
        metalModel5.setProductNo("002003");
        metalModel5.setProductName("中国银象棋12g");
        metalModel5.setProductPrice(BigDecimal.valueOf(698.00));
        metalModel5.setUnit("套");
        metalModel5.setDiscountCards("9折券");
        metalModel5.setFullReduction("three");
        metalList.add(metalModel5);
        MetalModels metalModel6 = new MetalModels();
        metalModel6.setProductNo("003001");
        metalModel6.setProductName("招财进宝");
        metalModel6.setProductPrice(BigDecimal.valueOf(1580.00));
        metalModel6.setUnit("条");
        metalModel6.setDiscountCards("95折券");
        metalList.add(metalModel6);
        MetalModels metalModel7 = new MetalModels();
        metalModel7.setProductNo("003002");
        metalModel7.setProductName("水晶之恋");
        metalModel7.setProductPrice(BigDecimal.valueOf(980.00));
        metalModel7.setUnit("条");
        metalModel7.setDiscountCards("");
        metalModel7.setFullReduction("threePieces");
        metalList.add(metalModel7);
    }

    public MetalModels getMetalInfo (String productNo){
        for (MetalModels metal:metalList){
            if (productNo.equals(metal.getProductNo())){
                return metal;
            }
        }
        return null;
    }
    public  List<OrderItemRepresentation> getPayInfoList (List<OrderItemCommand> orderItemCommand){
        List<OrderItemRepresentation> payInfoList= new ArrayList<OrderItemRepresentation>();
        for (OrderItemCommand item:orderItemCommand){
            OrderItemRepresentation payinfo=getPayInfo(item.getProduct(),item.getAmount());
            payInfoList.add(payinfo);
        }
        return payInfoList;
    }
    public OrderItemRepresentation getPayInfo (String productNo,BigDecimal amount) {
        MetalModels metal = getMetalInfo(productNo);
        BigDecimal money = metal.getProductPrice().multiply(amount);
        OrderItemRepresentation orderItemRepresentation = new OrderItemRepresentation(productNo,metal.getProductName(),metal.getProductPrice(),amount,money);
        return orderItemRepresentation;
    }
    public  List<DiscountItemRepresentation> getDiscountList (List<OrderItemCommand> orderItemCommand, List<String> discount){
        List<DiscountItemRepresentation> discountList= new ArrayList<DiscountItemRepresentation>();
        for (OrderItemCommand item:orderItemCommand){
            DiscountItemRepresentation discountInfo =getDiscount(item.getProduct(),item.getAmount(),discount.get(0).toString());
            discountList.add(discountInfo);
        }
        return discountList;
    }
    public DiscountItemRepresentation getDiscount (String productNo,BigDecimal amount,String diacountType) {
        MetalModels metal = getMetalInfo(productNo);
        BigDecimal money = metal.getProductPrice().multiply(amount);
        BigDecimal actualPayment = money;
        BigDecimal actualPayment1 = money;

        if (diacountType.equals(metal.getDiscountCards())){
            actualPayment1 = money.multiply(BigDecimal.valueOf(discountMap.get(diacountType))).divide(BigDecimal.valueOf(100));
            System.out.println("actualPayment1=="+actualPayment1);
        }
        OrderItemRepresentation orderItemRepresentation = new OrderItemRepresentation(productNo,metal.getProductName(),metal.getProductPrice(),amount,actualPayment);
        if (metal.getFullReduction()!=null){
            Integer someThousands= money.divide(BigDecimal.valueOf(1000)).setScale( 0, BigDecimal.ROUND_DOWN ).intValue();
            if (metal.getFullReduction().equals("three")){
                if (someThousands>=3){
                    actualPayment=money.subtract(BigDecimal.valueOf(350*(someThousands/3)));
                }else if (someThousands>=2){
                    actualPayment=money.subtract(BigDecimal.valueOf(30*(someThousands/2)));
                }else if (someThousands>=1){
                    actualPayment=money.subtract(BigDecimal.valueOf(10*someThousands));
                }
            }else  if (metal.getFullReduction().equals("two")){
                if (someThousands>=2){
                    actualPayment=money.subtract(BigDecimal.valueOf(30*(someThousands/2)));
                }else if (someThousands>=1){
                    actualPayment=money.subtract(BigDecimal.valueOf(10*someThousands));
                }
            }else if (metal.getFullReduction().equals("one")){
                if (someThousands>=1){
                    actualPayment=money.subtract(BigDecimal.valueOf(10*someThousands));
                }
            }else if (metal.getFullReduction().equals("threePieces")){
                if (amount.compareTo(BigDecimal.valueOf(3))==0){
                    actualPayment=money.multiply(BigDecimal.valueOf(2.5));
                }else if (amount.compareTo(BigDecimal.valueOf(3))>0){
                    actualPayment=metal.getProductPrice().multiply(amount.subtract(BigDecimal.valueOf(1)));
                }
            }
        }
        actualPayment=getMinValue(actualPayment,actualPayment1);
        BigDecimal discount = money.subtract(actualPayment);
        System.out.println("actualPayment=="+actualPayment);
        DiscountItemRepresentation discountItemRepresentation = new DiscountItemRepresentation(productNo,metal.getProductName(),actualPayment,discount);
        return discountItemRepresentation;
    }
    public BigDecimal getMinValue (BigDecimal valuea,BigDecimal valueb){
        System.out.println("valuea=="+valuea+"===valueb==="+valueb);
        if (valuea.compareTo(valueb)>=0){
            return valueb;
        }else {
            return valuea;
        }
    }
}
