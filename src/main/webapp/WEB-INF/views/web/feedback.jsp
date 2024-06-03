
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value='/phan-hoi' var="addfeedback" />
<c:url value='/lich-su' var="history" />

<div class="modal-dialog">

		<form:form action="${addfeedback }" id="formFeedback" method="get"
			modelAttribute="model">
			<form:hidden path="roomId" />
			<form:hidden path="accountId" />
			<form:hidden path="orderId" />
			<div class="modal-dialog" role="document" >
				<div class="modal-content" style="box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px; border: 5px dashed #fff;">
					<div class="modal-header" style="background:#908a78;border: 2px dashed #fff; ">
						<h5 class="modal-title" id="exampleModalLongTitle" style="color:#fff">Phản hồi
							từ khách hàng <!-- <i class="fa-solid fa-heart" style="color:red;padding-left:5px;font-size:18px"></i> --></h5>
						<a class="close" data-dismiss="modal" href='${history }'
							aria-label="Close">
							<span aria-hidden="true" style="color:#fff">&times;</span>
						</a>
					</div>
					<div class="modal-body">
						<form:textarea rows="10" cssClass="form-control mb-3 search" path="description" style=" box-shadow: inset 5px 5px 5px #d2d8de, inset -5px -5px 5px #cbced1;border:none"/>
						<form:errors path="description" style="color:red; text-align:center"/>
					</div>
					<div class="modal-footer">
						<form:button type="submit" class="btn" style="background:#d7c38c;color:#333;padding:5px 35px; font-size:18px">Gửi</form:button>
					</div>
				</div>
			</div>
			
		</form:form>
		
</div>
