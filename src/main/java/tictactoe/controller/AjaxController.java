package tictactoe.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import tictactoe.controller.beans.CellBean;
import tictactoe.model.GameField;
import tictactoe.model.GameStatus;
import tictactoe.model.Player;
import tictactoe.model.PlayerO;
import tictactoe.model.PlayerX;

/**
 * Servlet implementation class AjaxController
 */
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		ServletContext context = getServletContext();
		String myName = request.getParameter("name");

		if (uri.contains("click.do")) {
			int x = Integer.parseInt(request.getParameter("x"));
			int y = Integer.parseInt(request.getParameter("y"));
			GameField game = (GameField) request.getSession().getAttribute(
					"game");
			Player player = game.getPlayerByName(myName);
			String type = null;
			if (player instanceof PlayerX) {
				type = "X";
			} else if (player instanceof PlayerO) {
				type = "O";
			} else {
				// TODO
			}
			CellBean cellBean = new CellBean();
			cellBean.setType(type);
			GameStatus status = player.turn(x, y);
			System.out.println(status.toString());
			if (!status.toString().equals(GameStatus.CONTINUE.toString())) {
				cellBean.setResult(status.toString());
			}
			response.setContentType("text/html");
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");

			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setHeader("Access-Control-Max-Age", "86400");
			Gson gson = new Gson();
			JsonObject myObj = new JsonObject();

			JsonElement cellObj = gson.toJsonTree(cellBean);
			myObj.add("cell", cellObj);
			PrintWriter out = response.getWriter();
			out.println(myObj.toString());
			out.close();

		} else if (uri.contains("startGame.do")) {
			GameField game = (GameField) request.getSession().getAttribute(
					"game");
			while (game.getPlayerO() == null || game.getPlayerX() == null) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Player player = game.getPlayerByName(myName);

			String type = null;
			if (player instanceof PlayerX) {
				type = "X";
			} else if (player instanceof PlayerO) {
				type = "O";
			} else {
				// TODO
			}

			response.setContentType("text/plain");

			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(type);
			/*
			 * if (game.getPlayerO().equals(myName)) {
			 * response.getWriter().write(game.getPlayerX().getName()); } else {
			 * response.getWriter().write(game.getPlayerO().getName()); }
			 */
		} else if (uri.contains("anotherTurn.do")) {
			GameField game = (GameField) request.getSession().getAttribute(
					"game");
			while (!game.isAnotherTurn()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			game.setAnotherTurn(false);

			CellBean cellBean = game.lastCell;
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setHeader("Cache-control", "no-cache, no-store");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires", "-1");

			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setHeader("Access-Control-Max-Age", "86400");
			Gson gson = new Gson();
			JsonObject myObj = new JsonObject();

			JsonElement cellObj = gson.toJsonTree(cellBean);
			myObj.add("cell", cellObj);
			out.println(myObj.toString());
			out.close();
		} else if (uri.contains("gameList.do")) {
			Map<String, GameField> gameList = (Map<String, GameField>) context
					.getAttribute("gameList");
			if (gameList != null) {
				List<String> gameListList = new ArrayList<String>(
						gameList.keySet());

				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				response.setHeader("Cache-control", "no-cache, no-store");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Expires", "-1");

				response.setHeader("Access-Control-Allow-Origin", "*");
				response.setHeader("Access-Control-Allow-Methods", "POST");
				response.setHeader("Access-Control-Allow-Headers",
						"Content-Type");
				response.setHeader("Access-Control-Max-Age", "86400");
				Gson gson = new Gson();
				JsonObject myObj = new JsonObject();

				JsonElement jsonObj = gson.toJsonTree(gameListList);
				myObj.add("gameList", jsonObj);
				out.println(myObj.toString());
				out.close();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
