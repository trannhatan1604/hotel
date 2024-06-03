<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/taglib.jsp"%>

<c:url value="/quan-tri/loai-phong/luu" var="updateTypeRoom" />
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
			<h6 class="m-0 font-weight-bold text-primary">${model.id != null ? 'Chỉnh sửa loại phòng' : 'Thêm loại phòng'}</h6>
		</div>
		<div class="card-body"> 
			<form:form method="POST" action="${updateTypeRoom }" enctype="multipart/form-data"
				modelAttribute="model">
				<%-- <input type="hidden" class="form-control" name="id" autofocus
					value="${model.id}" /> --%>
				<form:hidden path="id" />
				<div class="form-group">
					<label class="control-label col-6 d-flex">Tên loại phòng: <p style="color:red;margin:0 5px ">*</p></label>
					<div class="col-sm-10">
						<%-- <input type="text" class="form-control" name="name" autofocus
							value="${model.name}" /> --%>
						<form:input path="name" cssClass="form-control"/>
						<form:errors  path="name" cssStyle="color:#e74a3b; padding-top:10px"/>
					</div>
				</div>
				<h1></h1>
				<div class="form-group">
					<label class="control-label col-6 d-flex">Số người: <p style="color:red;margin:0 5px ">*</p></label>
					<div class="col-sm-10">
						<form:input type="number" cssClass="form-control" path="quantity" min="1"
							 />
						<form:errors path="quantity"></form:errors>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-6 d-flex">Giá phòng: <p style="color:red;margin:0 5px ">*</p></label>
					<div class="col-sm-10">
						<form:input cssClass="form-control" path = "priceFormat"
							 />
						<form:errors path="priceFormat" cssStyle="color:#e74a3b; padding-top:10px"></form:errors>
						<form:errors path="price" cssStyle="color:#e74a3b; padding-top:10px"></form:errors>
					</div>
				</div>
	
				<div class="form-group">
					<label class="control-label col-6 d-flex">Kích thước phòng(m<sup style="padding-top:10px">2</sup>): <p style="color:red;margin:0 5px ">*</p></label>
					<div class="col-sm-10">
						<form:input cssClass="form-control" type="number" path = "space" min="1"/>
						<form:errors path="space" cssStyle="color:#e74a3b; padding-top:10px"></form:errors>
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2">Mô tả:</label>
					<div class="col-sm-10">
						<form:textarea cssClass="form-control" cssStyle="height:150px" path = "description" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2">Ảnh loại phòng:</label>
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
					<div class="col-lg-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary">
							<i class="fa fa-floppy-o"></i> Lưu dữ liệu
						</button>
						<a href="/hotel/quan-tri/loai-phong" class="btn btn-secondary">
							Quay lại </a>
					</div>
				</div>
		</div>
		</form:form>

	</div>
</div>
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