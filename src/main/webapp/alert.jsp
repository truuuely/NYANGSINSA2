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
	swal({
        text : '${msg}',
        button : "확인"
     })
	.then(function(){
		if(${lang != null}){
		       location.href = '${location}?lang=${lang}';
		    }
		else if(${vo != null}){
			location.href = '${location}?'+vo+'=${'+vo+'}';
		}
		    else{
		       location.href = '${location}';
		    }
	})
});




</script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</html>