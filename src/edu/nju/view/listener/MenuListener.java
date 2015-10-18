/*
 *
 * TODO To manage menu action
 */
package edu.nju.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.nju.controller.impl.ClientControllerServiceImpl;
import edu.nju.controller.impl.HostControllerServiceImpl;
import edu.nju.controller.impl.MenuControllerImpl;
import edu.nju.controller.impl.SettingControllerImpl;
import edu.nju.controller.msgqueue.OperationQueue;
import edu.nju.controller.msgqueue.operation.StartGameOperation;
import edu.nju.controller.service.ClientControllerService;
import edu.nju.controller.service.HostControllerService;
import edu.nju.controller.service.MenuControllerService;
import edu.nju.controller.service.SettingControllerService;
import edu.nju.network.Configure;
import edu.nju.view.CustomDialog;
import edu.nju.view.MainFrame;
import edu.nju.view.RecordDialog;
 

public class MenuListener implements ActionListener{

	private MainFrame ui;
	MenuControllerService menuController = new MenuControllerImpl();
	SettingControllerService settingController = new SettingControllerImpl();
	ClientControllerService clientController = new ClientControllerServiceImpl();
	HostControllerService hostController = new HostControllerServiceImpl();
	
	public MenuListener(MainFrame ui){
		this.ui = ui;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == ui.getMenuItem("start")) {//生成游戏，默认生成简单游戏
			menuController.startGame();
		} else if (e.getSource() == ui.getMenuItem("easy")) {//生成简单游戏
			settingController.setEasyGameLevel();
		} else if (e.getSource() == ui.getMenuItem("hard")) {//生成中等游戏
			settingController.setHardGameLevel();
		} else if (e.getSource() == ui.getMenuItem("hell")) {//生成大型游戏
			settingController.setHellGameLevel();
		} else if (e.getSource() == ui.getMenuItem("custom")) {//生成定制游戏，需要向controller传递棋盘的高、宽和雷数
			JFrame mframe = ui.getMainFrame();
            CustomDialog customframe = new  CustomDialog(mframe) ;
            customframe.show();
            settingController.setCustomizedGameLevel("自定义",customframe.getWidth(),customframe.getHeight(),customframe.getMineNumber());
		} else if (e.getSource() == ui.getMenuItem("exit")) {
			System.exit(0);
		} else if (e.getSource() == ui.getMenuItem("record")) {//统计胜率信息
			JFrame mframe = ui.getMainFrame();
            RecordDialog recordDialog = new RecordDialog(mframe) ;
            recordDialog.show();
		}else if(e.getSource() == ui.getMenuItem("host")){//注册成为主机
			hostController.serviceetupHost();
		}else if(e.getSource() == ui.getMenuItem("client")){//注册成为客户端
			JFrame f = ui.getMainFrame();
			f.setVisible(false); 
			clientController.setupClient("127.0.0.1");
		}
	}
	
	

}