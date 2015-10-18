package edu.nju.controller.impl;

import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.service.ClientControllerService;
import edu.nju.network.client.ClientInHandlerImpl;
import edu.nju.network.client.ClientServiceImpl;
import edu.nju.network.modelProxy.ChessBoardModelProxy;
import edu.nju.network.modelProxy.GameModelProxy;
import edu.nju.view.MainFrame;

public class ClientControllerServiceImpl implements ClientControllerService{

	@Override
	public boolean setupClient(String ip) {
		// TODO Auto-generated method stub
	 
	ClientServiceImpl client = new ClientServiceImpl();
	ClientInHandlerImpl clientH = new ClientInHandlerImpl();
	MainFrame ui = new MainFrame();
	
	GameModelProxy gameProxy = new GameModelProxy(client);
	gameProxy.addObserver(ui);
	ChessBoardModelProxy chessBoardModelProxy = new ChessBoardModelProxy(client);
	chessBoardModelProxy.addObserver(ui.getMineBoard());
	
	clientH.addObserver(chessBoardModelProxy);
	clientH.addObserver(gameProxy);
	client.init(ip, clientH);
	OperationQueue.changBoardModel(chessBoardModelProxy);
	OperationQueue.changGameModel(gameProxy);
	
		return true;
	}


}
