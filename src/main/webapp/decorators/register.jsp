<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>    
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="icon" href="<c:url value='/template/logo.png'/>" type="image/png">
	<title><decorator:title default="Đăng ký" /></title>

    <!-- Custom fonts for this template-->
    <link href="<c:url value='/template/admin/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url value='/template/admin/css/sb-admin-2.min.css'/>" rel="stylesheet">
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

<body style="background:#dfa974">
	<%@include file="/common/header_login.jsp" %>
    <decorator:body/>
	<%@include file="/common/footer_login.jsp" %>
    <!-- Bootstrap core JavaScript-->
    <script src="<c:url value = '/template/admin/vendor/jquery/jquery.min.js'/>"></script>
    <script src="<c:url value = '/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js'/>" ></script>

    <!-- Core plugin JavaScript-->
    <script src="<c:url value = '/template/admin/vendor/jquery-easing/jquery.easing.min.js'/>"></script>

    <!-- Custom scripts for all pages-->
    <script src="<c:url value = '/tempalte/admin/js/sb-admin-2.min.js'/>"></script>

</body>

</html>