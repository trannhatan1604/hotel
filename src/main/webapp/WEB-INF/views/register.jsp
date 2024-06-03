<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<c:url value='/dang-ky/luu' var="register"/>
<div class="container">

	<div class="card o-hidden border-0 shadow-lg my-5 w-75 mx-auto">
		<div class="card-body p-0">
			<!-- Nested Row within Card Body -->
			<div class="row">
				<div class="col-lg-6 d-none d-lg-block"
					style="background-image: url('https://images.unsplash.com/photo-1618773928121-c32242e63f39?q=80&amp;amp;w=2070&amp;amp;auto=format&amp;amp;fit=crop&amp;amp;ixlib=rb-4.0.3&amp;amp;ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'); background-position: center; background-size: cover;"></div>
				<div class="col-lg-6">
					<div class="p-5">
						<div class="text-center">
							<h1 class="h4 text-gray-900 mb-4">Tạo tài khoản</h1>
						</div>
						<form:form cssClass="user" method="post" action="${register }"  modelAttribute="model">
							<form:hidden cssClass="form-control" path="id" />
							<form:hidden path="fullName" value="1"/>
							<div class="form-group row mb-2">
								<div class="col-sm-6 mb-3 mb-sm-0">
									<form:input cssClass="form-control form-control-user position-relative"
										path="firstName" placeholder="Tên" autofocus="autofocus" />
										<p class="position-absolute" style="top:30%;right:20%;color:red">*</p>
									<form:errors path="firstName" cssStyle="color:red;font-size:13px;padding-left:5px"></form:errors>
								</div>
								<div class="col-sm-6">
									<form:input type="text" class="form-control form-control-user position-relative"
										path="lastName" placeholder="Họ" />
										<p class="position-absolute" style="top:30%;right:20%;color:red">*</p>
									<form:errors path="lastName" cssStyle="color:red;font-size:13px;padding-left:5px"></form:errors>
								</div>
								
							</div>
							
							<%-- <div class="form-group mb-2">
								<form:input cssClass="form-control form-control-user"
									path="accountName" placeholder="Tên đăng nhập"/>
								<form:errors path="accountName" cssStyle="color:red;font-size:13px;padding-left:5px"></form:errors>
							</div> --%>
							<div class="form-group mb-2 position-relative">
								<form:input type="Phone" cssClass="form-control form-control-user " 
									path="phone" placeholder="Số điện thoại"/>
									<p class="position-absolute" style="top:35%;right:5%;color:red">*</p>
								<form:errors path="phone" cssStyle="color:red;font-size:13px;padding-left:5px"></form:errors>
							</div>
							
							<div class="form-group row mb-2" >
								<div class="col-sm-6 mb-3 mb-sm-0">
									<form:input type="password" cssClass="form-control form-control-user"
										path="password" placeholder="Mật khẩu"/>
									<form:errors path="password" cssStyle="color:red;font-size:13px;padding-left:5px;white-space: nowrap;"></form:errors>
								</div>
								<div class="col-sm-6">
									<form:input type="password" cssClass="form-control form-control-user"
										path="repeatPassword" placeholder="Nhập lại mật khẩu"/>
								</div>
							</div>
							<div class="form-group row mb-2" >
								<form:errors path="repeatPassword" cssStyle="color:red;font-size:13px;padding-left:15px;white-space:nowrap"></form:errors>
							</div>	
							<button class="btn btn-user btn-block" style="color:#fff; background: #dfa974">Đăng ký tài khoản</button>
							<hr>
						</form:form>
						
						<!-- <div class="text-center">
							<a class="small" href="forgot-password.html">Forgot Password?</a>
						</div> -->
						<div class="text-center">
							<a class="small" href="<c:url value='/dang-nhap'/>" style="color:#333">Bạn đã có tài khoản rồi? Đăng nhập!</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>