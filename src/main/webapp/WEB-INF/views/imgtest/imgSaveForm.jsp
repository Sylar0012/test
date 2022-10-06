<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <form enctype="multipart/form-data" id ="fileUploadForm">
		<div class="form-group">
			<input id="title" type="text" placeholder="제목">
			<br/>
			<br/>
            <input type="file" id ="file" />
			<br/>
			<br/>
			<input id="content" type ="text" placeholder="내용">
			<br/>
			<br/>
         	<button type="button" id="btnSave">작성완료</button>
		</div><%-- form-group--%>
	</form>
</body>
<script>

	$("#btnSave").click(()=>{
		Save();
	});


	function Save(){

		let formData = new FormData();
	
		let data ={

		}

		formData.append('title',$("#title").val());
		formData.append('content',$("#content").val());
		formData.append('file', $("#file")[0].files[0]);

		formData.append('key', new Blob([ JSON.stringify(data) ], {type : "application/json"}));
		
		$.ajax("/imgtest/img",{
			type : "POST",
			data : formData,
			processData: false,    
       		contentType: false, 
			enctype : 'multipart/form-data'
		}).done((res) => {
			if (res.code == 1) {
				alert("이미지 등록 성공");
				}
			});
		}

</script>
</html>