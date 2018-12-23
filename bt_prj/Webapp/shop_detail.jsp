<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 <%@ include file="/include/header.jsp" %>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
     //$("#btn").click(function(){   
    //});
});    
</script>
</head>

<body>
  <div id="app">
    <div class="main-wrapper">
      <div class="navbar-bg"></div>
      <%@ include file="/include/top.jsp" %>
      <%@ include file="/include/left.jsp"%>
      <div class="main-content">
        <section class="section">
          <h1 class="section-header">
            <div>이게 무슨 맛집이야 어?!</div>
          </h1>

          <div class="section-body">
          
            <div class="jumbotron">
              <h1 class="display-5">${SVO.sname}</h1>
              <p class="lead">${SVO.sinfo}</p>
              <hr class="my-4">
              <p>1.7km</p>
              <p class="lead">
            </p>
            </div>
            <h2 class="section-title">${SVO.sinfo}</h2>
            
            <div class="row">
              
              <div class="col-12 col-md-6 col-lg-6">
                <div class="card">
                  <div class="card-header">
                    <div class="float-right">
  
                    </div>
                    <h4>Shop Picture</h4>
                  </div>
                  <div class="card-body">
                    <div class="active" data-tab-group="carousel" id="carousel-simple">
                      <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
 						
 						
 						
                        <div class="carousel-inner">
                          <!-- 이미지 루프 -->
                          <c:forEach var = "pp" items = "${LIST_PIC}" varStatus="cc">
                          <div class="carousel-item <c:if test="${cc.index==0}">active</c:if>">
                            <img class="d-block w-100" src='./cdir/${pp.pname}' alt="First slide">
                          </div>
                          </c:forEach>
                          
                        <!--   <div class="carousel-item">
                            <img class="d-block w-100" src="/img/news/img07.jpg" alt="Second slide">
                          </div>
                          <div class="carousel-item">
                            <img class="d-block w-100" src="/img/news/img08.jpg" alt="Third slide">
                          </div> -->
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                          <span class="sr-only">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                          <span class="carousel-control-next-icon" aria-hidden="true"></span>
                          <span class="sr-only">Next</span>
                        </a>
                      </div>                              
                    </div>
                  </div>
                </div>
                </div>
                
                <div class="col-12 col-md-6 col-lg-6">
                <div class="card">
                  <div class="card-header">
                    <h4>Shop location</h4>
                  </div>
                  <div class="card-body">
                    <div class="alert alert-light">
                      Here is a simple example using the map, we use the plugin <code>gmaps.js</code> made by <a href="https://github.com/hpneo" target="_blank">@hpneo</a>. You can learn more about this plugin <a href="https://github.com/hpneo/gmaps" target="_blank">here</a>.
                    </div>
                    <div id="simple-map" style="height:450px;"></div>
                  </div>
                </div>
              </div>
              </div>
            </div>
        </section>
          </div>
      </div>
      
      <div>
      <%@ include file="/include/footer.jsp"%>
    </div>


   <%@ include file="/include/script.jsp"%>
   <script src="http://maps.google.com/maps/api/js?key=AIzaSyBwuxZTF5uBsmC-N2OjG31i2UBg1sgjCn0&amp;sencer=true"></script>
     
     <!-- &callback = initMap 의 형태로 사용할 수 있는 코드 ↓↓ -->
     <script src="/modules/gmaps.js"></script>
     <script>
       // init map
       /* var simple_map = new GMaps({
            div: '#simple-map',
            lat: ${SVO.lat},
            lng: ${SVO.lng}
       });
       
       simple_map.addMarker({
           lat: ${SVO.lat},
           lng: ${SVO.lng},
           title: 'Lima',
           details: {
             database_id: 42,
             author: 'HPNeo'
           },
           click: function(e){
             if(console.log)
               console.log(e);
             alert('You clicked in this marker');
           },
           mouseover: function(e){
             if(console.log)
               console.log(e);
           }
         }); */
         
     
        // 지도 + 마커
        
        // Initialize and add the map
       /*  function initMap() { */
        // The location of Uluru
        var locate = {lat: ${SVO.lat}, lng: ${SVO.lng}};
        // The map, centered at Uluru
        var map = new google.maps.Map(
            document.getElementById('simple-map'), {zoom: 13, center: locate});
        // The marker, positioned at Uluru
        var marker = new google.maps.Marker({
        		 position: locate, 
        		 map: map, 
        		 title:'${SVO.sname}' 
        		});
   /*    } */
     
     </script>
</body>
</html>