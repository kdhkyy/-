<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
     <!-- 헤더 영역 css/jquery cdn  -->
   <%@ include file="/include/header.jsp" %>

<script>
/* reference web :  */
$(document).ready(function(){
   if (navigator.geolocation) {
         navigator.geolocation.getCurrentPosition(function(position) {
            var mylat = position.coords.latitude;
            var mylng = position.coords.longitude;
                  var res = {"lat":mylat ,"lng":mylng, "currentPage":1};
                  $.ajax({
                        url:"/shop",
                        type:"post",
                        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                        data: "KEY="+JSON.stringify(res),
                        
                        
                        success:function(resObject){
                        console.log("123415");
                           var res = "";
                    
                          $.each(resObject, function(idx, vv){
                             res += "<li class=\"media\">";
                             res += "<img class=\"mr-3 rounded-circle\" width=\"50\" src=\"/cdir/"+vv.pname+"\" alt=\"avatar\">";
                             res += "<div class=\"media-body\">";
                             res += "<div class=\"float-right\"><small>"+vv.distance+"km"+"</small></div>";
                             res += "<div class=\"media-title\"><a href='/shop_detail?sseq="+vv.sseq+"'>"+vv.sname+"</a></div>";
                             res += "<small>"+vv.sinfo+"</small>";
                             res += "</div></li>";
                          });
                          $(".list-unstyled.list-unstyled-border").html(res);
                          console.log("123415");
                      }
                  })
            }, function(error) {
               console.error(error);
            }, {
               enableHighAccuracy : false,
               maximumAge : 0,
               timeout : Infinity
            });
    } else {
         alert('GPS를 지원하지 않습니다');
    }
   
   //-------------------------------------크롤링시작-----------------------------------------------------
   
     
   $.ajax({
                        url:"/mango",
                        type:"post",
                        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                        data: "top=4",
                        resultType="json",
                        
                        success:function(resObject){
                        console.log("123415");
                           
                        var res = "";
                              
                          $.map(resObject, function(vv, idx){
                        	  res += "<tr>";
                        	  res += "<td>";
                        	  res += "상호명";
                        	  res += "</td>";
                        	  res += "<td>";
                        	  res += "<a href="#"><img src="/img/avatar/avatar-1.jpeg" alt="avatar" width="30" class="rounded-circle mr-1">주소</a>";
                        	  res += "</td>";
                        	  res += "<td>";
                        	  res += "<a class="btn btn-primary btn-action mr-1" data-toggle="tooltip" title="Edit">""<i class="ion ion-edit"></i></a>";
                        	  res += "<a class="btn btn-danger btn-action" data-toggle="tooltip" title="Delete">""<i class="ion ion-trash-b"></i></a>";
                        	  res += "</td>";
                        	  res += "</tr>";  
                          });
                          $(".mango").html(res);
                          console.log("123415");
                      }
                  })  
});
   </script>
</head>

<body>
  <div id="app">
    <div class="main-wrapper">
      <div class="navbar-bg"></div>
      
      <!-- 상단 검색창 -->
      <%@ include file="/include/top.jsp" %>
      
      
      <!-- 레프트 영역 -->
      <%@ include file="/include/left.jsp" %>
      
      
      <!-- 컨텐츠 영역 -->
      <div class="main-content">
        <section class="section">
          <h1 class="section-header">
            <div>
            Dashboard
                        이름 :${sessionScope.SESS_NAME} <br>
          
            <% 
            Cookie[] carr = request.getCookies();
            if(carr != null){
            for(int i=0; i<carr.length; i++){
            	Cookie c = carr[i];
            	out.print(c.getName() + "," + c.getValue() + "<br>");
            	}
            }
            %>
            
            
            
            </div>
          </h1>
          <div class="row">
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-primary">
                  <i class="ion ion-person"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>Total Admin</h4>
                  </div>
                  <div class="card-body">
                    10
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-danger">
                  <i class="ion ion-ios-paper-outline"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>News</h4>
                  </div>
                  <div class="card-body">
                    42
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-warning">
                  <i class="ion ion-paper-airplane"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>Reports</h4>
                  </div>
                  <div class="card-body">
                    1,201
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-12">
              <div class="card card-sm-3">
                <div class="card-icon bg-success">
                  <i class="ion ion-record"></i>
                </div>
                <div class="card-wrap">
                  <div class="card-header">
                    <h4>Online Users</h4>
                  </div>
                  <div class="card-body">
                    47
                  </div>
                </div>
              </div>
            </div>                  
          </div>
          <div class="row">
            <div class="col-lg-8 col-md-12 col-12 col-sm-12">
              <div class="card">
                <div class="card-header">
                  <div class="float-right">
                    <div class="btn-group">
                      <a href="#" class="btn active">Week</a>
                      <a href="#" class="btn">Month</a>
                      <a href="#" class="btn">Year</a>
                    </div>
                  </div>
                  <h4>Statistics</h4>
                </div>
                <div class="card-body">
                  <canvas id="myChart" height="158"></canvas>
                  <div class="statistic-details mt-sm-4">
                    <div class="statistic-details-item">
                      <small class="text-muted"><span class="text-primary"><i class="ion-arrow-up-b"></i></span> 7%</small>
                      <div class="detail-value">$243</div>
                      <div class="detail-name">Today's Sales</div>
                    </div>
                    <div class="statistic-details-item">
                      <small class="text-muted"><span class="text-danger"><i class="ion-arrow-down-b"></i></span> 23%</small>
                      <div class="detail-value">$2,902</div>
                      <div class="detail-name">This Week's Sales</div>
                    </div>
                    <div class="statistic-details-item">
                      <small class="text-muted"><span class="text-primary"><i class="ion-arrow-up-b"></i></span>9%</small>
                      <div class="detail-value">$12,821</div>
                      <div class="detail-name">This Month's Sales</div>
                    </div>
                    <div class="statistic-details-item">
                      <small class="text-muted"><span class="text-primary"><i class="ion-arrow-up-b"></i></span> 19%</small>
                      <div class="detail-value">$92,142</div>
                      <div class="detail-name">This Year's Sales</div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 col-md-12 col-12 col-sm-12">
              <div class="card">
                <div class="card-header">
                  <h4>Recent Activities</h4>
                </div>
                <div class="card-body">
                  
                  
                  <ul class="list-unstyled list-unstyled-border">
                  
<!-- 반복문 -->
                   <%--  <c:forEach var="vo" items="${SHOP_LIST}">
                    <li class="media">
                      <img class="mr-3 rounded-circle" width="50" src="/cdir/${vo.pname}" alt="avatar">
                      <div class="media-body">
                        <div class="float-right"><small>${vo.distance}</small></div>
                        <div class="media-title">${vo.sname}</div>
                        <small>${vo.sinfo}</small>
                      </div>
                    </li>
                    </c:forEach> --%>
<!-- 반복문 끝  -->
                    
                  </ul>
                    
                  <div class="text-center">
                    <a href="#" class="btn btn-primary btn-round">
                      View All
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-5 col-md-12 col-12 col-sm-12">
              <form method="post" class="needs-validation" novalidate="">
                <div class="card">
                  <div class="card-header">
                    <h4>Quick Draft</h4>
                  </div>
                  <div class="card-body">
                    <div class="form-group">
                      <label>Title</label>
                      <input type="text" name="title" class="form-control" required>
                      <div class="invalid-feedback">
                        Please fill in the title
                      </div>
                    </div>
                    <div class="form-group">
                      <label>Content</label>
                      <textarea class="summernote-simple"></textarea>
                    </div>
                  </div>
                  <div class="card-footer">
                    <button class="btn btn-primary">Save Draft</button>
                  </div>
                </div>
              </form>
            </div>
            <div class="col-lg-7 col-md-12 col-12 col-sm-12">
              <div class="card">
                <div class="card-header">
                  <div class="float-right">
                    <a href="#" class="btn btn-primary">View All</a>
                  </div>
                  <h4>Latest Posts</h4>
                </div>
                
                
<!-- 맛집 크롤링 결과----------------------------------------------------- -->
                <div class="card-body">
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>상호명</th>
                          <th>주소</th>
                          <th>수정/삭제</th>
                        </tr>
                      </thead>
                      <tbody class="mango">
                      
                       <!-- AJAX 통신결과 뿌려질 영역 -->                       
                        
                                
                      </tbody>
                    </table>
                  </div>
                </div>
                
                <!-- 맛집 크롤링  -->
              </div>
            </div>
          </div>
        </section>
      </div>
      
      
      
      <!-- 풋터영역  -->
      <%@ include file="/include/footer.jsp" %>
     

     
    </div>
  </div>


     <!-- 스크립트 -->
     <%@ include file="/include/script.jsp" %>
     
<script src="/modules/chart.min.js"></script>
  <script src="/modules/summernote/summernote-lite.js"></script>

  <script>
  var ctx = document.getElementById("myChart").getContext('2d');
  var myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
      datasets: [{
        label: 'Statistics',
        data: [460, 458, 330, 502, 430, 610, 488],
        borderWidth: 2,
        backgroundColor: 'rgb(87,75,144)',
        borderColor: 'rgb(87,75,144)',
        borderWidth: 2.5,
        pointBackgroundColor: '#ffffff',
        pointRadius: 4
      }]
    },
    options: {
      legend: {
        display: false
      },
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: true,
            stepSize: 150
          }
        }],
        xAxes: [{
          gridLines: {
            display: false
          }
        }]
      },
    }
  });
  </script>
  <script src="/js/scripts.js"></script>
  <script src="/js/custom.js"></script>
  <script src="/js/demo.js"></script>
</body>
</html>