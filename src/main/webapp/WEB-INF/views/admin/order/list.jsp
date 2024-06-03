<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/quan-tri/phieu-dat/tim-kiem" var="searchOrder" />
<div class="container-fluid">
	<c:if test="${not empty errorMessage}">
		<div class="alert alert-danger position-fixed mx-auto"
			style="top: 30px; left: 45%" role="alert">
			<button type="button" class="close ml-3" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			${errorMessage}
		</div>

	</c:if>
	<!-- DataTales Example -->
	<div class="card shadow mb-4 mt-2">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Quản lý danh sách
				phiếu đặt phòng</h6>
		</div>

		<div class="card-body">
			<div class="">
				<form id="formSearch" action="${searchOrder }" method="post"
					data-container="#searchResult">
					<input type="hidden" name="page" id="page" /> <input type="hidden"
						name="limit" id="limit" />
					<div id="dataTable_wrapper"
						class="dataTables_wrapper dt-bootstrap4">
						<div class="row ml-1 mb-4 mr-3 ">
							<div class="col-md-2 mr-2 pl-0">
							<select
								class="custom-select form-control mt-4"
								name="statusId" onchange="doSearch('#formSearch', this.value);;resetPageAndSubmit();">
								<option value="0">Trạng thái...</option>
								<c:forEach var="entry" items="${orderStatuses}">
									<option value="${entry.key}">${entry.value}</option>
								</c:forEach>
							</select></div>
							
							<div class="col-md-2 mr-3">
								Ngày đến
								<div>
									<div class="row">
										<input type="date" class="form-control" name="checkinDate"
											onchange="doSearch('#formSearch', this.value);resetPageAndSubmit();">
									</div>
								</div>
							</div>
							<div class="col-md-2 mr-3 ">
								Ngày đi
								<div>
									<div class="row">
										<input type="date" class="form-control" name="checkoutDate"
											onchange="doSearch('#formSearch', this.value);resetPageAndSubmit();">
									</div>
								</div>
							</div>
							<div class="col-sm-12 col-md-5">
								<div id="dataTable_filter" class="dataTables_filter">
									<div>
										<div class="row mt-4">
											<input type="search" name="searchValue"
												class="form-control col-lg-10"
												placeholder="Tìm theo khách hàng hoặc phòng..."
												aria-controls="dataTable">
											<div class="input-group-append col-lg-2">
												<button class="btn btn-primary">
													<i class="fas fa-search fa-sm"></i>
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="searchResult"></div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
	document.getElementById("page").value = ${model.page};
	document.getElementById("limit").value = ${model.limit};
	//xử lý tìm kiếm select đưa về lại trang 1
    function resetPageAndSubmit() {
        // Đặt giá trị của phần tử có id là "page" và "limit" về ${model.page} và ${model.limit} tương ứng
        document.getElementById("page").value = 1;
        document.getElementById("limit").value = ${model.limit};

        // Gửi biểu mẫu tìm kiếm khi thay đổi lựa chọn
        $("#formSearch").submit();
    }
	  $(document).ready(function () {
	        $("#formSearch").submit(function (e) {
	            e.preventDefault();
	            doSearch(this, 1);
	        });
	        // Initially load search results
	        doSearch("#formSearch", ${model.page});
	    });

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
	                formatAllPrices();
	            }
	        });
	    }
	    
	    window.setTimeout(function() {
			$(".alert").fadeTo(500, 0).slideUp(1000, function() {
				$(this).remove();
			});
		}, 1500);
</script>
</div>
