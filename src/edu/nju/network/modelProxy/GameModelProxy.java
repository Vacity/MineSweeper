package edu.nju.network.modelProxy;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.nju.controller.msgqueue.operation.MineOperation;
import edu.nju.controller.msgqueue.operation.SetCustomGame;
import edu.nju.controller.msgqueue.operation.SetEasyGame;
import edu.nju.controller.msgqueue.operation.SetHardGame;
import edu.nju.controller.msgqueue.operation.SetHellGame;
import edu.nju.controller.msgqueue.operation.StartGameOperation;
import edu.nju.model.impl.GameLevel;
import edu.nju.model.impl.UpdateMessage;
import edu.nju.model.service.ChessBoardModelService;
import edu.nju.model.service.GameModelService;
import edu.nju.model.service.StatisticModelService;
import edu.nju.model.state.GameResultState;
import edu.nju.model.state.GameState;
import edu.nju.model.vo.GameVO;
import edu.nju.network.client.ClientService;
/**
 * GameModel的代理，在网络对战时替代Client端的相应Model。
 * @author 晨晖
 *
 */

public class GameModelProxy extends ModelProxy implements GameModelService{
	
	
	public GameModelProxy(ClientService client){
		this.net = client;
	}

	@Override
	public boolean setGameLevel(String level) {
		// TODO Auto-generated method stub
		switch(level){
		case"小":net.submitOperation(new SetEasyGame() );
		case"中":net.submitOperation(new SetHardGame());
		case"大":net.submitOperation(new SetHellGame());
		}
		
		return false;
	}

	@Override
	public boolean startGame() {
		// TODO Auto-generated method stub
		MineOperation op = new StartGameOperation();
		net.submitOperation(op);
		return true;
	}

	@Override
	public boolean gameOver(GameResultState result) {
		// TODO Auto-generated method stub
			
		return false;
	}


	@Override
	public boolean setGameSize(String level,int width, int height, int mineNum) {
		// TODO Auto-generated method stub
		net.submitOperation(new SetCustomGame(width,height,mineNum));
		
		return true;
	}

	@Override
	public List<GameLevel> getGameLevel() {
		// TODO Auto-generated method stub
		return null;
	}

}
