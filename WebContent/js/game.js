$(document)
		.ready(
				function fnFormatDetails() {
					var type;
					$
							.ajax({
								url : 'startGame.do',
								data : {
									game : gameName,
									name : user
								},
								dataType : "text",
								success : function(data) {
									type = data;
									createField();

									if (type === 'O') {
										document.getElementById('status').innerHTML = "Ход противника";
										$
												.ajax({
													url : 'anotherTurn.do',
													data : {
														game : gameName
													},
													dataType : "json",
													success : function(data) {
														draw(data.cell.type,
																data.cell.x,
																data.cell.y);
														document
																.getElementById('status').innerHTML = "Ваш ход";
													}
												});
									} else {
										document.getElementById('status').innerHTML = "Ваш ход";
									}
								}
							});

					var stage = new Kinetic.Stage({
						container : 'container',
						width : 300,
						height : 300
					});
					var fieldLayer = new Kinetic.Layer();
					var gameLayer = new Kinetic.Layer();

					function createField() {

						var fields = new Array();
						var fieldSize = 100;
						for ( var i = 0; i < 3; i++) {
							fields[i] = new Array();
							for ( var j = 0; j < 3; j++) {
								fields[i][j] = new Kinetic.Rect({
									x : i * fieldSize,
									y : j * fieldSize,
									width : 100,
									height : 100,
									stroke : 'black'
								});
							}
						}

						for ( var i = 0; i < 3; i++) {
							for ( var j = 0; j < 3; j++) {
								fieldLayer.add(fields[i][j]);
								var t = fields[i][j];
								t
										.on(
												'mousedown',
												function(x, y) {
													return function() {
														$
																.ajax({
																	url : 'click.do',
																	dataType : "json",
																	data : {
																		game : gameName,
																		name : user,
																		x : x,
																		y : y
																	},
																	success : function(
																			data) {
																		draw(
																				data.cell.type,
																				x,
																				y);

																		if (data.cell.result) {
																			document
																					.getElementById('status').innerHTML = data.cell.result;
																		} else {
																			document
																					.getElementById('status').innerHTML = "Ход противника";
																		}
																		;
																		$
																				.ajax({
																					url : 'anotherTurn.do',
																					data : {
																						game : gameName
																					},
																					dataType : "json",
																					success : function(
																							data) {
																						draw(
																								data.cell.type,
																								data.cell.x,
																								data.cell.y);
																						document
																								.getElementById('status').innerHTML = "Ваш ход";
																					}
																				});

																	}
																});
													};
												}(i, j));
							}
						}

						stage.add(fieldLayer);
						stage.add(gameLayer);

					}
					/*
					 * var type = data; $.ajax({ url : 'anotherTurn.do', data : {
					 * game : gameName, name : user, x : x, y : y, type : type },
					 * dataType : "text", success : function(data) {
					 * draw(data.type, data.x, data.y); } });
					 */

					function draw(type, x, y) {
						if (type === 'X') {
							drawX(x, y);
						}
						if (type === 'O') {
							drawO(x, y);
						}

					}
					function drawX(x, y) {
						var line1 = new Kinetic.Line({
							points : [ x * 100, y * 100, x * 100 + 100,
									y * 100 + 100, x * 100, y * 100 + 100,
									x * 100 + 100, y * 100 ],
							stroke : 'red',
							strokeWidth : 3
						});
						gameLayer.add(line1);
						stage.add(gameLayer);
					}

					function drawO(x0, y0) {

						var circle = new Kinetic.Circle({
							x : x0 * 100 + 50,
							y : y0 * 100 + 50,
							radius : 40,
							fill : 'red',
							stroke : 'black',
							strokeWidth : 4
						});

						gameLayer.add(circle);
						stage.add(gameLayer);

					}

				});
