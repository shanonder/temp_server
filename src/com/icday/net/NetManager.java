package com.icday.net;

public class NetManager {

	private static NetManager instance;
	public static NetManager getInstance() {
		if(instance == null){
			instance = new NetManager();
		}
		return instance;
	}
	
	public void init(){
		
	}

}
