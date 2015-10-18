package edu.nju.model.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nju.model.po.BlockPO;
import edu.nju.model.service.ChessBoardModelService;
import edu.nju.model.service.GameModelService;
import edu.nju.model.service.ParameterModelService;
import edu.nju.model.state.BlockState;
import edu.nju.model.state.GameResultState;
import edu.nju.model.state.GameState;
import edu.nju.model.vo.BlockVO;

public class ChessBoardModelImpl extends BaseModel implements ChessBoardModelService{
	
	private GameModelService gameModel;
	private ParameterModelService parameterModel;
	
	private BlockPO[][] blockMatrix;
 
	
	public ChessBoardModelImpl(ParameterModelService parameterModel){
		this.parameterModel = parameterModel;
	}

	@Override
	public boolean initialize(int width, int height, int mineNum) {
		// TODO Auto-generated method stub
		/********************简单示例初始化方法，待完善********************/
		blockMatrix = new BlockPO[width][height];
		setBlock(mineNum);
		
		this.parameterModel.setMineNum(mineNum);
		/***********请在删除上述内容的情况下，完成自己的内容****************/
		
		this.printBlockMatrix();
		
		return false;
	}

	@Override
	public boolean excavate(int x, int y) {
		// TODO Auto-generated method stub
		/********************简单示例挖开方法，待完善********************/
		if(blockMatrix == null)
			return false;
		
		List<BlockPO> blocks = new ArrayList<BlockPO>();
		BlockPO block = blockMatrix[x][y];
		GameState gameState = GameState.RUN;
		if(block.getState()==BlockState.UNCLICK){
		blocks.add(block);
		block.setState(BlockState.CLICK);
		if(block.isMine()){
			for(int i=0;i<blockMatrix.length;i++){
				for(int j=0;j<blockMatrix[0].length;j++){
				if(blockMatrix[i][j].isMine())
					blocks.add(blockMatrix[i][j]);
				}
				}
			gameState = GameState.OVER;
			this.gameModel.gameOver(GameResultState.FAIL);
		 }
		int i,j;
		
		if(block.getMineNum()==0){
		for(i=x-1;i<=x+1;i++){
			for(j=y-1;j<=y+1;j++){
				if(blockMatrix == null)
					return false;	
				if(i>=0&&j>=0&&i<blockMatrix.length&&j<blockMatrix[0].length){						
						excavate(i, j);
				 }				
				}}}
		
		int UnClickNum=0;
		for(i=0;i<blockMatrix.length;i++){
			for(j=0;j<blockMatrix[0].length;j++){
			if(blockMatrix[i][j].getState()==BlockState.UNCLICK)
				UnClickNum++;
			}
		}
	       if(UnClickNum==0){
	    	   gameState = GameState.OVER;
				this.gameModel.gameOver(GameResultState.SUCCESS);
	       }
		}	   
		super.updateChange(new UpdateMessage("excute",this.getDisplayList(blocks, gameState)));			
		/***********请在删除上述内容的情况下，完成自己的内容****************/
		return true;
	
	}
	 
	@Override
	public boolean mark(int x, int y) {
		// TODO Auto-generated method stub
		/********************简单示例标记方法，待完善********************/
		GameState gameState = GameState.RUN;
		if(blockMatrix == null)
			return false;
		
		List<BlockPO> blocks = new ArrayList<BlockPO>();
		BlockPO block = blockMatrix[x][y];
		 
		BlockState state = block.getState();
		if(state == BlockState.UNCLICK){
			block.setState(BlockState.FLAG);
			this.parameterModel.minusMineNum();
		}
		else if(state == BlockState.FLAG){
			block.setState(BlockState.UNCLICK);
			this.parameterModel.addMineNum();
		}
		
		blocks.add(block);
		int i,j;
		int UnClickNum=0;
		for(i=0;i<blockMatrix.length;i++){
			for(j=0;j<blockMatrix[0].length;j++){
			if(blockMatrix[i][j].getState()==BlockState.UNCLICK)
				UnClickNum++;
			}
		}
	       if(UnClickNum==0){
	    	   gameState = GameState.OVER;
				this.gameModel.gameOver(GameResultState.SUCCESS);
	       }
	    	   
		
		super.updateChange(new UpdateMessage("excute",this.getDisplayList(blocks, GameState.RUN)));
		/***********上述内容写得很好****************/
		
		return true;
	}

	@Override
	public boolean quickExcavate(int x, int y) {
		// TODO Auto-generated method stub
		/***********请在此处完成快速挖开方法实现****************/
		List<BlockPO> blocks = new ArrayList<BlockPO>();
		int i,j,count=0;
		GameState gameState=GameState.RUN;
		
		for(i=x-1;i<=x+1;i++){
			for(j=y-1;j<=y+1;j++){
				if(blockMatrix == null)
					return false;	
				
				if(i>=0&&j>=0&&i<blockMatrix.length&&j<blockMatrix[0].length){				
				BlockPO block = blockMatrix[i][j];
				BlockState state = block.getState();
				if(state==BlockState.FLAG)
					count++;
				}}}
			
			if(count==blockMatrix[x][y].getMineNum()){
		for(i=x-1;i<=x+1;i++){
			for(j=y-1;j<=y+1;j++){
				if(blockMatrix == null)
					return false;	
				
				if(i>=0&&j>=0&&i<blockMatrix.length&&j<blockMatrix[0].length){				
				BlockPO block = blockMatrix[i][j];
				BlockState state = block.getState();
				if(state == BlockState.UNCLICK)
				block.setState(BlockState.CLICK);
				blocks.add(block);
				
				if(block.isMine()&&block.getState()==BlockState.CLICK){
					gameState = GameState.OVER;
					this.gameModel.gameOver(GameResultState.FAIL);
				}
				}
			}
		}
			}
				super.updateChange(new UpdateMessage("excute",this.getDisplayList(blocks, gameState)));	
		
		
		return false;
	}

	/**
	 * 示例方法，可选择是否保留该形式
	 * 
	 * 初始化BlockMatrix中的Block，并随机设置mineNum颗雷
	 * 同时可以为每个Block设定附近的雷数
	 * @param mineNum
	 * @return
	 */
	private boolean setBlock(int mineNum){
		int width = blockMatrix.length;
		int height = blockMatrix[0].length;
	   
	   for(int i=0;i<width;i++){
		   for(int j=0;j<height;j++){
			   blockMatrix[i][j] = new BlockPO(i,j);
		   }
		  }
			 //初始化及布雷 
			 //放置雷，并设定block附近雷数，现有放置方法为固定方法，请添加随机实现	
			while(mineNum>0)	{	
				int i=(int) (Math.random()*width);
				int j=(int)	(Math.random()*height);
				
			if(!blockMatrix[i][j].isMine()){
				blockMatrix[i][j].setMine(true);			
				addMineNum(i,j);
				mineNum--;
			}
			}		
				
		return false;
	}
	
	
	/**
	 * 示例方法，可选择是否保留该形式
	 * 
	 * 将(i,j)位置附近的Block雷数加1
	 * @param i
	 * @param j
	 */
	private void addMineNum(int i, int j){
		int width = blockMatrix.length;
		int height = blockMatrix[0].length;
		
		int tempI = i-1;		
		
		for(;tempI<=i+1;tempI++){
			int tempJ = j-1;
			for(;tempJ<=j+1;tempJ++){
				if((tempI>-1&&tempI<width)&&(tempJ>-1&&tempJ<height)){
//					System.out.println(i+";"+j+":"+tempI+";"+tempJ+":");
					blockMatrix[tempI][tempJ].addMine();
				}
			}
		}
		 
	}
	
	/**
	 * 将逻辑对象转化为显示对象
	 * @param blocks
	 * @param gameState
	 * @return
	 */
	private List<BlockVO> getDisplayList(List<BlockPO> blocks, GameState gameState){
		List<BlockVO> result = new ArrayList<BlockVO>();
		for(BlockPO block : blocks){
			if(block != null){
				BlockVO displayBlock = block.getDisplayBlock(gameState);
				if(displayBlock.getState() != null)
				result.add(displayBlock);
			}
		}
		return result;
	}

	@Override
	public void setGameModel(GameModelService gameModel) {
		// TODO Auto-generated method stub
		this.gameModel = gameModel;
	}
	
	private void printBlockMatrix(){
		for(BlockPO[] blocks : this.blockMatrix){
			for(BlockPO b :blocks){
				String p = b.getMineNum()+"";
				if(b.isMine())
					p="*";
				System.out.print(p);
			}
			System.out.println();
		}
	}
}
