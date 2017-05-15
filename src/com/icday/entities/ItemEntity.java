package com.icday.entities;

import java.util.Date;

import com.icday.database.automatic.entitys.TbItem;
import com.icday.database.csvs.DtItem;
import com.icday.database.net.datas.ItemData;

public class ItemEntity {
	public String insid;
	public DtItem cfg;
	public Date createTime;
	public String params;
	public static TbItem toTb(ItemEntity ivo) {
		TbItem item = new TbItem();
		item.setCfgid(ivo.cfg.id);
		item.setInsid(ivo.insid);
		item.setCreatetime(ivo.createTime);
		return item;
	}
	
	public ItemData toMsg() {
		ItemData item = new ItemData();
		item.setCfgId(this.cfg.id);
		item.setCreateTime(createTime.getTime());
		item.setInsId(insid);
		item.setType(this.cfg.type);
		return item;
	}
	
}
