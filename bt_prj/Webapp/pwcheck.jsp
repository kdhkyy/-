<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <!-- 헤더 css / jquery cdn -->
  <%@ include file="/include/header.jsp" %>
  
<script>
$(document).ready(function(){
/* 	var pw = userpw.val();
	var pw2 = ${KEY_MVO.uesrId};
 	if( pw == pw2){
	
}
	
	$("#regForm").submit();   */
});    
</script>
</head>

<body>

<!-- 컨텐츠영역 -->
  <div id="app">
    <section class="section">
      <div class="container mt-5">
        <div class="row">
          <div class="col-12 col-sm-8 offset-sm-2 col-md-6 offset-md-3 col-lg-6 offset-lg-3 col-xl-4 offset-xl-4">
            <div class="login-brand">
              Member Register
            </div>

            <div class="card card-primary">
              <div class="card-header"><h4>Memberinfo Edit</h4></div>

              <div class="card-body">
                
                <!-- ----------------------비밀번호 체크 폼------------------------------------- -->
                <form name="regForm" method="POST" action="/pwcheck" class="needs-validation" novalidate="">
                 <input type="hidden" name="mode" value="${param.mode}">
                  <div class="form-group">
                    <label for="userid">User ID</label>
                    <input id="userid" type="text" class="form-control" name="userid" 
                    	   tabindex="1" value="${KEY_MVO.userId}" required autofocus>
                    
                 <!--    <div class="invalid-feedback"> //값을 잘 못 넣거나 넣지 않은 경우 아래와 같은 문구를 넣음
                      ID를 입력하세요.
                    </div>
                  </div> -->

                  <div class="form-group">
                    <label for="password" class="d-block">Password
                      <div class="float-right">
                        <a href="forgot.jsp">
                          Forgot Password?
                        </a>
                      </div>
                    </label>
                    <input id="userpw" type="password" class="form-control" name="userpw" tabindex="2" required>
                    <div class="invalid-feedback">
                     	비밀번호를 입력하세요.
                    </div>
                  </div>

<!--                   <div class="form-group">
                    <div class="custom-control custom-checkbox">
                      <input type="checkbox" name="remember" class="custom-control-input" tabindex="3" id="remember-me">
                      <label class="custom-control-label" for="remember-me">Remember Me</label>
                    </div>
                  </div>
 -->
                  <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block" tabindex="4">
                      Edit
                    </button>
                  </div>
                </form>
                <!-- 로그인 폼 종료 -->
                
                
              </div>
            </div>
            <div class="mt-5 text-muted text-center">
              Don't have an account? <a href="register.jsp">Create One</a>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
<!-- 컨텐츠영역 끝 -->

 			<!-- 푸터 영역 -->
      		<%@ include file="/include/footer.jsp" %>
            

 			<!-- 스크립트 영역 -->
 			<%@ include file="/include/script.jsp" %>
</body>
</html>