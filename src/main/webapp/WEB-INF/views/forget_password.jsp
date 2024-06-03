<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/gui-mat-khau-moi" var="request"/>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script>
    function showAlert(notification) {
    	Swal.fire({
    		  icon: "warning",
    		  title: "Thông báo",
    		  text: notification,
    		  timer: 3000,
    		}).then((result) => {
            if (result.isConfirmed) {
                // Nếu người dùng xác nhận, điều hướng tới href của thẻ <a>
                window.location.href = document.getElementById('cancel-link').href;
            } else if (result.isDenied) {
                Swal.fire("Changes are not saved", "", "info");
            }
        });
        setTimeout(() => {
    	    
    	}, 3000);
    }
</script>
<%
    // Lấy giá trị của tham số "message" từ URL
    String error = request.getParameter("error");

    // Kiểm tra xem tham số "message" có tồn tại không
    if (error != null) {
        %>
        <script>
        	showAlert("Vui lòng không để trống email!");
	    </script>
    <%}%>

<div class="container">
	
	<div class="card o-hidden border-0 shadow-lg my-5 w-50 mx-auto">
		<div class="card-body py-2">
			<!-- Nested Row within Card Body -->
			<div class="row mx-auto">

				<div class="col-lg-12">
					<div class="p-5">
						<div class="text-center">
							<h1 class="h4 text-gray-900 mb-4">Quên mật khẩu</h1>
						</div>
						<form id="formSearchCustomer" method="post" action="<c:url value='/quan-tri/dat-phong/tim-khach-hang'/>">
							<div class="form-row">
								<div class="form-group col-md-6">
									<input class="form-control form-control-user position-relative mb-2" type="text" name="phoneNumber" id="phoneNumber" placeholder="Số điện thoại(*)" autofocus="autofocus" />
									<span id="phoneNumberError" class="text-danger" style="white-space: nowrap;"></span>
								</div>
								<div class="form-group col-md-6">
									<input type="text" class="form-control form-control-user position-relative" id="customerName" name="customerName"  readonly />
								</div>
								<input type="submit" value="Tìm khách hàng" class="btn btn-user btn-block mb-4" style="color:#fff; background: #dfa974">
							</div>
						</form>
						<form:form id="formConfirm" cssClass="user" method="post" action="${request }"  modelAttribute="model">
							<form:hidden cssClass="form-control" path="id" />
							<form:hidden path="fullName"  value="1"/>
							<form:hidden path="phone" id="phone" value="1"/>
							
							<%-- <div class="form-group mb-2">
								<form:input cssClass="form-control form-control-user"
									path="accountName" placeholder="Tên đăng nhập"/>
								<form:errors path="accountName" cssStyle="color:red;font-size:13px;padding-left:5px"></form:errors>
							</div> --%>
							
							<div class="form-group mb-2 position-relative">
								<form:input type="Email" cssClass="form-control form-control-user " 
									path="email" placeholder="Email"/>
									<p class="position-absolute" style="top:35%;right:5%;color:red">*</p>
								<p id="result" style="font-size:13px;padding-left:5px "></p>
							</div>
							<div class="d-flex flex-wrap">
								<div class="d-flex flex-nowrap" style="font-size:14px;margin-bottom:-10px">
									<p class="text-gray-600 mb-1" style="font-size:11px"><p style="color:red;margin-right:10px">*</p> Vui lòng nhập số điện thoại của bạn</p>
								</div>
								<div class="d-flex flex-nowrap" style="font-size:14px;">
									<p class="text-gray-600" style="font-size:11px"><p style="color:red;margin-right:10px">*</p> Vui lòng nhập gmail để được cấp lại  mật khẩu</p>
								</div>
								
							</div>
							
							<button class="btn btn-user btn-block" style="color:#fff; background: #dfa974">Gửi yêu cầu</button>
							 
							<hr>
						</form:form>
						
						<!-- <div class="text-center">
							<a class="small" href="forgot-password.html">Forgot Password?</a>
						</div> -->
						<div class="text-center">
							<a class="small" href="<c:url value='/dang-nhap'/>" style="color:#333">Đăng nhập!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>

<script>
$('#formConfirm input, #formConfirm button').prop('disabled', true);
$(document).ready(function () {
	
    $('#formSearchCustomer').submit(function(e) {
        e.preventDefault(); // Ngăn submit mặc định của form
        searchCustomer();
        //return false; // Đảm bảo không có chuyển hướng xảy ra
    });

    function searchCustomer() {
        var phoneNumber = $("#phoneNumber").val();
        var action = $("#formSearchCustomer").attr('action'); // Lấy giá trị action từ form
        var method = $("#formSearchCustomer").attr('method'); // Lấy giá trị method từ form

        $.ajax({
            url: action,
            type: method,
            data: { phoneNumber: phoneNumber }, // Chỉ gửi phoneNumber
            dataType: "json",
            success: function(data) {
                console.log("Name người dùng: " + data.fullName);
                if (data.fullName) {
                	$("#phone").val(data.phone);
                    $("#customerName").val(data.fullName);
                    $("#customerId").val(data.id);
                    // Enable form fields and buttons
                    $('#formConfirm input, #formConfirm button').prop('disabled', false);
                } else {
                	$('#formConfirm input, #formConfirm button').prop('disabled', true);
                	$("#customerName").val('');
                    if (data.message) {
                        $("#phoneNumberError").text(data.message); // Display error message
                    } else {
                        // Disable form fields and buttons if customer not found
                        $("#logoutModal").addClass("fade show");
                        $("#logoutModal").attr({
                            "tabindex": "-1",
                            "aria-labelledby": "exampleModalLabel",
                            "aria-modal": "true",
                            "role": "dialog"
                        });
                        $("#logoutModal").css({
                            "display": "block",
                            "padding-right": "17px"
                        });
                        $("#modalOverlay").show(); // Hiển thị overlay
                    }
                }
            },
            error: function() {
                alert("Yêu cầu tìm khách hàng của bạn không hợp lệ!");
            }
        });
    }
});
// nhớ bỏ script src="<c:url value='/template/admin/vendor/jquery/jquery.min.js'/>" lên nằm đầu 
	document.getElementById('formConfirm').addEventListener('submit', function(e) {
           
            var email = document.getElementById('email').value;
            var resultElement = document.getElementById('result');
            var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // Biểu thức chính quy kiểm tra email

            if (!emailPattern.test(email) || !email) {
            	resultElement.textContent = 'Email không hợp lệ';
                resultElement.style.color = 'red';
                e.preventDefault(); // Ngăn chặn hành động submit mặc định
            } 
        });
    </script>