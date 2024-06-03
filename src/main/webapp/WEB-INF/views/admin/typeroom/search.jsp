<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-sm-12">
		<c:if test="${empty model.getListResult()}">
			<div class="alert alert-info" role="alert">Không tìm thấy phiếu
				đặt nào.</div>
		</c:if>
		<c:if test="${not empty model.getListResult()}">
		<table class="table table-bordered" id="dataTable" width="100%"
			cellspacing="0">
			<thead>
				<tr>
					<th class="text-center">Ảnh</th>
					<th class="text-center">Tên loại phòng</th>
					<th class="text-center">Số lượng người</th>
					<th class="text-center">Giá phòng</th>
					<th class="text-center">Kích thước</th>
					<th class="text-center">Mô tả</th>
					<th style="width: 120px"><a
						href="<c:url value='/quan-tri/loai-phong/them'/>"
						style="margin: 0 auto;"
						class="text-center btn btn-xs btn-success btn-sm"> <i
							class="fa fa-add"></i>
					</a></th>
				</tr>
			</thead>
			<tfoot>
				<tr style="white-space: nowrap;">
					<th class="text-center">Ảnh</th>
					<th class="text-center">Tên loại phòng</th>
					<th class="text-center">Số lượng người</th>
					<th class="text-center">Giá phòng</th>
					<th class="text-center">Kích thước</th>
					<th class="text-center">Mô tả</th>
					<th style="width: 120px"></th>
				</tr>
			</tfoot>
			<tbody>

				<c:forEach items="${model.getListResult()}" var="item">
					<tr>
						<td>
							<img class="profile-user-img img-responsive img-bordered lazy-load" style=" width: 200px;height: 180px; 
            																							object-fit: cover;"
						        src="<c:url value='${item.image }'/>" />
						</td>
						<td>${item.name }</td>
						<td>${item.quantity }</td>
						<td>${item.priceFormat }</td>
						<td>${item.space } m<sup>2</sup></td>
						<td ><p style="height:180px; overflow-y: auto; text-overflow: ellipsis;margin: 0;scrollbar-color: #4e73df #fff;">${item.description } </p></td>
						<td><a
							href="<c:url value = '/quan-tri/loai-phong/chinh-sua?id=${item.id }'/>"
							class="btn btn-xs btn-primary btn-modal"> <i
								class="fa fa-edit"></i>
						</a> <a
							href="<c:url value='/quan-tri/loai-phong/xoa?id=${item.id }'/>"
							class="btn btn-xs btn-danger"
							onclick="return confirm('Xóa loại phòng này ?')"> <i
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
	<div class="col-sm-12 col-md-5">
		<div class="dataTables_info" id="dataTable_info" role="status"
			aria-live="polite">Hiển thị trang ${model.page } trên
			${model.totalPage } của ${model.totalItem} Loại phòng</div>
	</div>
	<div class="col-sm-12 col-md-7">
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
console.log(totalPages);
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