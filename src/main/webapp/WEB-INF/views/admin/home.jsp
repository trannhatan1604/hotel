<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ page import="java.text.NumberFormat
"%>

<style>
.canvasjs-chart-credit {
	display: none !important
}

.card {
	border-radius: 12px;
}
</style>

<div class="container-fluid">

	<!-- Page Heading -->
	<div class="d-sm-flex align-items-center justify-content-between mb-4">

	</div>

	<!-- Content Row -->
	<div class="row">
		<!-- Room empty  -->
		<div class="col-xl-4 col-md-6 mb-4">
			<div class="card border-left-info shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xl font-weight-bold text-info text-uppercase mb-1">Phòng
								trống (${model.emptyRoom}/${model.totalRoom})</div>
							<div class="row no-gutters align-items-center">
								<div class="col-auto">
									<div
										class="h5 mb-0 mr-3 font-weight-bold text-xl text-gray-800">${model.percentRoomFree}%</div>
								</div>
								<div class="col">
									<div class="progress progress-sm mr-2">
										<div class="progress-bar bg-info" role="progressbar"
											style="width: ${model.percentRoomFree}%"
											aria-valuenow="${model.percentRoomFree}" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-door-open fa-2x text-info"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Revenue today -->
		<div class="col-xl-4 col-md-6 mb-4">
			<div class="card border-left-warning shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-md font-weight-bold text-warning text-uppercase mb-1">
								Doanh thu hôm nay</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${model.revenueToday}</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-dollar-sign fa-2x text-warning"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Revenue this month -->
		<div class="col-xl-4 col-md-6 mb-4">
			<div class="card border-left-success shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-md font-weight-bold text-success text-uppercase mb-1">
								Doanh thu tháng này</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${(model.revenueThisMonth) }</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-dollar-sign fa-2x text-success"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<!-- Promotion available -->
		<div class="col-xl-4 col-md-6 mb-4">
			<div class="card border-left-secondary shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-md font-weight-bold text-secondary text-uppercase mb-1">
								Khuyến mãi hiện có</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${model.availablePromotion.size()}</div>
							<div>
								<c:forEach items="${model.availablePromotion}" var="promotion">
									<li>${promotion.name}</li>
								</c:forEach>
							</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-tags fa-2x text-secondary"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- The most type room booked -->
		<div class="col-xl-4 col-md-6 mb-4">
			<div class="card border-left-primary shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-md font-weight-bold text-primary text-uppercase mb-1">
								Loại phòng đặt nhiều</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">${model.mostOrderTypeRoom}</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-calendar fa-2x text-primary"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Total guest arrival -->

		<div class="col-xl-4 col-md-6 mb-4">
			<div class="card border-left-danger shadow h-100 py-2">
				<div class="card-body">
					<div class="row no-gutters align-items-center">
						<div class="col mr-2">
							<div
								class="text-xl font-weight-bold text-danger text-uppercase mb-1">
								Khách hôm nay</div>
							<div class="h5 mb-0 font-weight-bold text-gray-800">Đến:
								${model.countOrderToday} | Đi: ${model.countCheckoutOrderToday}</div>
						</div>
						<div class="col-auto">
							<i class="fas fa-sign-in-alt fa-2x text-danger"></i>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div class="px-5 py-3 shadow-lg rounded-lg">
		<h1 class="text-center text-primary font-weight-bold my-3">
			<span class="badge badge-pill badge-primary">Đơn phòng hôm nay</span>
		</h1>
		<table class="table table-striped table-hover rounded">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Mã hoá đơn</th>
					<th scope="col">Người đặt</th>
					<th scope="col">Số lượng</th>
					<th scope="col">Phòng</th>
					<th scope="col">Ngày đến</th>
					<th scope="col">Ngày đi</th>
					<!-- <th scope="col"></th> -->
				</tr>
			</thead>
			<tbody>
				<c:if test="${model.newOrderList.size() == 0}">
					<tr>
						<td class="text-center" colspan="7">Chưa có đơn đặt phòng nào</td>
					</tr>
				</c:if>
				<c:forEach items="${model.newOrderList}" var="order">
					<tr>
						<td class="align-middle">${order.id}</td>
						<td class="align-middle">${order.getAccount().getFullName()}</td>
						<td class="align-middle">${order.getQuantity()}</td>
						<td class="align-middle">${order.getRoom().getName()}</td>
						<td class="align-middle">${order.checkinDate}</td>
						<td class="align-middle">${order.checkoutDate}</td>
						<!-- <td class="align-middle"><button
								class="btn btn-outline-danger">Duyệt</button></td> -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="mx-auto" style="width: fit-content">
			<a class="btn btn-dark mx-auto" href="/hotel/quan-tri/phieu-dat"><
				Tất cả hoá đơn ></a>
		</div>
	</div>

	<div class="px-5 py-3 shadow-lg rounded-lg mt-5">
		<h1 class="text-center text-primary font-weight-bold my-3">
			<span class="badge badge-pill badge-info">Trả phòng hôm nay</span>
		</h1>
		<table class="table table-striped table-hover rounded">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Mã hoá đơn</th>
					<th scope="col">Người đặt</th>
					<th scope="col">Số lượng</th>
					<th scope="col">Phòng</th>
					<th scope="col">Ngày đến</th>
					<th scope="col">Ngày đi</th>
					<th scope="col">Giá</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${model.orderTodayCheckoutList.size() == 0}">
					<tr>
						<td class="text-center" colspan="7">Hôm nay không có người
							trả phòng</td>
					</tr>
				</c:if>
				<c:forEach items="${model.orderTodayCheckoutList}" var="order">
					<tr>
						<td class="align-middle">${order.id}</td>
						<td class="align-middle">${order.getAccount().getFullName()}</td>
						<td class="align-middle">${order.getQuantity()}</td>
						<td class="align-middle">${order.getRoom().getName()}</td>
						<td class="align-middle">${order.checkinDate}</td>
						<td class="align-middle">${order.checkoutDate}</td>
						<td class="align-middle">${order.totalPrice}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="mx-auto" style="width: fit-content">
			<a class="btn btn-dark mx-auto" href="/hotel/quan-tri/phieu-dat"><
				Tất cả hoá đơn ></a>
		</div>
	</div>


	<div class="row pt-5">
		<!-- Line Chart -->
		<!-- Revennue Days -->
		<div class="col-xl-12 col-lg-7 position-relative">
			<form class="" action="<c:url value='/quan-tri/trang-chu/search'/>"
				id="formSearch" method="post" data-container="#dataSearch">
				<select
					style="position: absolute; right: 20px; top: 10px; width: fit-content; z-index: 100;"
					class="form-control" name="days" onchange="doSearch()">
					<option value="7" selected>7 ngày</option>
					<option value="15">15 ngày</option>
					<option value="30">30 ngày</option>
				</select>
				<div id="dataSearch"></div>
				<!-- 			<div id="chartRevenueRecentDays" class="shadow" style="height: 370px; width: 100%; border-radius: 10px; overflow: hidden;"></div> -->
			</form>
		</div>

		<!-- Revennue Months -->
		<div class="col-xl-12 col-lg-7 mt-5">
			<div id="chartLineRevenueMonths" class="shadow"
				style="height: 370px; width: 100%; border-radius: 10px; overflow: hidden;"></div>
		</div>

		<!-- Pie Chart -->
		<!-- Ratio typeroom booked -->
		<div class="col-md-6 mt-5">
			<div id="chartPieTypeRoom" class="shadow"
				style="height: 370px; width: 100%; border-radius: 10px; overflow: hidden;"></div>
		</div>

		<!-- Ratio revenue quater -->
		<div class="col-md-6 mt-5">
			<div id="chartPieQuarterRevenue" class="shadow"
				style="height: 370px; width: 100%; border-radius: 10px; overflow: hidden;"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#formSearch").submit(function(e) {
			e.preventDefault();
			doSearch(this);
		});
		// Initially load search results
		doSearch();
	});

	function doSearch() {
		var action = $(formSearch).prop("action");
		var method = $(formSearch).prop("method");
		var container = $(formSearch).data("container");

		// Include page information in search data
		var searchData = $(formSearch).serializeArray();

		$.ajax({
			url : action,
			type : method,
			data : searchData,
			error : function() {
				alert("Your request is not valid!");
			},
			success : function(data) {
				$('#dataSearch').html(data);
			}
		});
	}

	window.onload = function() {

		//Chart line revenue
		var dps = [ [] ];
		var chartLineRevenueMonths = new CanvasJS.Chart(
				"chartLineRevenueMonths", {
					theme : "light2", // "light1", "dark1", "dark2"
					animationEnabled : true,
					title : {
						text : "Doanh thu mỗi tháng",
						fontFamily : 'Nunito',
						fontColor : '#4e73df'
					},
					subtitles : [ {
						fontFamily : 'Nunito'
					} ],
					axisX : {
						valueFormatString : "Tháng ##",
						maximum : 12,
					},
					axisY : {
						title : "VND"
					},
					data : [ {
						type : "spline",
						xValueFormatString : "##",
						yValueFormatString : "#,##0 triệu",
						dataPoints : dps[0]
					} ]
				});

		var xValue;
		var yValue;

		<c:forEach items="${model.chartRevenueMonthsDataList}" var="dataPoints" varStatus="loop">
		<c:forEach items="${dataPoints}" var="dataPoint">
		xValue = "${dataPoint.x}";
		yValue = parseFloat("${dataPoint.y}");
		dps[parseInt("${loop.index}")].push({
			x : xValue,
			y : yValue
		});
		</c:forEach>
		</c:forEach>

		chartLineRevenueMonths.render();

		// Chart pie percent type room
		var dps = [ [] ];
		var chartPieTypeRoom = new CanvasJS.Chart("chartPieTypeRoom", {
			exportEnabled : true,
			animationEnabled : true,
			theme : "light2", // "light1", "dark1", "dark2"
			title : {
				text : "Tỉ lệ loại phòng đặt",
				fontFamily : 'Nunito',
				fontColor : '#4e73df'
			},
			/* subtitles: [{
				text: "Age Groups of Visitors"
			}], */
			data : [ {
				type : "pie",
				yValueFormatString : "#,##0\"%\"",
				indexLabel : "{label} - {y}",
				dataPoints : dps[0]
			} ]
		});

		var yValue;
		var label;

		<c:forEach items="${model.chartTypeRoomDataList}" var="dataPoints" varStatus="loop">
		<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		label = "${dataPoint.label}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			y : yValue,
		});
		</c:forEach>
		</c:forEach>

		chartPieTypeRoom.render();

		// Chart pie revenue quarter
		var dps = [ [] ];
		var chartPieQuarterRevenue = new CanvasJS.Chart(
				"chartPieQuarterRevenue", {
					exportEnabled : true,
					animationEnabled : true,
					theme : "light2", // "light1", "dark1", "dark2"
					title : {
						text : "Tỉ lệ doanh thu theo quý",
						fontFamily : 'Nunito',
						fontColor : '#4e73df'
					},
					data : [ {
						type : "pie",
						yValueFormatString : "#,##0\"%\"",
						indexLabel : "{label} - {y}",
						dataPoints : dps[0]
					} ]
				});

		var yValue;
		var label;

		<c:forEach items="${model.chartRevenueQuarterDataList}" var="dataPoints" varStatus="loop">
		<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		label = "${dataPoint.label}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			y : yValue,
		});
		</c:forEach>
		</c:forEach>

		chartPieQuarterRevenue.render();

	}
</script>
