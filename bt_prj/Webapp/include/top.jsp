<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>

//<a href="profile.jsp" class="dropdown-item has-icon loginclick">
/* 	$(document).ready(function() {
		$(".dropdown-item.has-icon.loginclick").click(function() {
		alert("접근권한이 없습니다. 로그인 해 주세요.");
		 $(location).attr('href',"register.jsp");
		});	
	});
	 */
//<a href="#" onClick="goLoginPage()">
function goLoginPage(){
	alert("접근권한이 없습니다. 로그인 해 주세요.");
	location.href="/login.jsp";
}
</script>

</head>
<body>
<!-- 상단검색창-->

<nav class="navbar navbar-expand-lg main-navbar">
<form class="form-inline mr-auto">
  <ul class="navbar-nav mr-3">
    <li><a href="#" data-toggle="sidebar" class="nav-link nav-link-lg"><i class="ion ion-navicon-round"></i></a></li>
    <li><a href="#" data-toggle="search" class="nav-link nav-link-lg d-sm-none"><i class="ion ion-search"></i></a></li>
  </ul>
  <div class="search-element">
    <input class="form-control" type="search" placeholder="Search" aria-label="Search">
    <button class="btn" type="submit"><i class="ion ion-search"></i></button>
  </div>
</form>
<ul class="navbar-nav navbar-right">
  <li class="dropdown dropdown-list-toggle"><a href="#" data-toggle="dropdown" class="nav-link notification-toggle nav-link-lg beep"><i class="ion ion-ios-bell-outline"></i></a>
    <div class="dropdown-menu dropdown-list dropdown-menu-right">
      <div class="dropdown-header">Notifications
        <div class="float-right">
          <a href="#">View All</a>
        </div>
      </div>
      <div class="dropdown-list-content">
        <a href="#" class="dropdown-item dropdown-item-unread">
          <img alt="image" src="/img/avatar/avatar-1.jpeg" class="rounded-circle dropdown-item-img">
          <div class="dropdown-item-desc">
            <b>Kusnaedi</b> has moved task <b>Fix bug header</b> to <b>Done</b>
            <div class="time">10 Hours Ago</div>
          </div>
        </a>
        <a href="#" class="dropdown-item dropdown-item-unread">
          <img alt="image" src="/img/avatar/avatar-2.jpeg" class="rounded-circle dropdown-item-img">
          <div class="dropdown-item-desc">
            <b>Ujang Maman</b> has moved task <b>Fix bug footer</b> to <b>Progress</b>
            <div class="time">12 Hours Ago</div>
          </div>
        </a>
        <a href="#" class="dropdown-item">
          <img alt="image" src="/img/avatar/avatar-3.jpeg" class="rounded-circle dropdown-item-img">
          <div class="dropdown-item-desc">
            <b>Agung Ardiansyah</b> has moved task <b>Fix bug sidebar</b> to <b>Done</b>
            <div class="time">12 Hours Ago</div>
          </div>
        </a>
        <a href="#" class="dropdown-item">
          <img alt="image" src="/img/avatar/avatar-4.jpeg" class="rounded-circle dropdown-item-img">
          <div class="dropdown-item-desc">
            <b>Ardian Rahardiansyah</b> has moved task <b>Fix bug navbar</b> to <b>Done</b>
            <div class="time">16 Hours Ago</div>
          </div>
        </a>
        <a href="#" class="dropdown-item">
          <img alt="image" src="/img/avatar/avatar-5.jpeg" class="rounded-circle dropdown-item-img">
          <div class="dropdown-item-desc">
            <b>Alfa Zulkarnain</b> has moved task <b>Add logo</b> to <b>Done</b>
            <div class="time">Yesterday</div>
          </div>
        </a>
      </div>
    </div>
  </li>
  <li class="dropdown"><a href="#" data-toggle="dropdown" class="nav-link dropdown-toggle nav-link-lg">
    <i class="ion ion-android-person d-lg-none"></i>
    <div class="d-sm-none d-lg-inline-block">
<!-- c:choose ={ }, c:when = if와 같음, outherwise = else와 같음 -->
    <c:choose>
    <c:when test="${sessionScope.SESS_GUBUN == 'a'}">
         ${sessionScope.SESS_NAME} 관리자 
    </c:when>
    <c:when test="${sessionScope.SESS_GUBUN == 'u'}">
         사용자 
    </c:when>
    <c:otherwise>
          게스트 
    </c:otherwise>
    </c:choose>   
          님 환영합니다.
    </div></a>
    
   <div class="dropdown-menu dropdown-menu-right">
            <c:choose>
               <c:when test="${sessionScope.SESS_NAME != null}">
                  <a href="/pwcheck.jsp" class="dropdown-item has-icon">
                  <i class="ion ion-android-person"></i> Profile
                  </a>
                  <a href="/login" class="dropdown-item has-icon">
                  <i class="ion ion-log-out"></i> Logout
                  </a>
               </c:when>
               <c:otherwise>
                  <a onclick="goLoginPage()" class="dropdown-item has-icon">
                  <i class="ion ion-android-person"></i> Profile
                  </a>
                  <a onclick="goLoginPage()" class="dropdown-item has-icon">
                  <i class="ion ion-log-out"></i> Login
                  </a>
               </c:otherwise>
            </c:choose>
         </div>
    
  
  </li>
</ul>
</nav>


</body>
</html>