<%@page import="com.hotel.util.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<header class="header-section">
        <div class="top-nav">
            <div class="container">
                <div class="row">
                    <div class="col-lg-7">
                        <ul class="tn-left">
                        	<li><a href="trang-chu" style="color: black;"><i class="fa fa-home"></i> Trang chủ</a></li>
                            <li><i class="fa fa-phone"></i> (84) 345 67890</li>
                            <li><i class="fa fa-envelope"></i> BinhMinhHolet@gmail.com</li>
                        </ul>
                    </div>
                    <div class="col-lg-5">
    <div class="tn-right">
        
        <security:authorize access = "isAnonymous()">
	        <div class="top-social">
	            <a href="#"><i class="fa fa-facebook"></i></a>
	            <a href="#"><i class="fa fa-twitter"></i></a>
	            <a href="#"><i class="fa fa-tripadvisor"></i></a>
	            <a href="#"><i class="fa fa-instagram"></i></a>
	        </div>
			<a href="/hotel/dang-nhap" class="bk-btn rounded" style="padding: 10px;">Đăng nhập</a>
        	<a href="/hotel/dang-ky" class="bk-btn rounded" style="padding: 10px;">Đăng ký</a>
		</security:authorize>
		
		<security:authorize access = "isAuthenticated()">
			<!-- Muốn sử dụng Security thì phải import vào taglib.jsp -->
			<!-- Nhớ import util.SecurityUtils -->
			<div class="bottom-social d-flex justify-content-end align-items-center" style="margin:10px">
				<p style="padding-right: 20px; margin:0 !important ; color:#333">Welcome <%=SecurityUtils.getPrincipal().getFullname() %></p>
				<a class="btn rounded" style="height: fit-content; width:70px;background:#dfa974" href="<c:url value = "/thoat"/>"><i class="fa-solid fa-right-from-bracket"></i></a>
			</div>
		</security:authorize>
        
    </div>
</div>

                </div>
            </div>
        </div>
        <div class="menu-item">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2">
                        <div class="logo">
                            <a href="./index.html">
                                <img src="img/logo.png" alt="">
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-10">
                        <div class="nav-menu">
                            <nav class="mainmenu">
                                <ul>
                                    <li><a href="/hotel/trang-chu">Trang chủ</a></li>
                                    <li><a href="/hotel/phong">Phòng</a></li>
                                    <security:authorize access="isAuthenticated()">
                                    	<li><a href="/hotel/lich-su">Lịch sử đặt phòng</a></li>
                                    </security:authorize>
                                    
                                        <!-- <ul class="dropdown">
                                            <li><a href="/hotel/chi-tiet">Chi tiết phòng</a></li>
                                            
                                        </ul> -->
                                    
                                </ul>
                            </nav>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>