package tictak.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tictak.model.GameField;
import tictak.model.Player;
import tictak.model.PlayerO;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) {
		ServletContext context = getServletContext();
		Map<String, GameField> gameList = (Map<String, GameField>) context
				.getAttribute("gameList");

		if (gameList == null) {
			gameList = new HashMap<String, GameField>();
		}

		String uri = request.getRequestURI();
		if (uri.equals("/TicTak/gameSettings.htm")) {
			String user = request.getParameter("user");
			request.getSession().removeAttribute("user");
			request.getSession().setAttribute("user", user);
			String page = "gameSettings";
			forward(request, response, page);
		} else if (uri.equals("/TicTak/createGame.htm")) {
			String gameName = request.getParameter("gameName");
			GameField game = new GameField(gameName);
			request.getSession().removeAttribute("game");
			request.getSession().setAttribute("game", game);
			Player player = game.generatePlayer();
			player.setName(request.getSession().getAttribute("user").toString());
			game.addPlayer(player);
			gameList.put(game.getName(), game);

			context.removeAttribute("gameList");
			context.setAttribute("gameList", gameList);
			request.setAttribute("game", game);

			forward(request, response, "game");

		} else if (uri.equals("/TicTak/joinGame.htm")) {
			String gameName = request.getParameter("game");
			GameField game = gameList.get(gameName);
			request.getSession().setAttribute("game", game);
			gameList.remove(gameName);
			context.removeAttribute("gameList");
			context.setAttribute("gameList", gameList);
			Player player = game.generatePlayer();
			player.setName(request.getSession().getAttribute("user").toString());
			game.addPlayer(player);
			request.setAttribute("game", game);
			forward(request, response, "game");
		}
	}

	private void forward(HttpServletRequest request,
			HttpServletResponse response, String page) {
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/jsp/" + page + ".jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
