package git_source;

import java.io.File;

public interface GameSnake {
	
	default void startSnake() {
		initSnake();	
	}
	public void initSnake();
	public void saveChanges(File file);
	public void loadChanges(File file);
	public void closeSnake();
	
}
