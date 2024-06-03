<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Sona Template">
    <meta name="keywords" content="Sona, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Khách Sạn Bình Minh</title>

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
	

    <!-- Css Styles -->
    <link rel="stylesheet" href="<c:url  value = '/template/web/css/font-awesome.min.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url  value = '/template/web/css/bootstrap.min.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url  value = '/template/web/css/elegant-icons.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url  value = '/template/web/css/flaticon.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url  value = '/template/web/css/owl.carousel.min.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url  value = '/template/web/css/nice-select.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url  value = '/template/web/css/jquery-ui.min.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url  value = '/template/web/css/magnific-popup.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url  value = '/template/web/css/slicknav.min.css'/>" type="text/css">
    <link rel="stylesheet" href="<c:url  value = '/template/web/css/style.css'/>" type="text/css">
	
	
	
</head>

<body>
    <!-- Page Preloder -->
   

    <!-- Offcanvas Menu Section Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="canvas-open">
        <i class="icon_menu"></i>
    </div>
    <div class="offcanvas-menu-wrapper">
        <div class="canvas-close">
            <i class="icon_close"></i>
        </div>
        <div class="search-icon  search-switch">
            <i class="icon_search"></i>
        </div>
        <div class="header-configure-area">
            <a href="/hotel/trang-chu" class="bk-btn">Booking Now</a>
        </div>
        <nav class="mainmenu mobile-menu">
            <ul>
                <li class="active"><a href="/hotel/trang-chu">Trang chủ</a></li>
                <li><a href="/hotel/phong">Phòng</a></li>
            </ul>
        </nav>
        <div id="mobile-menu-wrap"></div>
        <div class="top-social">
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-tripadvisor"></i></a>
            <a href="#"><i class="fa fa-instagram"></i></a>
        </div>
        <ul class="top-widget">
            <li><i class="fa fa-phone"></i> (12) 345 67890</li>
            <li><i class="fa fa-envelope"></i> BinhMinhHolet@gmail.com</li>
        </ul>
    </div>
    <!-- Offcanvas Menu Section End -->

    <!-- Header Section Begin -->
   <%@ include file="/common/web/header.jsp"%>
    <!-- Header End -->

    <!-- Content -->
  	<decorator:body/>
    <!-- Content -->

    <!-- Footer Section Begin -->
   	<%@include file="/common/web/footer.jsp" %>
    <!-- Footer Section End -->

    <!-- Search model Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch"><i class="icon_close"></i></div>
            <form class="search-model-form">
                <input type="text" id="search-input" placeholder="Search here.....">
            </form>
        </div>
    </div>
    <!-- Scroll to Top Button-->
	<!-- <a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a> -->
   
    
	
	<!-- Bootstrap core JavaScript-->
	<script
		src="<c:url value='/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

    <!-- Js Plugins -->
    <script src="<c:url value = '/template/web/js/jquery-3.3.1.min.js'/>"></script>

    <script src="<c:url value = '/template/web/js/jquery.magnific-popup.min.js'/>"></script>
    <script src="<c:url value = '/template/web/js/jquery.nice-select.min.js'/>"></script>
    <script src="<c:url value = '/template/web/js/jquery-ui.min.js'/>"></script>
    <script src="<c:url value = '/template/web/js/jquery.slicknav.js'/>"></script>
    <script src="<c:url value = '/template/web/js/owl.carousel.min.js'/>"></script>
    <script src="<c:url value = '/template/web/js/main.js'/>"></script>
	
	<!-- date-range -->
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
		
	<link rel="stylesheet" type="text/css"
		href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
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
	<!-- Phân trang -->
	<script src="<c:url value='/template/admin/paging/jquery.twbsPagination.js' />"></script>
	<script src="<c:url value='/template/admin/js/bootstrap.min.js' />"></script>
	
	
</body>

</html>