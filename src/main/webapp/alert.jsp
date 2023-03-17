<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="css/alert.css" type="text/css">
<body>

</body>


<script>

document.addEventListener("DOMContentLoaded", function(){
	var location = '${location}';
	swal({
        text : '${lang} / ${location} / ${msg}',
        button : "확인"
     })
	.then(function(){
		if(location.indexOf('main.do') !== -1 || location.indexOf('login.do') !== -1 || location.indexOf('register.do') !== -1){
		    console.log('1');
		    location.href = '${location}?lang=${lang}';
		}
		else if(${vo != null}){
			console.log('2');
			location.href = '${location}?'+vo+'=${'+vo+'}';
		}
	    else{
	    	console.log('3');
	        location.href = "${location}";
	        console.log('4');
	    }
	})
});




</script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</html>