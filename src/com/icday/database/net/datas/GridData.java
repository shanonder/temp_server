package com.icday.database.net.datas;

import java.io.Serializable;
import com.icday.database.net.datas.ItemData;
	/**
	 * 此类由protocol_generate_util自动生成
	 * md5:bdb4980f0cdcd230aa8ff60bbeab28c0
	 */
public class GridData implements Serializable{
	private static final long serialVersionUID = 1L;
	private int index;
	private ItemData item;
	public int getIndex(){
		return this.index;
	}
	public void setIndex(int index){
		this.index=index;
	}
	public ItemData getItem(){
		return this.item;
	}
	public void setItem(ItemData item){
		this.item=item;
	}

}