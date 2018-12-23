<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <!-- 헤더 css / jquery cdn -->
  <%@ include file="/include/header.jsp" %>
  
<script>
$(document).ready(function(){
    $("#files").change(function(){
    	var plistStr = "";
    	var input = document.getElementById("files");
    	for (var i = 0; i < input.files.length; i++) {
    		plistStr = input.files[i].name + "<br>";
    	}
    	$("#fileListDiv").html("");
    	$("#fileListDiv").html(plistStr);
    }); 
	
	$("#saveButton").click(function(){
        console.log($("#sinfo").val());
        $("#sform").submit();     
       		 
    });
});    
</script>

<style>
.pac-container{
  z-index: 1500 !important;
}
</style>
</head>

<body>
  <div id="app">
    <div class="main-wrapper">
      <div class="navbar-bg"></div>
      
      <!-- 상단 검색창 -->
      <%@ include file="/include/top.jsp" %>
      
      <!-- 레프트 메뉴 영역 -->
      <%@ include file="/include/left.jsp" %>
      
      <!-- 컨텐츠 영영 -->
      <div class="main-content">
        <section class="section">
          <h1 class="section-header">
            <div>맛집 등록하기 --- <a href="/shop_form.jsp">[글쓰기]</a></div>
          </h1>

          <div class="section-body">
            
             <div class="row">
             
              <div class="col-12 col-md-6 col-ld-6">
              
              
              <!--  ------------------------------------------------- -->
              <!--  ------------------등록폼--------------- -->
              <!--  ------------------------------------------------- -->
              <form method="post" class="needs-validation" novalidate="" id="sform" action="/Shop_Insert" enctype="multipart/form-data">
              
                <div class="card">
                  <div class="card-header">
                    <h4>Shop Register Form</h4>
                  </div>
                  <div class="card-body">
                     <div class="alert alert-primary">
                         For maps, we use <a href="http://maps.google.com/" target="_blank">Google Map</a> created.
                     </div>
                  
                    <div class="form-group">
                      <label>맛집 상호명</label>
                      <input type="text" name="sname" class="form-control" required>
                      <div class="invalid-feedback">
                           		상호명을 입력하세요.
                      </div>
                    </div>
                    <div class="form-group">
                      <label>사진업로드</label>
                      <input type="file" name="files[]" id="files" multiple class="form-control" required>
                      <div class="invalid-feedback">
                          		 대표 사진 한장을 등록해주세요.
                      </div>
                    </div>
                    
                    <div id="fileListDiv">
                    <!-- 첨부파일 목록 보여줄 곳 -->
                    </div>
                    
                    <div class="form-group">
                      <label>※선택 : 맛집 지도위치 </label>
                      <a href="http://maps.google.com/" target="_blank">Google Map</a>
                      
<!-- javascript Map API -->
<!-- Places API -->
<!-- Google Maps API -->                      
<div id="locationField">
  <input tpye=text name="placename" id="placename" placeholder="검색할 상호명을 입력하세요">
</div>
<input tpye=text class="field" name="lat" id="lat" />
<input tpye=text class="field" name="lng" id="lng" />


                    </div>
                    <div class="form-group">
                      <label>맛집 소개글</label><br>
                      <textarea id="sinfo" name="sinfo" class="summernote-simple"></textarea>
                    </div>
                  </div>
                  <div class="card-footer">
                    <button id="saveButton" class="btn btn-primary">Save Shop Info</button>
                  </div>
                </div>
              </form>
           
                     <!--  ------------------------------------------------- -->
                     <!--  ------------------등록폼--------------- -->
                     <!--  ------------------------------------------------- -->
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
              
            </div>   <!-- end of rows -->
             
             
              
            
          </div>      <!-- end of section-body -->
          
        </section>
      </div>            <!-- end of main-content -->
      
       <!-- 푸터 영역 -->
      <%@ include file="/include/footer.jsp" %>
      
    </div>
  </div>

  <!-- 스크립트 영역 -->
  <%@ include file="/include/script.jsp" %>
  
  <script src="/modules/summernote/summernote-lite.js"></script>
  <script src="http://maps.google.com/maps/api/js?key=AIzaSyBwuxZTF5uBsmC-N2OjG31i2UBg1sgjCn0&libraries=places&callback=initAutocomplete" async defer></script>
  <script src="/modules/gmaps.js"></script>
 
 
  <script>
  //https://fatc.club/2017/06/05/1949
  //https://developers.google.com/maps/documentation/javascript/examples/geocoding-simple?hl=ko
   //     
  var placeSearch;
  var placename = document.getElementById('placename');
  var autocomplateResult;
  
  function initAutocomplete() {
    // Create the autocomplete object, restricting the search to geographical
    // location types.
    // type = address:주소, establishment:상호, geocode:위경도
    autocomplateResult = 
       new google.maps.places.Autocomplete(placename, {types:['establishment']});
       //setupClickListener('changetype-establishment', ['establishment']);
         
    // When the user selects an address from the dropdown, populate the address
    // fields in the form.
    autocomplateResult.addListener('place_changed', function(){
    	
       // Get the place details from the autocomplete object.
       var choicePlace = autocomplateResult.getPlace();
       var latVal = choicePlace.geometry.location.lat();
       var lngVal = choicePlace.geometry.location.lng();
       
       //------------입력폼에 위도/경도 값 세팅
      document.getElementById("lat").value=latVal;
      document.getElementById("lng").value=lngVal;
      //$("#lat").val(latVal);
    
      //------------우측 영역에 선택장소 지도 그리기------------------------
      
  		var locate = {lat:latVal, lng:lngVal};
 	 // The map, centered at Uluru
  		var resultMap = new google.maps.Map(
      		document.getElementById('simple-map'), {zoom: 13, center: locate});
 	 
  	// The marker, positioned at Uluru
 		var marker = new google.maps.Marker({
  			position: locate, 
  		 	map: resultMap});
  
    	});
 	 }
  

  </script>
  
  
</body>
</html>