package edu.nju.network.modelProxy;

import edu.nju.controller.msgqueue.operation.DoubleClickOperation;
import edu.nju.controller.msgqueue.operation.LeftClickOperation;
import edu.nju.controller.msgqueue.operation.RightClickOperation;
import edu.nju.model.service.ChessBoardModelService;
import edu.nju.model.service.GameModelService;
import edu.nju.network.client.ClientService;

public class ChessBoardModelProxy extends ModelProxy implements ChessBoardModelService{
	public ChessBoardModelProxy(ClientService net){
		this.net=net;
	}

	public boolean excavate(int x, int y) {
		// TODO Auto-generated method stub
		net.submitOperation(new LeftClickOperation(x,y));
		return false;
	} 

	@Override
	public boolean mark(int x, int y) {
		// TODO Auto-generated method stub
		net.submitOperation(new RightClickOperation(x,y));
		return false;
	}

	@Override
	public boolean quickExcavate(int x, int y) {
		// TODO Auto-generated method stub
		net.submitOperation(new DoubleClickOperation(x,y));
		return false;
	}

	@Override
	public void setGameModel(GameModelService gameModel) {
		// TODO Auto-generated method stub	
	}

	@Override
	public boolean initialize(int width, int height, int mineNum) {
		// TODO Auto-generated method stub
		return false;
	}

}
