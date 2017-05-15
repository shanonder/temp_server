package com.icday.enums;

import java.util.HashMap;

public enum PackEnum {
	PACK_BAG(0),
	PACK_DRESS(1),
	PACK_STORE(2)
	;
	
	
	private static HashMap<Integer, PackEnum> enumMap;
	static {
		enumMap = new HashMap<>();
        for(PackEnum ele : values()) {
        	enumMap.put(ele.value, ele);
        }
    }
	
	public static PackEnum getEnum(int type){
		PackEnum en = enumMap.get(type);
		if(en == null){
			en = PACK_BAG;
		}
		return en;
	}
	
	
	private PackEnum(int value) {
		this.setValue(value);
	}
	
	private int value;
	
	private void setValue(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
