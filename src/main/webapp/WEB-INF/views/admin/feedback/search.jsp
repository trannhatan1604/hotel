<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<c:forEach items="${model.getListResult()}" var="feedback">
		<div class="col-lg-3 mb-4">
			<div class="">
				<div class="card " style="width: 18rem; min-height: 300px;">
					<div
						class="card-header d-flex justify-content-between align-items-center pl-3 pb-1 pt-2">
						<h3 class="m-0 font-weight-bold text-primary">${feedback.roomName }</h3>
					</div>
					<div class="card-body border-bottom-info">
						<div class="figure-caption">Khách hàng:</div>
						${feedback.accountName }
						<p class="card-text">
							<!-- Thêm một lớp mới -->
						<div class="figure-caption">Mô tả:</div>
						<p style="min-height: 50px;">${feedback.description }</p>
						</p>
						
						<c:choose>
							<c:when test="${feedback.status == 1}">
								<a href="<c:url value='/quan-tri/phan-hoi/chinh-sua?id=${feedback.id }&status=2'/>" class="btn btn-primary btn-icon-split" onclick="return confirm('Đã xác nhận phản hồi ?')"> <span
									class="icon text-white-50"> <i class="fas fa-flag"></i>
								</span> <span class="text" >Xử lý</span>
								</a>
								<a href="<c:url value='/quan-tri/phan-hoi/chinh-sua?id=${feedback.id }&status=-1'/>" class="btn btn-danger btn-icon-split" onclick="return confirm('Từ chối phản hồi này ?')"> <span
									class="icon text-white-50"> <i class="fas fa-trash"></i>
								</span> <span class="text">Từ chối</span>
								</a>
							</c:when>
							<c:when test="${feedback.status == 2}">
								<div href="#" class="btn btn-success btn-icon-split"> <span
									class="icon text-white-50"> <i class="fas fa-trash"></i>
								</span> <span class="text">Đã xử lý</span>
								</div>
							</c:when>
							<c:otherwise>
								<div class="btn btn-secondary btn-icon-split"> <span
									class="icon text-white-50"> <i class="fas fa-trash"></i>
								</span> <span class="text">Đã từ chối</span>
								</div>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			</div>
		</div>
	</c:forEach>


</div>
<div class="row">
	<div class="col-sm-12 col-md-6">
		<div class="dataTables_info" id="dataTable_info" role="status"
			aria-live="polite">Hiển thị trang ${model.page } trên
			${model.totalPage } của ${model.totalItem} Phản hồi</div>
	</div>
	<div class="col-sm-12 col-md-6">
		<div class="dataTables_paginate paging_simple_numbers"
			id="dataTable_paginate">
			<ul class="pagination" id="pagination"></ul>
		</div>
	</div>
</div>
<script>
var totalPages = ${model.totalPage};
var currentPage = ${model.page};
$(function () {
	document.getElementById("page").value = ${model.page};
	document.getElementById("limit").value = ${model.limit};
    window.pagObj = $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 3,
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