package tictak.model;

import java.util.Random;

import tictak.controller.beans.CellBean;

public class GameField {
	private int[][] field;
	private String name;
	private Player playerX;
	private Player playerO;
	private boolean doAnotherTurn = false;
	public CellBean lastCell;

	public GameField(String gameName) {
		this.name = gameName;
		field = new int[3][3];
	}

	/**
	 * @return if 0 then game continue, if 1 then X win, if 2 then 0 win, if -1
	 *         then draw.
	 */
	public int gameOver() {
		if ((field[1][1] == 1 && field[2][2] == 1 && field[0][0] == 1)
				|| (field[0][2] == 1 && field[1][1] == 1 && field[2][0] == 1)) {
			return 1;
		} else {
			for (int i = 0; i < 3; i++) {
				if ((field[i][0] == 1 && field[i][1] == 1 && field[i][2] == 1)
						|| (field[0][i] == 1 && field[1][i] == 1 && field[2][i] == 1)) {
					return 1;
				}
			}
		}

		if (((field[1][1] == 2 && field[2][2] == 2 && field[0][0] == 2) || (field[0][2] == 2
				&& field[1][1] == 2 && field[2][0] == 2))) {
			return 2;
		} else {
			for (int i = 0; i < 3; i++) {
				if ((field[i][0] == 2 && field[i][1] == 2 && field[i][2] == 2)
						|| (field[0][i] == 2 && field[1][i] == 2 && field[2][i] == 2)) {
					return 2;
				}
			}
		}
		for (int[] cell : field) {
			for (int i : cell) {
				if (i == 0) {
					return 0;
				}
			}
		}
		return -1;
	}

	public boolean isEmpty(int x, int y) {
		return field[x][y] == 0;
	}

	public void setX(int x, int y) {
		field[x][y] = 1;
		lastCell = new CellBean();
		lastCell.setX(x);
		lastCell.setY(y);
		lastCell.setType("X");
		setAnotherTurn(true);		
		print();

	}

	public void setO(int x, int y) {
		field[x][y] = 2;
		lastCell = new CellBean();
		lastCell.setX(x);
		lastCell.setY(y);
		lastCell.setType("O");		
		setAnotherTurn(true);
		print();

	}
	
	private void print() {
		for (int i = 0; i < 3; i++) {
			System.out.println();
			for (int j = 0; j<3; j++) {
				System.out.print(field[i][j]);
			}
		}
	}

	public Player generatePlayer() {
		if (playerO == null && playerX == null) {
			Random rnd = new Random();
			if (rnd.nextBoolean()) {
				return new PlayerX(this);
			} else {
				return new PlayerO(this);
			}
		} else if (playerO != null && playerX == null) {
			return new PlayerX(this);
		} else if (playerO == null && playerX != null) {
			return new PlayerO(this);
		} else {
			return null;
		}

	}

	public void addPlayer(Player player) {
		if (player instanceof PlayerX) {
			playerX = player;
		} else {
			playerO = player;
		}

	}

	public String getName() {
		return name;
	}

	public Player getPlayerX() {
		return playerX;
	}

	public Player getPlayerO() {
		return playerO;
	}

	public Player getPlayerByName(String myName) {
		if (playerO.getName().equals(myName)) {
			return playerO;
		} else {
			return playerX;
		}
	}

	public boolean isAnotherTurn() {		
		return doAnotherTurn;
	}
	
	public void setAnotherTurn(boolean bool) {
		doAnotherTurn = bool;
	}

}
