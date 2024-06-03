<%@page import="com.hotel.dto.RoomDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/quan-tri/phong/luu" var="updateRoom" />
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800"></h1>
	<c:if test="${not empty message}">

		<div class="alert alert-${alert}" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			${message}
		</div>
	</c:if>
	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">${model.id != null ? 'Chỉnh sửa phòng' : 'Thêm phòng'}</h6>
		</div>
		<div class="card-body">
			<form:form method="POST" action="${updateRoom }"
				enctype="multipart/form-data" modelAttribute="model">
				<%-- <input type="hidden" class="form-control" name="id" autofocus
					value="${model.id}" />
				<input type="hidden" name="ratequantity"
					value="${model.ratequantity}" /> --%>
				<form:hidden path="id" />
				<form:hidden path="ratequantity" />

				<div class="form-group">
					<label class="control-label col-sm-2">Tên phòng:</label>
					<div class="col-sm-11">
						<%-- <input type="text" class="form-control" name="name" autofocus
							value="${model.name}" /> --%>
						<form:input path="name" cssClass="form-control"  />
						<form:errors path="name" cssStyle="color:#e74a3b; padding-top:10px"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group col">
						<label class="control-label col-sm-6">Trạng thái:</label>
						<div class="col-sm-10">
							<%-- <select class="form-control" name="statusid">
								<option value="0">-- Chọn trạng thái --</option>
								<c:forEach items="${statuses}" var="status">
									<option value="${status.id}"
										${status.id == model.statusid ? 'selected' : ''}>${status.statusname}</option>
								</c:forEach>
							</select> --%>
							<form:select path="statusid" cssClass="form-control">
								<form:option value="" label="--Chọn loại phòng--"/>
								<form:options items="${statuses }"/>
							</form:select>
							<form:errors path="statusid" cssStyle="color:#e74a3b; padding-top:10px"/>
						</div>
					</div>
					<h1></h1>
					<div class="form-group col">
						<label class="control-label col-sm-5">Loại phòng:</label>
						<div class="col-sm-10">
							<form:select path="typeid" cssClass="form-control">
								<form:option value="" label="--Chọn loại phòng--"/>
								<form:options items="${typerooms }"/>
							</form:select>
							<form:errors path="typeid" cssStyle="color:#e74a3b; padding-top:10px"/>
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2">Ảnh phòng:</label>
					<div class="col-sm-5">
						<form:input type="hidden" path="image"  /> 
						<input
							style="padding: 5px" type="file"
							class="form-control image-upload" name="photo" accept="image/*"
							onchange="document.getElementById('Photo').src = window.URL.createObjectURL(this.files[0])" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-offset-2 col-sm-11">
						<img id="Photo" src="<c:url value='${model.image}'/>"
							class="img img-bordered"
							style="width: 350px; border-radius: 5px; border: 1px #7a6f6f solid" />
					</div>
				</div>


				<div class="form-group text-right">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">
							<i class="fa fa-floppy-o"></i> Lưu dữ liệu
						</button>
						<a href="/hotel/quan-tri/phong" class="btn btn-secondary">
							Quay lại </a>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</div>
<script>
	window.setTimeout(function() {
		$(".alert").fadeTo(300, 0).slideUp(300, function() {
			$(this).remove();
		});
	}, 1500);
</script>