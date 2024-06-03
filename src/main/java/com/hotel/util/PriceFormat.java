package com.hotel.util;

import java.text.DecimalFormat;

import org.springframework.stereotype.Component;

@Component
public class PriceFormat {
	public String format(float price){
		//tạo thêm ở dto 1 cái string format price
		DecimalFormat df = new DecimalFormat("###,###");
        String formattedNumber = df.format(price);
        return formattedNumber;
	}
}
