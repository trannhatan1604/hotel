<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script>
function showAlert(notification) {
    Swal.fire({
        icon: "success", // Changed to "warning" for better context
        title: "Thông báo",
        text: notification,
        timer: 3000,
        showConfirmButton: false // Automatically close after 3 seconds
    });
}
</script>
<%
String successRegister = request.getParameter("successRegister");

// Kiểm tra xem tham số "message" có tồn tại không
if (successRegister != null) {
%>
	<script>
		showAlert("Đăng ký tài khoản thành công!");
	</script>
<%
}

String error = request.getParameter("error");

// Kiểm tra xem tham số "message" có tồn tại không
if (error != null) {
%>
	<script>
		showAlert("Quý khách vui lòng kiểm tra email!");
	</script>
<%
}
%>
<div class="row justify-content-center">

	<div class="col-xl-10 col-lg-12 col-md-9">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-6 d-none d-lg-block bg-login-img"
						style="background-image: url('https://images.unsplash.com/photo-1618773928121-c32242e63f39?q=80&amp;amp;w=2070&amp;amp;auto=format&amp;amp;fit=crop&amp;amp;ixlib=rb-4.0.3&amp;amp;ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'); background-position: center; background-size: cover;">
					</div>
					<div class="col-lg-6">
						<div class="p-5">
							
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">ĐĂNG NHẬP</h1>
							</div>
							<form class="user" action="j_spring_security_check" method="post">
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="userName" name="j_username" placeholder="Số điện thoại">
								</div>
								<div class="form-group">
									<input type="password" class="form-control form-control-user"
										id="password" name="j_password" placeholder="Mật khẩu">
								</div>
								
								<c:if test="${param.incorrectAccount != null}">
									<div class="alert alert-danger" style="font-size: 13px">Tài khoản không đúng hoặc không hoạt động!</div>
								</c:if>
								<c:if test="${param.accessDenied != null}">
									<div class="alert alert-danger" style="font-size: 13px">Bạn không có quyền truy cập!</div>
								</c:if>
								<c:if test="${param.nullAccount != null}">
									<div class="alert alert-danger" style="font-size: 13px">Tên đăng nhập hoặc mật khẩu không bỏ trống!</div>
								</c:if>
								<input type="submit" class="btn btn-user btn-block" style="background:#dfa974; color:#fff" placeholder="Đăng nhập">
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="<c:url value= '/doi-mat-khau'/>" style="color:#333">Quên mật khẩu?</a>
							</div> 
							<div class="text-center">
								<a class="small" href="<c:url value= '/dang-ky'/>" style="color:#333">Tạo tài khoản mới!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

</div>
<script>
window.setTimeout(function() {
    $(".alert").fadeTo(300, 0).slideUp(300, function(){
        $(this).remove(); 
    });
}, 2500);
</script>