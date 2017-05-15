package com.icday.enums;

import java.util.HashMap;

import com.icday.entities.EquipEntity;
import com.icday.entities.ItemEntity;

public enum ItemEnum {
	ITEM_COMMON(0,ItemEntity.class),
	ITEM_EQUIP(1,EquipEntity.class)
	;
	
	private static HashMap<Integer, ItemEnum> enumMap;
	static {
		enumMap = new HashMap<>();
        for(ItemEnum ele : values()) {
        	enumMap.put(ele.value, ele);
        }
    }
	
	public static ItemEnum getEnum(int type){
		ItemEnum en = enumMap.get(type);
		if(en == null){
			en = ITEM_COMMON;
		}
		return en;
	}
	
	private int value;
	private Class<?> voClazz;
	private ItemEnum(int value,Class<?> voClazz) {
		this.setValue(value);
		this.setVoClazz(voClazz);
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	private void setValue(int value) {
		this.value = value;
	}
	/**
	 * @return the voClazz
	 */
	public Class<?> getVoClazz() {
		return voClazz;
	}
	/**
	 * @param voClazz the voClazz to set
	 */
	public void setVoClazz(Class<?> voClazz) {
		this.voClazz = voClazz;
	}
}
