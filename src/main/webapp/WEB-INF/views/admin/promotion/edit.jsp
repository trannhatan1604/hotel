<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/taglib.jsp" %>

<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800"></h1>


	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">${model.id == 0 ? "Thêm" : "Chỉnh sửa"}  khuyến
				mãi</h6>
		</div>
		<div class="card-body">
			<div class="">
				<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
				<form:form action="/hotel/quan-tri/khuyen-mai/luu" method="post" modelAttribute="model">
					<div class="column">
						<!-- <div class="col-sm-12 col-md-6">
							
						</div>
						<div class="col-sm-12 col-md-6">
							
						</div> -->
						<form:input type="hidden" class="form-control" path="id" />
						<div class="row">
							<div class="form-group col">
								<label class="control-label col-6 d-flex">Tên khuyến mãi: <p style="color:red;margin:0 5px ">*</p></label>
								<div class="col-sm-12">
									<form:input cssClass="form-control" path="name" autofocus="autofocus"/>
									<form:errors cssStyle="color:red;padding-top:10px" path="name"/>
								</div>
							</div>
							<div class="form-group col">
							<label class="control-label col-6 d-flex">Thời gian: </label>
								<div class="col-sm-12">
									<form:input cssClass="form-control" type="text" path="dateRange" />
									<form:errors cssStyle="color:red;padding-top:10px" path="dateRange"/>
								</div>
								<%-- ${model.startdate.toString().replace('-', '/')} - ${model.enddate.toString().replace('-', '/')} --%>
							</div>
						</div>

						<div class="row">
							
							<div class="form-group col">
							<label class="control-label col-6 d-flex">Giá trị giảm:  <p style="color:red;margin:0 5px ">*</p></label>
								<div class="col-sm-12">
									<form:input cssClass="form-control" type="number" min="1" path="level"  />
									<form:errors cssStyle="color:red;padding-top:10px" path="level"/>
								</div>
								
							</div>
						</div>
						<div class="row">
							
							<div class="form-group col">
							<label class="control-label col-sm-6">Mô tả:</label>
								<div class="col-sm-12">
									<form:input cssClass="form-control" type="text" path="description" />
								</div>
							</div>
						</div>
						<div class="form-group d-flex justify-content-end" style="padding: 0 12px;">
							<div class="">
								<form:button type="submit" class="btn btn-primary">
									<i class="fa fa-floppy-o"></i> Lưu dữ liệu
								</form:button>
								<a onclick="history.back()" class="btn btn-secondary">Quay lại </a>
							</div>
						</div>
					</div>
				</form:form>
				</div>
			</div>
		</div>
	</div>

</div>


