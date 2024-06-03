<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<div class="card shadow col-md-12 mb-3 p-0 " style="">
				<div
					class="card-header d-flex justify-content-between align-items-center ">
					<div>
						Thông tin khách hàng -
						<c:if test="${customerModel.status eq 1 }">
							<div class="badge badge-success">Đang hoạt động</div>
						</c:if>
						<c:if test="${customerModel.status eq 0 }">
							<div class="badge badge-secondary">Không hoạt động</div>
						</c:if>
					</div>
					<div class="card-link ">
						<a
							href="<c:url value ='/quan-tri/khach-hang/chinh-sua?id=${customerModel.getId() }'/>"
							style="color: #4e73df;"><i class="fa fa-edit"
							style="color: #4e73df;"></i></a>

					</div>
				</div>
				<div class="row p-3 card-body">
					<img width="250px"
						class="col-md-5 profile-user-img img-responsive img-bordered lazy-load  shadow"
						src="<c:url value="${customerModel.getImage() }"/>" />
					<div class="col-md-7">
						<div class="col-md-12 card-title">
							<div class="font-weight-bold">Tên khách hàng:</div>
							${customerModel.getFullName() }
						</div>
						<hr class="sidebar-divider">
						<div class="col-md-12  card-title">
							<div class="font-weight-bold">Số điện thoại:</div>
							${customerModel.getPhone() }
						</div>
						<div class="col-md-12  card-title">
							<div class="font-weight-bold">Địa chỉ:</div>
							${customerModel.getAddress() }
						</div>
					</div>
				</div>

			</div>