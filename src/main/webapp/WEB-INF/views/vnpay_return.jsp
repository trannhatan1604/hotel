<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.hotel.config.Config"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <title>KẾT QUẢ THANH TOÁN</title>
        <!-- Bootstrap core CSS -->
        <link href="/vnpay_jsp/assets/bootstrap.min.css" rel="stylesheet"/>
        <!-- Custom styles for this template -->
        <link href="/vnpay_jsp/assets/jumbotron-narrow.css" rel="stylesheet"> 
        <script src="/vnpay_jsp/assets/jquery-1.11.3.min.js"></script>
    </head>
    <body>
        <%
		    //Begin process return from VNPAY
		    Map<String, String> fields = (Map<String, String>) request.getAttribute("params");
		
		    String vnp_SecureHash = fields.get("vnp_SecureHash");
		    if (fields.containsKey("vnp_SecureHashType")) {
		        fields.remove("vnp_SecureHashType");
		    }
		    if (fields.containsKey("vnp_SecureHash")) {
		        fields.remove("vnp_SecureHash");
		    }
		    String signValue = Config.hashAllFields(fields);
		    LocalDateTime currentDateTime = LocalDateTime.now();
		
		    // Định dạng ngày và giờ
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String formattedDateTime = currentDateTime.format(formatter);
		    float total = Float.parseFloat(fields.get("vnp_Amount")) / 100;
		    DecimalFormat df = new DecimalFormat("###,###");
		    String formattedNumber = df.format(total);
		%>

        <!--Begin display -->
        <div class="container mx-auto jumbotron" style="max-width:500px;padding:20px;margin:20px;border:2px solid #333;">
            <div class="header clearfix mb-2" >
                <h3 class="text-muted text-center"><u>KẾT QUẢ THANH TOÁN</u></h3>
            </div>
            <div class="table-responsive">
                <div class="form-group">
                    <label >Mã giao dịch thanh toán : </label>
                    <label>${params.vnp_TxnRef }</label>
                </div>    
                <div class="form-group">
                    <label >Số tiền : </label>
                    <label><%=formattedNumber%> VNĐ</label>
                </div>  
                <div class="form-group">
                    <label >Mô tả giao dịch : </label>
                    <label>${params.vnp_OrderInfo }</label>
                </div> 
                <%-- <div class="form-group">
                    <label >Mã lỗi thanh toán:</label>
                    <label><%=request.getParameter("vnp_ResponseCode")%></label>
                </div>  --%>
                <div class="form-group">
                    <label >Mã giao dịch tại CTT VNPAY-QR : </label>
                    <label>${params.vnp_TransactionNo }</label>
                </div> 
                <div class="form-group">
                    <label >Mã ngân hàng thanh toán : </label>
                    <label>${params.vnp_BankCode }</label>
                </div> 
                <div class="form-group">
                    <label >Thời gian thanh toán : </label>
                    <%-- <label><%=request.getParameter("vnp_PayDate")%></label> --%>
                    <label><%=formattedDateTime%></label>
                </div> 
                <div class="form-group">
                    <label >Tình trạng giao dịch : </label>
                    <label>
                        <%
                        	
/* 						    if (signValue.equals(vnp_SecureHash)) { */
						        if ("00".equals(fields.get("vnp_TransactionStatus"))) {
						            out.print("Thành công");
						        } else {
						            out.print("Không thành công");
						        }
						    /* } else {
						        out.print(signValue);
						    } */
						%></label>
                </div> 
            </div>
            <p>
                &nbsp;
            </p>
            <footer class="footer d-flex justify-content-end">
                <p>&copy; Khách sạn Bình Minh</p>
            </footer>
        </div>  
    </body>
</html>
