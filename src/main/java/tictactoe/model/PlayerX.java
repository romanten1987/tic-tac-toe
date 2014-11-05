package tictactoe.model;

public class PlayerX implements Player {

	private GameField gameField;
	private String name;

	public PlayerX(GameField gameField) {
		this.gameField = gameField;
	}

	public GameStatus turn(int x, int y) {
		GameStatus gameStatus = null;
		if (gameField.isEmpty(x, y)) {
			gameField.setX(x, y);
			int result = gameField.gameOver();
			if (result == 1) {
				gameStatus = GameStatus.WIN;
			} else if (result == 2) {
				gameStatus = GameStatus.LOST;
			} else if (result == -1) {
				gameStatus = GameStatus.DRAW;
			} else {
				gameStatus = GameStatus.CONTINUE;
			}
		} else {
			// XXX
		}

		return gameStatus;
	}

	public void setName(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

}
