<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#google").click(function(){
		
	})

})

</script>

<script>
		function googleLibLoad() {
			 gapi.load('auth2', googleAuth); 
		}
			 
					 
		function googleAuth() { 
				console.log("googleLibLoad().....");
				
				//lib 로드
				GoogleAuth = gapi.auth2.init(
						  {client_id:'879984487341-7imoijvsj2hha8se0nvdiiruf52bdr8e.apps.googleusercontent.com'}
			 	).then(
					  function(){
						  console.log("00.google lib load")
					  },
					  function(){
						  console.log("00.google lib load faild")
					  }
				);
				
				
				var googleAuth = gapi.auth2.getAuthInstance()
				
				//로드 된 lib를 이용하여 로그인
				if(!googleAuth.issignedIn.get()){
					googleAuth.signIn().then(
						function(){
							console.log("로그인 성공!!")
						//로드된 lib를 사용해 로그인한 사용자 정보 가져오기
						var googleUser = googleAuth.currentUser.get();
							console.log("user uniq.ID" + googleUser.getId ());
				
						//GoogleUser.isSignedIn () 이걸로도 사용할 수 있음
				
						var basicProfile = googleUser.getBasicProfile ();
				
							console.log("Profile.ID:"+basicProfile.getId ());
							console.log("Profile.name:"+basicProfile.getName ());
							console.log("Profile.gname:"+basicProfile.getGivenName ());
							console.log("Profile.fname:"+basicProfile.getFamilyName ());
							console.log("Profile.img:"+basicProfile.getImageUrl ());
							console.log("Profile.email:"+basicProfile.getEmail ());
						
					}, function(){
						console.log("로그인 실패.....")
					}
					);
				} else {
					googleAuth.signOut().then(
						function(){
							console.log("2.signOut()");
						})
			}
				
				var authResponse = gapi.auth2.AuthorizeResponse
				
				gapi.auth2.authorize({
					  client_id: '879984487341-7imoijvsj2hha8se0nvdiiruf52bdr8e.apps.googleusercontent.com',
					  scope: 'email profile openid',
					  response_type: 'id_token permission'
					}, function(response) {
					  if (response.error) {
					    // An error happened!
					    return;
					  }
					  // The user authorized the application for the scopes requested.
					  var accessToken = response.access_token;
					  var idToken = response.id_token;
					  // You can also now use gapi.client to perform authenticated requests.
					});
			}
		
		
	
	

	/* $(document).ready(function() {
		.click(function() {
		//	todo
		//});
		
			function googleLibLoad() {
				 var GoogleAuth =  gapi.load('auth2', function() { 
					  gapi.auth2.init(
							  {client_id:'879984487341-7imoijvsj2hha8se0nvdiiruf52bdr8e.apps.googleusercontent.com'})
				  }
				}
	});
	GoogleAuth.then(onInit, onError); */
	
</script>
</head>
<body>
	Google sign-in test
	<hr>

	<script type="button" id="googleAuthBtn" value="로그인"><br></script>
	<div></div>

	<script
		src="http://apis.google.com/js/platform.js?onload=googleLibLoad" async
		defer></script>


</body>
</html>