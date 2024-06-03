<%@page import="com.hotel.util.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value='/quan-tri/thong-tin?id=${SecurityUtils.getPrincipal().getId()}' var="changePass"/>
<form:form action="${changePass }" method="post" modelAttribute="model" >
	<form:hidden path="id" value="${SecurityUtils.getPrincipal().getId() }"/>
	<h6>
	<i class="fa-solid fa-key"></i> Đổi mật khẩu
	</h6>
	<div class="col-md-10  form-group mt-md-3">
		<form:input cssClass="form-control" path="password" id="password" />
		<form:errors cssStyle="padding-top:10px; color:red" path="password"/>
	</div>
	<div class="col-md-10 form-group mt-md-3">
		<form:input cssClass="form-control" path="newPassword" id="newPassword" />
	</div>
	<div class="col-md-10 form-group mt-md-3">
		<form:input cssClass="form-control" path="repeatPassword" id="repeatPassword" />
	</div>
	<div class="d-flex justify-content-end" style="margin-right:100px">
		<form:button type="submit" class="btn btn-primary " id="submit">Lưu dữ liệu</form:button>
	</div>
</form:form>
<script>
$(document).ready(function() {
    $('#password').attr('placeholder', 'Mật khẩu cũ');
    $('#newPassword').attr('placeholder', 'Mật khẩu mới');
    $('#repeatPassword').attr('placeholder', 'Nhập lại mật khẩu mới')
    $(document).ready(function() {
        $('#submit').click(function() {
            $('#formSearch').submit();
        });
    });
});
</script>