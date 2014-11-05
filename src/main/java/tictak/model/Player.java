package tictak.model;

public interface Player {

	GameStatus turn(int x, int y);

	void setName(String name);
	
	String getName();

}
