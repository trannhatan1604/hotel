<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">

	<div class="col-sm-12">
		<c:if test="${empty model.getListResult()}">
			<div class="alert alert-info" role="alert">Không tìm thấy nhân viên nào.</div>
		</c:if>
		<c:if test="${not empty model.getListResult()}">
		<table class="table table-bordered" id="dataTable" width="100%"
			cellspacing="0">
			<thead>
				<tr>
					<th class="text-center">Ảnh</th>
					<th class="text-center">Tên nhân viên</th>
					<th class="text-center">Số điện thoại</th>
					<th class="text-center">Địa chỉ</th>
					<th class="text-center">Trạng thái</th>
					<th style="width: 120px"><a
						href="<c:url value='/quan-tri/nhan-vien/chinh-sua'/>"
						style="margin: 0 auto;"
						class="text-center btn btn-xs btn-success btn-sm"> <i
							class="fa fa-add"></i>
					</a></th>


				</tr>
			</thead>
			<tfoot>
				<tr>
					<th class="text-center">Ảnh</th>
					<th class="text-center">Tên nhân viên</th>
					<th class="text-center">Số điện thoại</th>
					<th class="text-center">Địa chỉ</th>
					<th class="text-center">Trạng thái</th>
					<th style="width: 120px"></th>
				</tr>
			</tfoot>
			<tbody>
				<c:forEach items="${model.getListResult()}" var="employee">

					<tr>
						<td class="text-center" ><img width="150px"
							class="profile-user-img img-responsive img-bordered"
							src="<c:url value='${employee.image }'/>" /></td>
						<td>${employee.fullName }</td>
						<td>${employee.phone }</td>
						<td>${employee.address }</td>

						<c:if test="${employee.status == 1}">
							<td><div class="badge badge-success d-flex justify-content-center" style="padding:5px">Đang làm việc</div></td>
						</c:if>
						<c:if test="${employee.status != 1}">
							<td><div class="badge badge-danger d-flex justify-content-center" style="padding:5px">Đã nghỉ việc</div></td>
						</c:if>
						
						<td><a class="btn btn-xs btn-primary btn-sm" href="<c:url value='/quan-tri/nhan-vien/chinh-sua?id=${employee.id }'/>"
							> <i class="fa fa-edit"></i>
						</a> <a href="<c:url value='/quan-tri/nhan-vien/xoa?id=${employee.id }'/>" class="btn btn-xs btn-danger btn-sm"
							onclick="return confirm('Xóa nhân viên này ?')"> <i
								class="fa fa-trash"></i>
						</a></td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
		</c:if>
	</div>
</div>
<div class="row">
	<div class="col-sm-12 col-md-6">
		<div class="dataTables_info" id="dataTable_info" role="status"
				aria-live="polite">Hiển thị trang ${model.page } trên
				${model.totalPage } của ${model.totalItem} Nhân viên</div>
		</div>
	<div class="col-sm-12 col-md-6">
		<div class="dataTables_paginate paging_simple_numbers"
			id="dataTable_paginate">
			<ul class="pagination" id="pagination"></ul>
		</div>
	</div>
</div>

<script>
window.scrollTo({top: 0, behavior: 'smooth'})
var totalPages = ${model.totalPage};
var currentPage = ${model.page};
$(function () {
	document.getElementById("page").value = ${model.page};
	document.getElementById("limit").value = ${model.limit};
    window.pagObj = $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 5,
        startPage: currentPage,
        onPageClick: function (event, page) {
            if (currentPage != page) {
                $('#limit').val(${model.limit});
                $('#page').val(page);
                $('#formSearch').submit();
            }
        }
    });

});
</script>