package com.icday.models;

public class ConfigProxy {
	private static ConfigProxy _instance;
	public static ConfigProxy getInstance() {
		// TODO Auto-generated method stub
		if(_instance == null){
			_instance = new ConfigProxy();
		}
		return _instance;
	}
	
	/**
	 * @return the mid
	 */
	public int getMid() {
		return mid;
	}

	/**
	 * @param mid the mid to set
	 */
	public void setMid(int mid) {
		this.mid = mid;
	}

	private int mid = 1;
}
