package edu.nju.controller.msgqueue.operation;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.model.service.GameModelService;

public class SetCustomGame extends MineOperation{
int height;
int width;
int mineNum;

	public SetCustomGame(int height,int width,int mineNum){
		this.height=height;
		this.width=width;
		this.mineNum=mineNum;
		
	}
	public void execute() {
		// TODO Auto-generated method stub
GameModelService game = OperationQueue.getGameModel();
		
		game.setGameSize("自定义",width, height, mineNum);
		game.startGame();
	}

}
