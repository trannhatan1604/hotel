<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid mt-4">
	<!-- DataTales Example -->
	<form action="<c:url value='/quan-tri/phan-hoi/tim-kiem'/>"
		id="formSearch" method="post" data-container="#searchResult">
		<input type="hidden" value="" id="page" name="page" /> <input
			type="hidden" value="" id="limit" name="limit" />
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<h6 class="m-0 font-weight-bold text-primary">Xử lý phản hồi</h6>
		</div>
		<div class="card-body">
			<div class="table">
				<div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
					<div class="row">

						<div class="col-sm-12 col-md-3">
							<div class="dataTables_length" id="dataTable_length">
								<label>Tình trạng yêu cầu:
								<select class="custom-select custom-select-sm form-control form-control-sm"
										name="status" onchange="doSearch('#formSearch', 1);resetPageAndSubmit();"><option
											value="0">-- Trạng thái --</option>
										<option value="1">Chưa xử lý</option>
										<option value="2">Đã xử lý</option>
										<option value="-1">Từ chối xử lý</option></select>
								</label>
							</div>
						</div>
						<!-- <div class="col-sm-12 col-md-9">
							<div id="dataTable_filter" class="dataTables_filter">
								<label>Tìm kiếm:
									<div class="row" >
										<input type="search" name="searchValue"
											class="form-control form-control-sm col-lg-10" 
											placeholder="" aria-controls="dataTable">
										<div class="input-group-append col-lg-2">
											<button class="btn btn-primary btn-sm" type="submit">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</label>
							</div>
						</div> -->
						
					</div>
					<hr class="sidebar-divider d-none d-md-block">
					<div id="searchResult"></div>

				</div>
			</div>
		</div>
	</div>
	</form>
</div>

<script>
	document.getElementById("page").value = ${model.page};
	document.getElementById("limit").value = ${model.limit};
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
			            }
			        });
			    }
</script>
