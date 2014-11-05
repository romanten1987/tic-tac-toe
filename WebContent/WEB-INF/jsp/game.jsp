<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:c="http://java.sun.com/jsp/jstl/core">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<jsp:text>
		<![CDATA[ <?xml version="1.0" encoding="UTF-8" ?> ]]>
	</jsp:text>
	<jsp:text>
		<![CDATA[ <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> ]]>
	</jsp:text>
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script>
	var gameName = '${game.name}';
	var user = "${user}";
</script>
<script src="http://code.jquery.com/jquery-latest.min.js">
	<jsp:text />
</script>
<script type="text/javascript"
	src="http://www.html5canvastutorials.com/libraries/kinetic-v4.1.0.js">
	<jsp:text />
</script>
<script type="text/javascript" src="js/game.js">
	<jsp:text />
</script>
<title>Insert title here</title>
</head>
<body>
	<h1>Tic-tac-toe</h1>
	<h2 id ="X">
		X:
		<c:out value="${game.playerX.name}" />
	</h2>
	<h2 id = "O">
		O:
		<c:out value="${game.playerO.name}" />
	</h2>

	<div id="container"></div>	
	<div id="status"></div>
	<div id="win"></div>


</body>
	</html>
</jsp:root>