package com.coding.sales.service;

import com.coding.sales.enity.FullReductionModels;
import com.coding.sales.enity.MetalModels;
import com.coding.sales.output.DiscountItemRepresentation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MetalService {

    static Map<String, Integer> discountMap = new HashMap<String, Integer>();
    static {
        discountMap.put("nineDiscount",90);
        discountMap.put("nineFiveDiscount",95);
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
        metalModel2.setDiscountCards("nineDiscount");
        metalList.add(metalModel2);
        MetalModels metalModel3 = new MetalModels();
        metalModel3.setProductNo("002001");
        metalModel3.setProductName("守扩之羽比翼双飞4.8g");
        metalModel3.setProductPrice(BigDecimal.valueOf(1080.00));
        metalModel3.setUnit("条");
        metalModel3.setDiscountCards("nineFiveDiscount");
        metalList.add(metalModel3);
        MetalModels metalModel4 = new MetalModels();
        metalModel4.setProductNo("002002");
        metalModel4.setProductName("中国经典钱币套装");
        metalModel4.setProductPrice(BigDecimal.valueOf(998.00));
        metalModel4.setUnit("套");
        metalModel4.setDiscountCards("");
        metalModel4.setFullReduction(fullReduction2);
        metalList.add(metalModel4);
        MetalModels metalModel5 = new MetalModels();
        metalModel5.setProductNo("002003");
        metalModel5.setProductName("中国银象棋12g");
        metalModel5.setProductPrice(BigDecimal.valueOf(698.00));
        metalModel5.setUnit("套");
        metalModel5.setDiscountCards("nineDiscount");
        metalModel5.setFullReduction(fullReduction3);
        metalList.add(metalModel5);
        MetalModels metalModel6 = new MetalModels();
        metalModel6.setProductNo("003001");
        metalModel6.setProductName("招财进宝");
        metalModel6.setProductPrice(BigDecimal.valueOf(1580.00));
        metalModel6.setUnit("条");
        metalModel6.setDiscountCards("nineFiveDiscount");
        metalList.add(metalModel6);
        MetalModels metalModel7 = new MetalModels();
        metalModel7.setProductNo("003002");
        metalModel7.setProductName("水晶之恋");
        metalModel7.setProductPrice(BigDecimal.valueOf(980.00));
        metalModel7.setUnit("条");
        metalModel7.setDiscountCards("");
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
    public DiscountItemRepresentation getPayMoney (String productNo,Integer amount,String diacountType) {
        MetalModels metal = getMetalInfo(productNo);
        BigDecimal money = metal.getProductPrice().multiply(BigDecimal.valueOf(amount));
        BigDecimal actualPayment = money;

        if (diacountType.equals(metal.getDiscountCards())){
            actualPayment = money.multiply(BigDecimal.valueOf(discountMap.get(diacountType)));
        }
        if (metal.getFullReduction()!=null){
            Integer someThousands= money.divide(BigDecimal.valueOf(1000)).setScale( 0, BigDecimal.ROUND_DOWN ).intValue();
            if (){

            }
            if (someThousands>=3){
                actualPayment=money.subtract(BigDecimal.valueOf(350*(someThousands/3)));
            }else if (someThousands>=2){
                actualPayment=money.subtract(BigDecimal.valueOf(30*(someThousands/2)));
            }else if (someThousands>=1){
                actualPayment=money.subtract(BigDecimal.valueOf(10*someThousands));
            }
        }
        DiscountItemRepresentation discountItemRepresentation = new DiscountItemRepresentation(productNo,metal.getProductName(),actualPayment);
        return discountItemRepresentation;
    }

}
