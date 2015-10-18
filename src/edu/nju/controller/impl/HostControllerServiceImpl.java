package edu.nju.controller.impl;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.service.HostControllerService;
import edu.nju.model.impl.ChessBoardModelImpl;
import edu.nju.model.impl.GameModelImpl;
import edu.nju.model.impl.ParameterModelImpl;
import edu.nju.model.impl.StatisticModelImpl;
import edu.nju.network.host.HostInHandlerImpl;
import edu.nju.network.host.HostServiceImpl;

public class HostControllerServiceImpl implements HostControllerService{

	@Override
	public boolean serviceetupHost() {
		// TODO Auto-generated method stub
		HostServiceImpl host = new HostServiceImpl();
		HostInHandlerImpl hostH = new HostInHandlerImpl();
		
		GameModelImpl game = (GameModelImpl)OperationQueue.getGameModel();
		game.addObserver(host);
		ChessBoardModelImpl chessBoard = (ChessBoardModelImpl)OperationQueue.getChessBoardModel();
		chessBoard.addObserver(host);
		if(host.init(hostH)){
			System.out.println("Connecting!!!");
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			game.startGame();
		} 
		return false;
	}

}
