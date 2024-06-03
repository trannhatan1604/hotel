<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/common/taglib.jsp"%>

<div class="container-fluid">

	<!-- DataTales Example -->
	<form class="card shadow mb-4" action="<c:url value='/quan-tri/khuyen-mai/tim-kiem'/>" id="formSearch" method="post" data-container="#searchResult">
		<input type="hidden" value="" id="page" name="page" /> 
		<input type="hidden" value="" id="limit" name="limit" />
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Quản lý khuyến mãi</h6>
		</div>
		
		<div class="card-body">
			<div class="d-flex justify-content-between">
				<div class="form-group col-md-4">
					<label>Tình trạng</label> 
					<select class="form-control" name="status" onchange=" resetPageAndSubmit();">
						<option value="" selected>Tất cả</option>
						<option value="isAvailable">Đang áp dụng</option>
						<option value="unused">Chưa áp dụng</option>
						<option value="expire">Đã hết</option>
					</select>
				</div>
				<div>
					<a href="/hotel/quan-tri/khuyen-mai/them"
						style="margin-left: auto;"
						class="text-center btn btn-xs btn-success mb-3"> <i
						class="fa fa-add"></i> Thêm khuyến mãi
					</a>
				</div>
			</div>
			<div id="searchResult"></div>
		</div>
	</form>
	
	<script>
		document.getElementById("page").value = ${model.page};
		document.getElementById("limit").value = ${model.limit};
		$(document).ready(function () {
	        $("#formSearch").submit(function (e) {
	            e.preventDefault();
	            doSearch(this, 1);
	        });
	        // Initially load search results
	        doSearch("#formSearch", ${model.page});
	    });
		
		function resetPageAndSubmit() {
	        // Đặt giá trị của phần tử có id là "page" và "limit" về ${model.page} và ${model.limit} tương ứng
	        document.getElementById("page").value = 1;
	        document.getElementById("limit").value = ${model.limit};

	        // Gửi biểu mẫu tìm kiếm khi thay đổi lựa chọn
	        $("#formSearch").submit();
	    }
	
	    function doSearch(formSearch, page) {
	        var action = $(formSearch).prop("action");
	        var method = $(formSearch).prop("method");
	        var container = $(formSearch).data("container");
	
	        // Include page information in search data
	        var searchData = $(formSearch).serializeArray();
	
	        $.ajax({
	            url: action,
	            type: method,
	            data: searchData,
	            error: function () {
	                alert("Your request is not valid!");
	            },
	            success: function (data) {
	                $('#searchResult').html(data);
	            }
	        });
	    }
	</script>

</div>


