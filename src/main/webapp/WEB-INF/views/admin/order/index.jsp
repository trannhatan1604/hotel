<%@page import="com.hotel.util.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url value='/quan-tri/dat-phong/luu' var="addOrder" />
<c:url value='/quan-tri/dat-phong/danh-sach-phong' var="searchRoom" />
<style>
.list-room {
	max-height: 100vh;
	overflow-y: scroll;
	scroll-snap-type: y mandatory;
}

.item-room {
	border-width: 2px !important;
	scroll-snap-align: start;
}

.booking-periods {
 display: none;
    position: absolute;
    width: 64%;
    top: 10px;
    left: 10px;
    right: 0;
    background: white;
    border: 1px solid #ccc;
    z-index: 1000;
    opacity: 0;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Thêm box-shadow */
    border-radius: 5px; /* Bo tròn viền */
    padding: 10px; /* Khoảng cách giữa nội dung và viền */
    transition: opacity 0.3s ease;
  
}

.item-room:hover .booking-periods {
    display: block;
    opacity: 1;
      transition: opacity 0.3s ease;
    box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1); /* Đổ bóng */
}

.booking-periods table {
    width: 100%;
    border-collapse: collapse;
}

.booking-periods th, .booking-periods td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: center;
}

.booking-periods th {
    background-color: #f2f2f2;
}
.past-date {
    background-color: #f2f2f2;
    color: #999;
}

.future-date {
    background-color: #d4edda;
    color: #155724;
}
</style>
<div id="modalOverlay"
	style="position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); z-index: 1050; display: none;"></div>

<div class="container-fluid">
	<div class="card-header py-3">
		<h6 class="m-0 font-weight-bold text-primary">Quản lý đặt phòng</h6>
	</div>
	<form id="formSearch"
		class="col-12 d-flex justify-content-center mt-4 mx-2 shadow px-1 py-3 rounded"
		method="post" action="${searchRoom }" data-container="#searchResult">
		<input type="hidden" class="" name="roomId" value="" id="fromRoomId">
		<input type="hidden" value="" id="page" name="page" /> <input
			type="hidden" value="" id="limit" name="limit" /> <input
			type="hidden" value="${roomModel.getTotalPage() }" id="totalPage"
			name="totalPage" />
		<div class="col-12 form-inline mx-auto  mb-1">
			<select class="custom-select form-control col-md-2 mb-md-0 mr-2"
				name="typeid" onchange="doSearch('#formSearch', 1)">
				<option value="0">Chọn loại phòng...</option>
				<c:forEach var="entry" items="${typerooms}">
					<option value="${entry.key}"
						${entry.key == model.typeid ? 'selected' : ''}>${entry.value}</option>
				</c:forEach>
			</select> 
			<select class="custom-select form-control col-md-2 mb-md-0 mr-2"
				name="quantity" onchange="doSearch('#formSearch', 1)">
				<option value="0">Chọn số lượng...</option>
				<c:forEach var="entry" items="${quantitys}">
					<option value="${entry.key}"
						${entry.key == model.typeid ? 'selected' : ''}>${entry.value}</option>
				</c:forEach>
			</select>
			<div class="form-group col-md-4 col-12 mb-md-0 mb-2 pr-0">
				<input type="text" class="form-control col-sm-12 col-md-10"
					placeholder="Tìm phòng theo tên..." name="searchValue">

				<div class="form-group col-md-2 col-sm-12 mb-md-0 p-0 pl-1">
					<button class="btn btn-primary  " type="submit">
						<i class="fas fa-search fa-sm"></i>
					</button>
				</div>
			</div>
		</div>
	</form>


	<div class="mt-5 col-12 d-flex flex-md-row flex-column mx-0">
		<div id="searchResult" class="col-md-4 col-12"></div>
		<div class="col-md-8 col-12">
			<h2 class="mb-3 font-weight-bold">Thông tin đặt phòng</h2>
			<form id="formSearchCustomer" class="shadow p-3 rounded mb-3"
				method="post"
				action="<c:url value='/quan-tri/dat-phong/tim-khach-hang'/>">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="phoneNumber">Số điện thoại</label> <input type="text"
							class="form-control" name="phoneNumber" id="phoneNumber">
						<span id="phoneNumberError" class="text-danger"></span>
					</div>
					<div class="form-group col-md-6">
						<label for="customerName">Khách hàng</label> <input type="text"
							class="form-control" id="customerName" name="customerName"
							readonly>
					</div>
					<input type="submit" value="Tìm khách hàng"
						class="btn btn-lg btn-primary col-12">
				</div>
			</form>
			<form:form modelAttribute="orderModel" id="formOrder"
				cssClass="shadow p-3 rounded" method="POST" action="${addOrder}">
				<form:hidden path='id' />
				<form:hidden path="roomId" id="formRoomId" />
				<form:hidden path="customerId" id="customerId" />
				<input type="hidden"
					value="<%=SecurityUtils.getPrincipal().getId()%>" name="employeeId" />
				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Ngày đến</label>
						<form:input path="checkinDate" id="checkinDate"
							cssClass="form-control" type="date" />
						<form:errors path="checkinDate"
							cssStyle="color:#e74a3b; padding-top:10px" />
					</div>
					<div class="form-group col-md-6">
						<label>Ngày đi</label>
						<form:input path="checkoutDate" id="checkoutDate"
							cssClass="form-control" type="date" />
						<form:errors path="checkoutDate"
							cssStyle="color:#e74a3b; padding-top:10px" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label>Số lượng</label>
						<form:input path="quantity" cssClass="form-control" type="number" />
						<form:errors path="quantity"
							cssStyle="color:#e74a3b; padding-top:10px" />

					</div>
					<div class="form-group col-md-6">
						<label>Khuyến mãi</label>
						<form:select path="promotionId" cssClass="form-control">
							<form:option value="0" label="Không áp dụng..." />
							<form:options items="${promotions }" />
						</form:select>
						<form:errors path="promotionId" cssClass="mt-2"
							cssStyle="color:#e74a3b; padding-top:10px" />
					</div>
				</div>
				<button type="submit"
					class="btn btn-lg form-control col-sm-12 btn-primary d-block mx-auto">Đặt
					phòng</button>
				<form:errors path="roomId" cssStyle="color:#e74a3b; margin-top:10px" />
			</form:form>
		</div>
	</div>
</div>

<a class="dropdown-item" href="#" id="btnShowModal" type="hidden"
	data-toggle="modal" data-target="#logoutModal" style="display: none">
	<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
	Logout
</a>
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
				<button class="close" type="button" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<div class="modal-body">Khách hàng chưa tồn tại trong danh sách</div>
			<div class="modal-footer">
				<button class="btn btn-secondary" type="button" data-dismiss="modal">Hủy</button>
				<a class="btn btn-primary"
					href="<c:url value='/quan-tri/khach-hang/chinh-sua'/>">Thêm mới</a>
			</div>
		</div>
	</div>
</div>
<script>
		//Khong cho thao tac tren form luu
		$('#formOrder input, #formOrder button, #formOrder select').prop('disabled', true);
		document.getElementById("page").value = ${roomModel.page};
		document.getElementById("limit").value = ${roomModel.limit};
		document.getElementById("totalPage").value = ${roomModel.totalPage};
		$(document).ready(function () {
			$("#logoutModal .close, #logoutModal .btn-secondary").click(function () {
		        $("#logoutModal").removeClass("fade show");
		        $("#logoutModal").removeAttr("tabindex aria-labelledby aria-modal role style");
		        $("#modalOverlay").hide();
		    });
		    $("#formSearch").submit(function (e) {
		        e.preventDefault();
		        doSearch(this, 1);
		    });
	    
		    $('#formSearchCustomer').submit(function(e) {
		        e.preventDefault();
		        searchCustomer(this);
		    });
		 /*    $('#formOrder').submit(function(e) {
		        e.preventDefault();
		        saveOrder(this);
		    }); */
		    // Initially load search results
		    doSearch("#formSearch", ${roomModel.page});
		});

		    function doSearch(formSearch, page) {
		        var action = $(formSearch).prop("action");
		        var method = $(formSearch).prop("method");
		        var container = $(formSearch).data("container");
		        // Set new page and limit values
		       
		        if (page <= 0) {
		            page = 1;
		        }
				
		        var totalPage = parseInt($("#totalPage").val());
		        if (totalPage > 0 && page >= totalPage) {
		            page = totalPage;
		        } 
		        
		        $("#page").val(page);
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
		    function searchCustomer(formSearch) {
		        var action = $(formSearch).prop("action");
		        var method = $(formSearch).prop("method");
		        var phoneNumber = $("#phoneNumber").val();

		        $.ajax({
		            url: action,
		            type: method,
		            data: { phoneNumber: phoneNumber }, // Dữ liệu được gửi đi, trong trường hợp này là phoneNumber
		            dataType: "json",
		            success: function(data) {
		            	console.log("Name người dùng: " + data.fullname);
		            	if (data.fullName) {
		                    $("#customerName").val(data.fullName);
		                    $("#customerId").val(data.id);
		                    // Enable form fields and buttons
		                    $('#formOrder input, #formOrder button, #formOrder select').prop('disabled', false);
		                    
		                } else {
		                	//nếu không tìm được người dùng
		                	$('#formOrder input, #formOrder button, #formOrder select').prop('disabled', true);
                			$("#customerName").val('');
		                	   if (data.message) {
		                           $("#phoneNumberError").text(data.message); // Display error message
		                       } else {
		                    	// Disable form fields and buttons if customer not found
				                    $("#logoutModal").addClass("fade show");
				                    $("#logoutModal").attr({
				                        "tabindex": "-1",
				                        "aria-labelledby": "exampleModalLabel",
				                        "aria-modal": "true",
				                        "role": "dialog"
				                    });
				                    $("#logoutModal").css({
				                        "display": "block",
				                        "padding-right": "17px"
				                    });
				                    $("#modalOverlay").show(); // Hiển thị overlay
		                       }
		                    
		                }
		                
		            },
		            error: function() {
		                alert("Yêu cầu tìm khách hàng của bạn không hợp lệ!");
		            }
		        });
		    }

       
    </script>