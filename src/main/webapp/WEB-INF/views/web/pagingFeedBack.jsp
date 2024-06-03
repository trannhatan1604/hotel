<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<h4 style="font-weight: bold">Phản hồi của khách hàng :</h4>
	<c:forEach items="${model.getListResult()}" var="item">
		<div class="row py-4 d-flex justify-content-center">
			<div class="col-3 p-3"
				style="border-radius: 8px; background: #d8d6d663; height-line: 20px; font-size: 17px">
				<div>
					<i class="fa-regular fa-user pr-3"></i>Khách hàng ${item.accountName}
				</div>
				<div class="d-flex align-items-start mt-3">
					<i class="fa-solid fa-person-booth pr-3 pt-1"></i>${item.typeRoomName }
				</div>
			</div>
			<div class="col-7 p-3 ml-5"
				style="border-radius: 8px; background: #009cffb8; height-line: 20px; font-size: 17px; color: #fff">
				<div style="height-max: 80px; display: flex; margin-bottom: 15px">
					<i class="fa-regular fa-comment mt-1 pr-3"></i>
					<div style="line-height: 1.5">${item.description }</div>
				</div>
				<p style="color: #333">Thời gian phản hồi : 2024-10-16</p>
			</div>
		</div>
	</c:forEach>

</div>
<div class="row">
	
	<div class="col-sm-12 col-md-7 mx-auto">
		<div class="dataTables_paginate paging_simple_numbers"
			id="dataTable_paginate">
			<ul class="pagination d-flex justify-content-center pt-3" id="pagination"></ul>
			
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