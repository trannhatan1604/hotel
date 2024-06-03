<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="<c:url value="/quan-tri/trang-chu"/>">
		<div class="sidebar-brand-icon rotate-n-10">
			<i class="fa-solid fa-hotel"></i>
		</div>
		<div class="sidebar-brand-text mx-3">
			Hotel Admin
		</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider my-0">

	<!-- Nav Item - Dashboard -->

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
		aria-controls="collapseTwo"> <i class="fas fa-fw fa-folder"></i> <span>Quản
				lý dữ liệu</span>
	</a>
		<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item" href="<c:url value = '/quan-tri/khach-hang'/>">Quản lý
					khách hàng</a> <a class="collapse-item" href="<c:url value = '/quan-tri/nhan-vien'/>">Quản
					lý nhân viên</a> <a class="collapse-item"
					href="<c:url value = '/quan-tri/loai-phong'/>">Quản lý loại phòng</a>
					<a class="collapse-item"
					href="<c:url value = '/quan-tri/phong'/>">Quản lý phòng</a>
			</div>
		</div></li>
		
	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseThree"
		aria-expanded="true" aria-controls="collapseThree"> <i class="fas fa-fw fa-solid fa-percent" style="color: #5696c8;"></i><span>Thống kê và khuyến mãi
		</span>
	</a>
		<div id="collapseThree" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item" href="<c:url value = '/quan-tri/khuyen-mai'/>">Quản lý
					khuyến mãi</a>
			</div>
		</div></li>
		
	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Heading -->

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseFour"
		aria-expanded="true" aria-controls="collapseFour"> <i
			class="fas fa-fw fa-folder"></i> <span>Quản lý đặt phòng</span>
	</a>
		<div id="collapseFour" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item" href="<c:url value = '/quan-tri/dat-phong'/>">Đặt phòng</a>
					<a class="collapse-item" href="<c:url value = '/quan-tri/phieu-dat'/>">Quản
					lý phiếu đặt</a>
			</div>
		</div></li>
	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>


</ul>