<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid">
	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800"></h1>
	<form action="<c:url value='/quan-tri/nhan-vien/tim-kiem'/>"
		id="formSearch" method="post" data-container="#searchResult">
		<input type="hidden" value="" id="page" name="page" /> <input
			type="hidden" value="" id="limit" name="limit" />
		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Quản lý nhân viên</h6>
			</div>
			<div class="card-body">
				<div class="">
					<div id="dataTable_wrapper"
						class="dataTables_wrapper dt-bootstrap4">
						<div class="row" style="padding-bottom:10px">
							<div class="col-sm-12 col-md-6">
								<div class="dataTables_length" id="dataTable_length">
									
								</div>
							</div>
							<div class="col-sm-12 col-md-6">
								<div id="dataTable_filter" class="dataTables_filter">
									<!-- 	<form action="#" class="row" style="margin-left: 3px;"> -->
									<div class="row" style="margin-top:10px; margin-bottom:20px">
										<input type="search" name="searchValue"
											class="form-control form-control-sm col-lg-10" 
											placeholder="Tìm kiếm theo tên nhân viên" aria-controls="dataTable">
										<div class="input-group-append col-lg-2">
											<button class="btn btn-primary btn-sm" type="submit">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>

									<!-- </form> -->
								</div>
							</div>
						</div>
					</div>
					<div id="searchResult"></div>
				</div>
			</div>
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