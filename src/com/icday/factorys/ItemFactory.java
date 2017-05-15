package com.icday.factorys;

import com.icday.database.automatic.entitys.TbItem;
import com.icday.entities.ItemEntity;
import com.icday.models.ItemModel;

public class ItemFactory {
	
	public static ItemEntity fromTb(TbItem tb){
		ItemEntity item = new ItemEntity();
		item.insid = tb.getInsid();
		item.createTime = tb.getCreatetime();
		item.cfg = ItemModel.getInstance().getCfg(tb.getCfgid());
		item.params = tb.getParams();
		return item;
	}
}
