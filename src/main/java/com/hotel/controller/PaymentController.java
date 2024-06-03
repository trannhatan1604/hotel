package com.hotel.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.config.Config;
import com.hotel.constant.SystemConstant;
import com.hotel.service.IOrderService;

@Controller
public class PaymentController {

	@Autowired
	private IOrderService orderService;
	
	public String getPay(String orderID, float total) throws UnsupportedEncodingException{
		
		 	String vnp_Version = "2.1.0";
	        String vnp_Command = "pay";
	        String orderType = "other";
	        //số tiền 
			/* long amount = Integer.parseInt(req.getParameter("amount"))*100; */
	        long amount = (long)total * 100;
	        String bankCode = "NCB";
	        
	        //Mã đơn hàng
	        String vnp_TxnRef = Config.getRandomNumber(8);
	        String vnp_IpAddr = "127.0.0.1";

	        String vnp_TmnCode = Config.vnp_TmnCode;
	        
	        Map<String, String> vnp_Params = new HashMap<>();
	        vnp_Params.put("vnp_Version", vnp_Version);
	        vnp_Params.put("vnp_Command", vnp_Command);
	        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
	        vnp_Params.put("vnp_Amount", String.valueOf(amount));
	        vnp_Params.put("vnp_CurrCode", "VND");
	        
	        vnp_Params.put("vnp_BankCode", bankCode);
	        //Mã hoá đơn
	        vnp_Params.put("vnp_TxnRef", orderID);
	        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang " + orderID);
	        vnp_Params.put("vnp_OrderType", orderType);

//	        String locate = req.getParameter("language");
//	        if (locate != null && !locate.isEmpty()) {
//	            vnp_Params.put("vnp_Locale", locate);
//	        } else {
//	            vnp_Params.put("vnp_Locale", "vn");
//	        }
	        vnp_Params.put("vnp_Locale", "vn");
	        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
	        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

	        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        String vnp_CreateDate = formatter.format(cld.getTime());
	        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
	        
	        cld.add(Calendar.MINUTE, 15);
	        String vnp_ExpireDate = formatter.format(cld.getTime());
	        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
	        
	        List fieldNames = new ArrayList(vnp_Params.keySet());
	        Collections.sort(fieldNames);
	        StringBuilder hashData = new StringBuilder();
	        StringBuilder query = new StringBuilder();
	        Iterator itr = fieldNames.iterator();
	        while (itr.hasNext()) {
	            String fieldName = (String) itr.next();
	            String fieldValue = (String) vnp_Params.get(fieldName);
	            if ((fieldValue != null) && (fieldValue.length() > 0)) {
	                //Build hash data
	                hashData.append(fieldName);
	                hashData.append('=');
	                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	                //Build query
	                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
	                query.append('=');
	                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
	                if (itr.hasNext()) {
	                    query.append('&');
	                    hashData.append('&');
	                }
	            }
	        }
	        String queryUrl = query.toString();
	        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
	        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
	        String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
		return paymentUrl;
	}
	
	@RequestMapping(value = "/thanh-toan", method = RequestMethod.POST)
	private ModelAndView thanhtoan(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String orderId = request.getParameter("ma");
	    float total = 0;
	    try {
	        total = Float.parseFloat(request.getParameter("total"));
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	    }

		try {
			String link = getPay(orderId, total);
			mav.setViewName("redirect:" + link);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mav;
	}
	//VNpay chỉ nhận kiểu yêu cầu theo Http Get 
	 @RequestMapping(value = "/thanh-toan/ket-qua-trung-gian", method = RequestMethod.GET)
	    private ModelAndView ketquaTrungGian(HttpServletRequest request, HttpSession session) {
		 String transactionStatus = request.getParameter("vnp_TransactionStatus");
		 Map<String, String> params = new HashMap<>();
		    Enumeration<String> parameterNames = request.getParameterNames();
		    while (parameterNames.hasMoreElements()) {
		        String paramName = parameterNames.nextElement();
		        params.put(paramName, request.getParameter(paramName));
		    }
		 if(params.get("vnp_TransactionStatus").equals("00")) {
			 orderService.updateStatus(Integer.parseInt(params.get("vnp_TxnRef")), SystemConstant.PAID);
		 }
		    // Chuyển hướng tới phương thức ketqua và chuyển params qua
	     ModelAndView mav = new ModelAndView("redirect:/thanh-toan/ket-qua");
	     session.setAttribute("params", params);
		 return mav;
	 }
	
	@RequestMapping(value = "/thanh-toan/ket-qua", method = RequestMethod.GET)
	private ModelAndView ketqua(HttpSession session) {
		Map<String, String> params = (Map<String, String>) session.getAttribute("params");
		
		if(params == null)
		{
			ModelAndView mav = new ModelAndView("redirect:/lich-su");
			return mav;
		}
		
	    session.removeAttribute("params");
		/*
		 * for (Map.Entry<String, String> map : params.entrySet()) {
		 * System.out.println(map.getKey()+" "+map.getValue()); }
		 */
		
	    ModelAndView mav = new ModelAndView("/vnpay_return");
	    mav.addObject("params", params);
		return mav;
	}
}
