<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <!-- 헤더 css / jquery cdn -->
      <%@ include file="/include/header.jsp" %>
</head>

<body>
  <div id="app">
    <div class="main-wrapper">
      <div class="navbar-bg"></div>
     
      <!-- 상단 -->
      <%@ include file="/include/top.jsp" %>
     
      <!-- 래프트 영역 -->
      <%@ include file="/include/left.jsp" %>

      
      <div class="main-content">
        <section class="section">
          <h1 class="section-header">
            <div>Simple Maps</div>
          </h1>

          <div class="section-body">
            <div class="row">
              <div class="col-12">
                <div class="card">
                  <div class="card-header">
                    <h4>Simple Maps</h4>
                  </div>
                  <div class="card-body">
                    <div class="alert alert-light">
                      Here is a simple example using the map, we use the plugin <code>gmaps.js</code> made by <a href="https://github.com/hpneo" target="_blank">@hpneo</a>. You can learn more about this plugin <a href="https://github.com/hpneo/gmaps" target="_blank">here</a>.
                    </div>
                    <div id="simple-map" style="height:400px;"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
      
      <!-- 푸터영역 -->
      <%@ include file="/include/footer.jsp" %>

	  <!-- 스크립트 -->
  	  <%@ include file="/include/script.jsp" %>
  
  <!-- 지도관련 스크립트 -->
<script src="http://maps.google.com/maps/api/js?key=AIzaSyDWnF3ONfPsUtJqoV-RZGwNm_abXeRkcQk&amp;sensor=true"></script>
     <script src="/modules/gmaps.js"></script>
     <script>
       // init map
       var simple_map = new GMaps({
            div: '#simple-map',
            lat: 37.473083599999995,
            lng: 126.8788276
       });
     </script>

</body>
</html>