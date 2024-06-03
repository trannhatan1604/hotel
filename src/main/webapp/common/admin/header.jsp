<%@page import="com.hotel.util.FeedbackUtils"%>
<%@page import="com.hotel.dto.FeedBackDTO"%>
<%@page import="com.hotel.util.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<nav class="navbar navbar-expand navbar-light bg-white topbar static-top shadow mb-4">



                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <%if(SecurityUtils.getPrincipal().getImg().equals("")||SecurityUtils.getPrincipal().getImg().isEmpty())
                    {
                    	SecurityUtils.getPrincipal().setImg("/template/admin/img/account/notphoto.png");
                    }
                    %>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            
                                <a class="text-center small text-gray-600 nav-link" href="<c:url value='/quan-tri/phan-hoi'/>"><i class="fas fa-envelope fa-fw mr-1"></i>Phản hồi</a>
                                <!-- Counter - Messages -->
                          		<%-- <span class="badge badge-danger badge-counter">${requestScope.countFeedBack}</span> --%>
                            
                            <!-- Dropdown - Messages -->
                            <%-- <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Phản hồi
                                </h6>
                                 
                                <!-- requestScope là gọi biến để dùng toàn bộ chương trình vì có tính @Scope -->
                               <c:forEach items="${feedbackUtils.listFeedBack}" var="item"> 
								    <a class="dropdown-item d-flex align-items-center" href="#">
								        <div class="dropdown-list-image mr-3">
								            <img class="rounded-circle" src="img/undraw_profile_1.svg" alt="...">
								            <div class="status-indicator bg-success"></div>
								        </div>
								        <div class="font-weight-bold">
								            <!-- Hiển thị thông tin của từng phản hồi -->
								            <div class="text-truncate">${item.description}</div>
								            <div class="small text-gray-500">${item.accountName}</div>
								        </div>
								    </a>
								</c:forEach>
								 
                            </div> --%>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><%=SecurityUtils.getPrincipal().getFullname() %></span>
                                <img class="img-profile rounded-circle" src="<c:url value='<%=SecurityUtils.getPrincipal().getImg() %>'/>">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="<c:url value='/quan-tri/thong-tin?id=${SecurityUtils.getPrincipal().getId()}'/>">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Thông tin cá nhân
                                </a>
                                
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="<c:url value='/thoat'/>" >
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Đăng xuất
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>