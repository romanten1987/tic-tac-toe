<!DOCTYPE html>
<html lang="en">
<head>
<title>SO question 4112686</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).ready(function fnFormatDetails() {
		var response = '';
		$.ajax({
			url : 'someservlet',
			dataType : "text",
			success : function(data) {
				alert(data);
			}
		});	
		
	});
</script>
</head>
<body>
	<!-- <button id="somebutton">press here</button> -->
	<div id="somediv"></div>
</body>
</html>