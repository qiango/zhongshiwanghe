package com.hongzhi.zswh.util.calculate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : May 13, 2016    11:01:25 AM
 */
public class StringMath {
	
	public  static Double  discountPrice(String method,Double price){
		ScriptEngineManager mgr = new ScriptEngineManager();    
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");        
	    try {
			return  Double.parseDouble( engine.eval(method.replace("_X_", price.toString())).toString())  ;
		} catch (ScriptException e) {
			e.printStackTrace();
			return  price;
		}
		
	}
	
	public static Double  discountPrice(Integer discount_type,Double price , Double param,String method){
	    if(!ObjectUtil.isEmpty(discount_type)){
            // 折扣类型, 1: 立减 XXX 元,  2:滿 param XXXX , 3:XX 折 
            switch (discount_type) {
            case 1:  
                    price = discountPrice(method, price);
                break;
            case 2:
                if(price >= param){
                    price = discountPrice(method, price);
                }
                break;
            case 3:
                    price = discountPrice(method, price);
                break;
            default:
                break;
            }
        }
	    return price;
	}
	
	 public static Double  discountPart(Integer discount_type,Double price , Double param,String method, Integer count){
	     return (price-discountPrice(discount_type, price,param,method))*count;
	 }
	       

}
