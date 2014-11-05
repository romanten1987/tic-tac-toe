$(document).ready(
		function fnFormatDetails() {
			$.ajax({
				url : 'gameList.do',
				data : {
					game : gameName,
					name : user
				},
				dataType : "json",
				success : function(data) {
					$('#gameList').append = '<table>';
					for ( var i = 0, len = data.gameList.length; i < len; ++i) {
						var gameName = data.gameList[i];
						$('#gameList').append(
								'<tr><td>' + gameName
										+ '</td><td><a href=joinGame.htm?game='
										+ gameName + '>join</a></td></tr>');

						/* $("<div id=\"gameList\">" + "bla" + "</div>") */
					}
					$('#gameList').append('</table>');

				}
			});

			/*
			 * var auto_refresh = setInterval(function() {
			 * $('#gameList').fadeOut('slow').load(alert("dfsd")).fadeIn("slow"); },
			 * 20000);
			 */

		});
