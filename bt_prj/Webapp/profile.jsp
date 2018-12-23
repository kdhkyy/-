<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <!-- 헤더 영역 css/jquery cdn  -->
   <%@ include file="/include/header.jsp" %>
   
<script>
$(document).ready(function(){
	//------------첨부파일 미리보기----------------------
	$("#pname").on("change", myFilePreviewFunc);  //.on(); 동적실행(실행될때마다 추가로 생성되는 버튼등을 사용하기 위해서는 on을 사용해야 한다.)
	
	function myFilePreviewFunc(e) {
			$("#prev-img-div").empty();             //div 냅두고 안에 내용만 지워라 = empty();
			var files = e.target.files;  			//[object FileList] 파일리스트가 오브젝트 형식으로 와짐
			//FileList into an array 
			//var fileArr = Array.prototype.slice.call(files);			
			var fileArr = Array.from(files); //[object File],[object File],[object File] 파일을 낱개로 잘라 배열로 정렬 
				
			if(fileArr.length > 3) {  //files.length
					alert("이미지 첨부는 최대 3개만 가능합니다.");
					$("#pname").val("");
					return false;
			}
			
			var fileSize = 0;
			fileArr.forEach(function(f) {
					fileSize += f.size;
			});
			if(fileSize > 10*1024*1024) {
					alert("이미지 첨부는 최대 10MB만 가능합니다.");
					$("#pname").val("");
					return false;
			}
			
			fileArr.forEach(function(f) {
					if(!f.type.match("image.*")) {
							alert("이미지 첨부만 가능합니다.");
							$("#pname").val("");
							return false;
					} 
					
					var reader = new FileReader();
					var htmlStr = "";
					reader.onload = function(e) {
							htmlStr += "<img src='"+e.target.result+"' style='height:80px;width:80px;'> ";
							$("#prev-img-div").append(htmlStr);
							//alert(htmlStr)
					}
					//reader.onload = function(e) { 
					//	$("#prev-img").attr("src", e.target.result); 
					//} 
					reader.readAsDataURL(f); 
			});
			
			
	}
	
	  $("#regButton").click(function(){	
		 // var id = $("#user_id").val();
		  var pw = $("#user_pw").val();
		  var pw2 = $("#user_pw2").val();
		  var agree = $("[id='agree']:checked").val();
		  
		  
		 /*  if(id == ""){
			  alert("아이디를 입력하세요")
			  $("#user_id").focus();
			  return false;
		  } */
		  if(pw == ""){
			  alert("비밀번호를 입력하세요")
			  $("#user_pw").focus();
			  return false;
		  }
		  if(pw != pw2){
			  alert("입력한 비밀번호가 일치하지 않습니다.")
			  $("#user_pw").focus();
			  return false;
		  } else if(agree != 'y'){
			  alert("약관동의에 체크하세요.")
			  $("#agree").focus();
			  return false;
		  }
		$("#regForm").submit();  
		
		actiongubun.val("delete")
		$("#regForm").attr("action","/del");
		$("#regForm").attr("method","post");
		$("#regForm").submit();
	});
	  
	  //회원정보 삭제 ------------------------------------
	  $("#delButton").click(function(){
		  actiongubun.val("delete")
			var result = confirm('정말 탈퇴하시겠습니다?');
		  	if(result){
		  		$(location).attr('href','/pwcheck.jsp?mode=del');
		  	}
		  
		  
		    //$("#regForm").attr("action","/del");
			//$("#regForm").attr("method","post");
			//$("#regForm").submit(); 
	  });
}); 

</script>
</head>

<body>
  <div id="app">
    <section class="section">
      <div class="container mt-5">
        <div class="row">
          <div class="col-12 col-sm-10 offset-sm-1 col-md-8 offset-md-2 col-lg-8 offset-lg-2 col-xl-8 offset-xl-2">
            <div class="login-brand">
              Member Profile Edit
            </div>

            <div class="card card-primary">
              <div class="card-header"><h4>Register</h4></div>
				
				
				
		<!-- ---------------------------------수정폼--------------------------------------------------- -->
              <div class="card-body" >
                <form id="regForm" method="POST" action="/edit" 
                		class="needs-validation" enctype="multipart/form-data"> <!-- 첨부파일넣기 -->
                  <div class="row">
                    <div class="form-group col-6">
                      <label for="frist_name">User ID</label>
                      <input id="user_id" type="text" class="form-control" name="user_id" 
                      			value=${KEY_MVO.userId} readonly>
                    </div>
                    <div class="form-group col-6">
                      <label for="last_name">User Name</label>
                      <input id="user_name" type="text" class="form-control" name="user_name" value=${KEY_MVO.userName}>
                      <div class="invalid-feedback"> <!-- 값을 잘 못 넣거나 넣지 않은 경우 아래와 같은 문구를 넣음 -->
                      	
                    	</div>
                    </div>
                  </div>

                  <div class="form-group">
                    <label for="email">Email</label>
                    <input id="user_email" type="email" class="form-control" name="user_email" value=${KEY_MVO.userEmail} required autofocus>
                    <div class="invalid-feedback">
                  	  올바른 email형식을 입력하세요.
                    </div>
                  </div>
                  
  
                  <div class="row">
                    <div class="form-group col-6">
                      <label for="password" class="d-block">Password</label>
                      <input id="user_pw" type="password" class="form-control" name="user_pw">
                    </div>
                    <div class="form-group col-6">
                      <label for="password2" class="d-block">Password Confirmation</label>
                      <input id="user_pw2" type="password" class="form-control" name="user_pw2">
                    </div>
                  </div>
                  
                  
               <div class="row">
					<div class="form-group col-6">
		                <label for="email">프로필사진</label>
		                <input id="pname" type="file" class="form-control" name="pname">
		                <div class="invalid-feedback">
		                    	사진 등록은 필수 입니다.
		                </div>
		               </div>
		                <div id="prev-img-div" class="form-group col-6">
		                  <!-- <img id="prev-img" style="height:100px;width:100px;"> -->
		               </div>
				</div>

                 <!--  <div class="form-divider">
                    Your Home
                  </div>
                  <div class="row">
                    <div class="form-group col-6">
                      <label>Country</label>
                      <select class="form-control">
                        <option>Indonesia</option>
                        <option>Palestine</option>
                        <option>Syria</option>
                        <option>Malaysia</option>
                        <option>Thailand</option>
                      </select>
                    </div>
                    <div class="form-group col-6">
                      <label>Province</label>
                      <select class="form-control">
                        <option>West Java</option>
                        <option>East Java</option>
                      </select>
                    </div>
                  </div>
                  <div class="row">
                    <div class="form-group col-6">
                      <label>City</label>
                      <input type="text" class="form-control">
                    </div>
                    <div class="form-group col-6">
                      <label>Postal Code</label>
                      <input type="text" class="form-control">
                    </div>
                  </div> -->
<!-- 
                  <div class="form-group">
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" name="agree" class="custom-control-input" id="agree" value="y">
                      <label class="custom-control-label" for="agree">약관동의</label>
                    </div>
                  </div>
 -->
 	
 				 <div class="row">
                  <div class="form-group col-6">
                    <button type="button" id="regButton" class="btn btn-primary btn-block">
                      Edit
                    </button>
                    </div>
                    <div class="form-group col-6">
                    <button type="button" id="delButton" name="delButton" class="btn btn-primary btn-block">
                      Delete
                    </button>
                  </div>
                  </div>
                </form>
                <!-- --------------------------가입폼---------------------------------- -->
              
              </div>
            </div>
            
             <!-- 풋터영역  -->
      		<%@ include file="/include/footer.jsp" %>
         
         
          </div>
        </div>
      </div>
    </section>
  </div>

  			<!-- 스크립트 -->
     		<%@ include file="/include/script.jsp" %>
</body>
</html>