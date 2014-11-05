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
<script src="js/gameSettings.js">
	<jsp:text />
</script>
<title>Create/join game</title>
</head>
<body>
	Hello,
	<c:out value="${user}" />
	!

	<form action="createGame.htm">
		<input name="gameName" type="text" /> <input type="submit"
			value="Create new game" />
	</form>

	<p>Games:</p>
	<table>
		<c:forEach items="${gameList}" var="game">
			<tr>
				<td><c:out value="${game.key}"></c:out></td>
				<td><a href="joinGame.htm?game=${game.key}">join</a></td>
			</tr>
		</c:forEach>
	</table>

	<!-- <div id="gameList"></div> -->




</body>
	</html>
</jsp:root>