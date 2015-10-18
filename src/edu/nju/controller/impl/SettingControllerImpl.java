package edu.nju.controller.impl;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.MineOperation;
import edu.nju.controller.service.SettingControllerService;
import edu.nju.model.service.GameModelService;

public class SettingControllerImpl implements SettingControllerService{

	@Override
	public boolean setEasyGameLevel() {
		// TODO Auto-generated method stub
GameModelService game = OperationQueue.getGameModel();
		
		game.setGameLevel("小");
		game.startGame();
		return true;
	}

	@Override
	public boolean setHardGameLevel() {
GameModelService game = OperationQueue.getGameModel();
		
		game.setGameLevel("中");
		game.startGame();
		// TODO Auto-generated method stub
		return true;
	} 

	@Override
	public boolean setHellGameLevel() {
GameModelService game = OperationQueue.getGameModel();
		
		game.setGameLevel("大");
		game.startGame();
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean setCustomizedGameLevel(String s,int height, int width, int mineNum) {
GameModelService game = OperationQueue.getGameModel();
		
		game.setGameSize("自定义",width, height, mineNum);
		game.startGame();
		// TODO Auto-generated method stub
		return true;
	}

}
