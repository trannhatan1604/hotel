<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/quan-tri/nhan-vien/luu" var="updateEmployee"/>
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800"></h1>


	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">${model.id != null ? 'Chỉnh sửa nhân viên' : 'Thêm nhân viên'}</h6>
		</div>
		<div class="card-body">
			<div class="">
				<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
					<form:form method="post" action="${updateEmployee }" enctype="multipart/form-data" modelAttribute="model">
						<form:hidden cssClass="form-control" path="id" />
						<div class="column">
						<!-- <div class="col-sm-12 col-md-6">
							
						</div>
						<div class="col-sm-12 col-md-6">
							
						</div> -->
						<div class="form-group">
							<label class="control-label col-2 d-flex">Tên đăng nhập:  <p style="color:red;margin:0 5px ">*</p></label>
							<div class="col-sm-11">
								<form:input cssClass="form-control" path="accountName"/>
								<form:errors path="accountName" cssStyle="color:red;padding-top:10px"></form:errors>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-2 d-flex">Tên nhân viên:  <p style="color:red;margin:0 5px ">*</p></label>
							<div class="col-sm-11">
								<form:input cssClass="form-control" path="fullName"  />
								<form:errors cssStyle="color:red;padding-top:10px" path="fullName"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-2 d-flex">Số điện thoại: <p style="color:red;margin:0 5px ">*</p></label>
							<div class="col-sm-11">
								<form:input cssClass="form-control" path="phone" />
								<form:errors cssStyle="color:red;padding-top:10px" path="phone"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Địa chỉ:</label>
							<div class="col-sm-11">
								<form:input cssClass="form-control" path="address" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Trạng thái:</label>
							<div class="col-sm-11">
								<div class="radio">
								    <label>
								        <form:radiobutton path="status" value="1" fn:checked="${model.status == 1 ? 'checked' : ''}" />
								            Nhân viên đang làm việc
								    </label>
								</div>
								<div class="radio">
								    <label>
								        <form:radiobutton path="status" value="0" fn:checked="${model.status != 1 ? 'checked' : ''}" />

								            Nhân viên đã nghỉ việc
								    </label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">Ảnh nhân viên:</label>
							<div class="col-sm-11">
								<form:hidden path="image" />
								<form:input style="padding:5px" type="file" cssClass="form-control image-upload" path="photo" accept="image/*"
									onchange="document.getElementById('Photo').src = window.URL.createObjectURL(this.files[0])" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-offset-2 col-sm-11">
		
								<img id="Photo"
									src="<c:url value='${model.image}'/>"
									class="img img-bordered" style="width: 200px;border-radius:5px;border: 1px #7a6f6f solid" />
							</div>
						</div>

						<div class="form-group text-right">
							<div class="col-lg-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary">
									<i class="fa fa-floppy-o"></i> Lưu dữ liệu
								</button>
								<a href="/hotel/quan-tri/nhan-vien" class="btn btn-secondary"> Quay lại </a>
							</div>
						</div>
					</div>

					</form:form>
				</div>
			</div>
		</div>
	</div>

</div>


