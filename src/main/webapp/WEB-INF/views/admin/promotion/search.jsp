<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="table">
	<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
		<div class="row">
			<c:forEach items="${model.listResult}" var="promotion">
				<div class="col-lg-6">
					<div class="card mb-4 py-3 border-left-info">
						<div class="card-body row pr-2 py-1" style="min-height: 280px">
							<div class="d-flex flex-column col-lg-12 p-1">
								<h2 class="d-flex align-items-center text-primary font-weight-bold ">
									<span class="badge badge-pill badge-primary">${promotion.level}%</span>
									<span class="text-primary text-uppercase ml-3">${promotion.name}</span>
								</h2>
								<h4 class="mt-4">Mô tả chi tiết:</h4>
								<p>${promotion.description}</p>
								<h6 class="font-italic text-danger font-weight-bolder">Có
									giá trị từ: ${promotion.startdate} đến ${promotion.enddate}</h6>
							</div>
							<div class="col-12 d-flex flex-column pr-3 justify-content-end">
								<!-- Thêm justify-content-end vào đây -->
								<div class="d-flex justify-content-end">
									<a
										href="/hotel/quan-tri/khuyen-mai/chinh-sua?id=${promotion.id}"
										class="btn btn-sm btn-primary btn-modal w-25 mb-1"> <i
										class="fa fa-edit"></i>
									</a>
								</div>
								<c:if test="${promotion.getAllowDelete()}">
									<div class="d-flex justify-content-end">
										<a href="/hotel/quan-tri/khuyen-mai/xoa?id=${promotion.id}"
											class="btn btn-sm btn-danger w-25 mb-1"
											onclick="return confirm('Xóa khuyến mãi này ?')"> <i
											class="fa fa-trash"></i>
										</a>
									</div>
								</c:if>		
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="row">
			<div class="col-sm-12 col-md-5">
				<div class="dataTables_info" id="dataTable_info" role="status"
					aria-live="polite">Hiển thị trang ${model.page} trên
					${model.totalPage} của ${model.totalItem} khuyến mãi</div>
			</div>
			<div class="col-sm-12 col-md-7">
				<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
					<ul id="pagination" class="pagination"></ul>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	var totalPages = ${model.totalPage};
	var currentPage = ${model.page};
	$(function () {
		document.getElementById("page").value = ${model.page};
		document.getElementById("limit").value = ${model.limit};
window.scrollTo({top: 0, behavior: 'smooth'})
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
