<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div
	class="card-header d-flex justify-content-between align-items-center ">
	<div>
		Thông tin phòng -
		<c:if test="${roomModel.statusname eq 'Trống'}">
			<div class="badge badge-success">${roomModel.statusname}</div>
		</c:if>
		<c:if test="${roomModel.statusname eq 'Bận'}">
			<div class="badge badge-danger">${roomModel.statusname}</div>
		</c:if>
		<c:if test="${roomModel.statusname eq 'Bảo trì'}">
			<div class="badge badge-warning">${roomModel.statusname}</div>
		</c:if>
	</div>
	<div class="card-link d-flex">
		<a id="collapseBtn" onclick="showCollapse();" class="collapsed mr-2"
			href="#" data-toggle="collapse" data-target="#collapseRoom"
			aria-expanded="true" aria-controls="collapseTwo"
			style="color: #858796;"> <i class="fa-solid fa-rotate mr-2"></i>
		</a> <a
			href="<c:url value ='/quan-tri/phong/chinh-sua?id=${roomModel.getId() }'/>"
			style="color: #4e73df;"><i class="fa fa-edit"></i></a>

	</div>
</div>
	<div class="card-body p-3 pb-0">
		<input type="hidden" id="newPriceHidden" value="${orderModel.getNewPrice() }"/>
		<input type="hidden" id="oldPriceHidden" value="${orderModel.getOldPrice()}"/>
		<input type="hidden" id="totalPriceHidden" value="${orderModel.getTotalPrice()}"/>
		<div class="col-md-12">
			<img width="350px"
				class=" d-flex col-md-12 justify-content-center mb-3profile-user-img img-responsive img-bordered lazy-load  shadow"
				src="<c:url value="${roomModel.image }"/>" />
		</div>
		<hr class="sidebar-divider">
		<div class="ml-3">
			<div class="card-title d-flex col-md-12">
				<div class="font-weight-bold mr-1">Tên phòng:</div>
				${roomModel.name }
			</div>
			<div class="card-title d-flex col-md-12">
				<div class="font-weight-bold mr-1">Loại phòng:</div>
				${roomModel.getTypename()}
			</div>
			<div class="card-title d-flex col-md-12">
				<div class="font-weight-bold mr-1">Số lượng:</div>
				${roomModel.getQuantity() }
			</div>
			<div class="card-title d-flex col-md-12">
				<div class="font-weight-bold mr-1">Giá:</div>
				<div id="roomPrice" >${roomModel.getPrice() }</div>
			</div>
			<div class="card-title col-md-12 " >
				<div class="font-weight-bold mr-1">Mô tả:</div>
				<p style="height:340px; overflow-y: auto; text-overflow: ellipsis;margin: 0;scrollbar-color: #4e73df #fff;">${roomModel.getDescription() }</p>
			</div>
		</div>

	</div>
