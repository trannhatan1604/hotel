<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>

<title><decorator:title default="HOTEL ADMIN" /></title>
<link rel="icon" href="<c:url value='/template/logo.png'/>" type="image/png">
<!-- Custom fonts for this template-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
	integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<link
	href="<c:url  value = '/template/admin/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
	<script
		src="<c:url value='/template/admin/vendor/jquery/jquery.min.js'/>"></script>
		
<!-- Custom styles for this template-->
<link href="<c:url value='/template/admin/css/sb-admin-2.min.css'/>"
	rel="stylesheet">
	
<link rel="stylesheet" type="text/css"
		href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />	
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- SideBar -->
		<%@ include file="/common/admin/menu.jsp"%>
		<!-- SideBar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Header -->
				<%@ include file="/common/admin/header.jsp"%>
				<!-- Header -->

				<!-- Body -->
				<decorator:body />
				<!-- Body -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<%@ include file="/common/admin/footer.jsp"%>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	

	
	<!-- Bootstrap core JavaScript-->
	<script
		src="<c:url value='/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<c:url value='/template/admin/vendor/jquery-easing/jquery.easing.min.js'/>"></script>

	<!-- Custom scripts for all pages-->
	<script src="<c:url value='/template/admin/js/sb-admin-2.min.js'/>"></script>

	<!-- Page level plugins -->
	<script
		src="<c:url value='/template/admin/vendor/chart.js/Chart.min.js'/>"></script>
	<!-- Page level custom scripts -->
	<script
		src="<c:url value='/template/admin/js/demo/chart-area-demo.js'/>"></script>
	<script
		src="<c:url value='/template/admin/js/demo/chart-pie-demo.js'/>"></script>
		
	<!-- date-range -->
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
		
	
	<script>
		$(function() {
			$('input[name="dateRange"]').daterangepicker(
					{
						opens : 'left',
						locale:{
							format: 'YYYY/MM/DD'
						}
					},
					function(start, end, label) {
						console.log("A new date selection was made: "
								+ start.format('YYYY-MM-DD') + ' to '
								+ end.format('YYYY-MM-DD'));
					});
		});
	</script>
<script
		src="<c:url value='/template/admin/paging/jquery.twbsPagination.js' />"></script>
		
	<script src="<c:url value='/template/admin/js/bootstrap.min.js' />"></script>
	<script src="https://cdn.canvasjs.com/canvasjs.min.js"></script>
</body>

</html>