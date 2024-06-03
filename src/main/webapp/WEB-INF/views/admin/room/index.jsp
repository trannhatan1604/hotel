<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value='/quan-tri/phong/tim-kiem' var="searchRoom" />
<div class="container-fluid">
	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800"></h1>
	<form:form action="${searchRoom }" modelAttribute="model"
		id="formSearch" method="post" data-container="#searchResult">
		
		<form:hidden path="page" id="page"/>
		<form:hidden path="limit" id="limit"/>
		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Quản lý phòng</h6>
			</div>
			<div class="card-body">
				<div class="">
					<div id="dataTable_wrapper"
						class="dataTables_wrapper dt-bootstrap4">
						<div class="row pb-4">
							<div class="col-sm-12 col-md-6">
								<div class="dataTables_length" id="dataTable_length">
									<label><%-- <select
										class="custom-select custom-select-sm form-control form-control-sm"
										name="typeid">
											<option value="0" >---Chọn loại phòng---</option>
											<c:forEach items="${typerooms}" var="typeroom">
												<option value="${typeroom.id}"
													${typeroom.id == model.typeid ? 'selected' : ''}>${typeroom.name }</option>
											</c:forEach>
									</select> --%> <form:select path="typeid"
											cssClass="custom-select custom-select-sm form-control form-control-sm" onchange="doSearch('#formSearch', this.value);resetPageAndSubmit();">
											<form:option value="0" label="--- Chọn loại phòng ---" />
											<form:options items="${typerooms }" />
										</form:select>
									</label>
								</div>
							</div>
							<div class="col-sm-12 col-md-6">
								<div id="dataTable_filter" class="dataTables_filter">
									<div>
										<!-- 	<form action="#" class="row" style="margin-left: 3px;"> -->
										<div class="row"><!-- 
											<input type="search" name="searchValue"
												class="form-control form-control-sm col-lg-10"
												placeholder="" aria-controls="dataTable"> -->
											<form:input path="searchValue" placeholder="Tìm kiếm theo tên phòng" aria-controls="dataTable" cssClass="form-control form-control-sm col-lg-10"/>
											<div class="input-group-append col-lg-2">
												<button class="btn btn-primary btn-sm">
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
		</div>

	</form:form>
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
	            }
	        });
	    }
</script>
</div>



