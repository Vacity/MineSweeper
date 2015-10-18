package edu.nju.model.impl;

import java.io.Serializable;

import edu.nju.model.state.GameResultState;

public class UpdateMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private Object value;
	private GameResultState gameResult;
	public UpdateMessage(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public UpdateMessage(GameResultState gameResult,String key, Object value) {
		super();
		this.key = key;
		this.value = value;
		this.gameResult = gameResult;
	}
	public GameResultState getGameResult() {
		return gameResult ;
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}
