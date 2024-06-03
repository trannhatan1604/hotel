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
						<th class="text-center">Khách hàng</th>
						<th class="text-center">Ngày đến</th>
						<th class="text-center">Ngày đi</th>
						<th class="text-center">Giá</th>
						<th class="text-center" width="180px">Trạng thái</th>
						<th style="width: 120px"><a
							href="<c:url value='/quan-tri/dat-phong'/>"
							style="margin: 0 auto;"
							class="text-center btn btn-xs btn-success btn-sm"> <i
								class="fa fa-add"></i>
						</a></th>
					</tr>
				</thead>
				<tfoot>
					<tr>
						<th class="text-center">Khách hàng</th>
						<th class="text-center">Ngày đến</th>
						<th class="text-center">Ngày đi</th>
						<th class="text-center">Giá</th>
						<th class="text-center">Trạng thái</th>
						<th style="width: 120px"></th>
					</tr>
				</tfoot>
				<tbody>
					<c:forEach items="${model.getListResult()}" var="item">
						<tr>
							<td>${item.getCustomer().getFullName() }</td>
							<td class="text-center">${item.getCheckinDate()}</td>
							<td class="text-center">${item.getCheckoutDate()}</td>
							<td class="total-price text-center">${item.getTotalPrice()}</td>
							<td><c:if
									test="${item.getStatusId() eq 1 || item.getStatusId() eq 6}">
									<div class="badge badge-success d-flex justify-content-center"
										style="padding: 5px"">Đã thanh toán</div>
								</c:if> <c:if test="${item.getStatusId() eq 2}">
									<div class="badge badge-warning d-flex justify-content-center"
										style="padding: 5px"">Chưa thanh toán</div>
								</c:if> <c:if test="${item.getStatusId() eq 3}">
									<div class="badge badge-danger d-flex justify-content-center"
										style="padding: 5px"">Hủy</div>
								</c:if>
								<c:if test="${item.getStatusId() eq 4}">
									<div class="badge badge-warning d-flex justify-content-center"
										style="padding: 5px"">Chờ duyệt</div>
								</c:if>
								<c:if test="${item.getStatusId() eq 5}">
									<div class="badge badge-info d-flex justify-content-center"
										style="padding: 5px"">Đã duyệt</div>
								</c:if></td>
							<td>
									<a
										href="<c:url value='/quan-tri/phieu-dat/chinh-sua?id=${item.id }'/>"
										class="btn btn-xs btn-primary btn-sm"> <i
										class="fa fa-edit"></i>
									</a>
								<c:if
									test="${item.getStatusId() ne 1 && item.getStatusId() ne 6}">
									
									<a
										href="<c:url value='/quan-tri/phieu-dat/xoa?id=${item.id }'/>"
										class="btn btn-xs btn-danger btn-sm"
										onclick="return confirm('Xóa phòng này ?')"> <i
										class="fa fa-trash"></i>
									</a>
								</c:if></td>
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
			aria-live="polite">Hiển thị trang số ${model.page } trên
			${model.totalPage } của ${model.totalItem} Phiếu</div>
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

function formatMoney(number) {
	console.log("Tien ne... " + number)
    return number.toLocaleString('vi-VN') + ' đ';
}
function formatAllPrices() {
    var priceElements = document.querySelectorAll('.total-price');
    priceElements.forEach(function(element) {
        var price = parseFloat(element.textContent);
        console.log(element.textContent);
        console.log(price);
        element.textContent = formatMoney(price);
    });
}
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