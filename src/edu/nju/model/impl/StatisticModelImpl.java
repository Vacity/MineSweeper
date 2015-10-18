package edu.nju.model.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import edu.nju.model.data.StatisticData;
import edu.nju.model.po.StatisticPO;
import edu.nju.model.service.StatisticModelService;
import edu.nju.model.state.GameResultState;

public class StatisticModelImpl extends BaseModel implements StatisticModelService{
	
	private StatisticData statisticDao;
	StatisticPO statistic = new StatisticPO();
	File statisticFile = new File("record.txt");
	String[] line = new String[5];
	String[] split1;
	String split2[];
	String split3[];
	String split4[];
	public StatisticModelImpl(){
		//初始化Dao
		statisticDao = new StatisticData();	
	}	

		
				
	@Override
	public void recordStatistic(String level,GameResultState result, int time) {
		try{
			int i=0;
			BufferedReader br = new BufferedReader(new FileReader(statisticFile));
	        while((line[i]=br.readLine())!=null){
	        	i++;
	        }
	        br.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		 split1 = line[0].split(" ");
		 split2 = line[1].split(" ");
		 split3 = line[2].split(" ");
		 split4 = line[3].split(" ");	
		if(level.equals("小")){
		    if(result.equals(GameResultState.SUCCESS)){
		    	split1[1] =Integer.toString(Integer.parseInt(split1[1])+1);
		    	split1[3] =Integer.toString(Integer.parseInt(split1[3])+1);
		    }
		    else{
		    	split1[2] =Integer.toString(Integer.parseInt(split1[2])+1);
		        split1[3] =Integer.toString(Integer.parseInt(split1[3])+1);
		    }
		    split1[4] =Double.toString((Double)(Math.round(Double.parseDouble(split1[1])/Double.parseDouble(split1[3])*100)/100.0));
		}
		 if(level.equals("中")){
			    if(result.equals(GameResultState.SUCCESS)){
			    	split2[1] =Integer.toString(Integer.parseInt(split2[1])+1);
			    	split2[3] =Integer.toString(Integer.parseInt(split2[3])+1);
			    }
			    else{
			    	split2[2] =Integer.toString(Integer.parseInt(split2[2])+1);
			        split2[3] =Integer.toString(Integer.parseInt(split2[3])+1);
			    }
			    split2[4] =Double.toString((Double)(Math.round(Double.parseDouble(split2[1])/Double.parseDouble(split2[3])*100)/100.0));
		    }
		 if(level.equals("大")){
			    if(result.equals(GameResultState.SUCCESS)){
			    	split3[1] =Integer.toString(Integer.parseInt(split3[1])+1);
			    	split3[3] =Integer.toString(Integer.parseInt(split3[3])+1);
			    }
			    else{
			    	split3[2] =Integer.toString(Integer.parseInt(split3[2])+1);
			        split3[3] =Integer.toString(Integer.parseInt(split3[3])+1);
			    }
			    split3[4] =Double.toString((Double)(Math.round(Double.parseDouble(split3[1])/Double.parseDouble(split3[3])*100)/100.0));
		 }
			    if(level.equals("自定义")){
				    if(result.equals(GameResultState.SUCCESS)){
				    	split4[1] =Integer.toString(Integer.parseInt(split4[1])+1);
				    	split4[3] =Integer.toString(Integer.parseInt(split4[3])+1);
				    }
				    else{
				    	split4[2] =Integer.toString(Integer.parseInt(split4[2])+1);
				        split4[3] =Integer.toString(Integer.parseInt(split4[3])+1);
				    }
				    split4[4] =Double.toString((Double)(Math.round(Double.parseDouble(split4[1])/Double.parseDouble(split4[3])*100)/100.0));
		         }
		  	    	try{			
						FileWriter writer = new FileWriter(statisticFile,false);
						for(int i=0;i<5;i++){
						writer.write(split1[i]+" ");
						}
						writer.write("\n");
						for(int i=0;i<5;i++){
						writer.write(split2[i]+" ");
						}
						writer.write("\n");
						for(int i=0;i<5;i++){
						writer.write(split3[i]+" ");
						}
						writer.write("\n");
						for(int i=0;i<5;i++){
						writer.write(split4[i]+" ");
						}
						writer.close();
		      }catch(Exception ex){
			  ex.printStackTrace();
		     }
		}
		// TODO Auto-generated method stub
	 	

	@Override
	public void showStatistics() {
		// TODO Auto-generated method stub
		
	}

}
