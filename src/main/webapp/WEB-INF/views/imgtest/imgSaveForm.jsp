<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>

<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form method="post" action="/imgtest/img" enctype="multipart/form-data">
		<div class="form-group">
			<div>
   				<form method="post" action="/upload" enctype="multipart/form-data">
            <input type="file" name="file" />
          <button type="submit">파일전송</button>
     </form>
</div><%-- form-group--%>
</body>
<script>

	$("#btnSave").click(()=>{
		Save();
	});


	function Save(){
		let data = new FormData();
		
		console.log($("#inputFile"))
		
		data.append('files',$("#inputFile"))

		$.ajax("/imgtest/img",{
			type : "POST",
			dataType :"json",
            processData: false,
            contentType: false,
			data: JSON.stringify(data),
			headers: {
				"Content-Type": "application/boundary; charset=utf-8"
			}
		}).done((res) => {
			if (res.code == 1) {
				alert("이미지 등록 성공");
				}
			});
		}

</script>
</html>