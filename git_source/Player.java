package git_source;

import java.io.File;

public class Player extends Snake {
	
	static Snake snake;
	static File file = new File("C:/Users/Vitalik/workspace/Git/src/snake_files");
	static Player player;
	
	public Player(File file) {
		super(file);
		
	}
	
	private static void playSnake() {
		new Snake(file);
		snake.startSnake();
	}
	private static void exitSnake() {
		snake.saveChanges(file);
		snake.closeSnake();
	}
	public static void main(String[] args) {
		
		playSnake();
		exitSnake();
		
	}

}
