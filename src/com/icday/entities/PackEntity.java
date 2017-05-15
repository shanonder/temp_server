package com.icday.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.icday.database.automatic.entitys.TbPack;
import com.icday.database.net.datas.GridData;
import com.icday.database.net.datas.ItemData;
import com.icday.database.net.datas.PackData;
import com.icday.enums.PackEnum;
import com.icday.models.ItemModel;

public class PackEntity {
	
	public static int maxLength = 200;
	public HashMap<Integer, ItemEntity> itemList;
	
	public int openLength ;
	public String owner;
	public PackEnum type;
	
	public PackEntity() {
		itemList = new HashMap<>();
	}

	
	public static TbPack toTb(PackEntity vo){
		TbPack tb = new TbPack();
		tb.setUserid(vo.owner);
		tb.setType(vo.type.getValue());
		tb.setOpenlength(vo.openLength);
		String content = "";
		for (Map.Entry<Integer, ItemEntity> entry : vo.itemList.entrySet()) {  
			int index = entry.getKey();
			ItemEntity item = entry.getValue();
		    content = content + index + ";" + item.insid + ";";
		}  
		tb.setContent(content);
		return tb;
	}
	
	public PackData toMsg(){
		PackData pack = new PackData();
		pack.setOpenLength(openLength);
		pack.setType(type.getValue());
		ArrayList<GridData> gridList = new ArrayList<>();
		pack.setItemList(gridList);
		for (Map.Entry<Integer, ItemEntity> entry : itemList.entrySet()) {  
			int index = entry.getKey();
			ItemEntity itemEntity = entry.getValue();
			GridData grid = new GridData();
			ItemData item = itemEntity.toMsg();
			grid.setIndex(index);
			grid.setItem(item);
			gridList.add(grid);
		}  
		return pack;
	}
	
	public static PackEntity fromTb(TbPack vo){
		PackEntity entity = new PackEntity();
		entity.openLength = vo.getOpenlength();
		entity.owner = vo.getUserid();
		entity.type = PackEnum.getEnum(vo.getType());
		String[] keys = vo.getContent().split(";");
		
		for(int i = 0 ; i < keys.length -1;){
			int index = Integer.parseInt(keys[i++]);
			String insid = keys[i++];
			ItemEntity item = ItemModel.getInstance().getItemByInsId(insid);
			entity.itemList.put(index, item);
		}
		return entity;
	}
}
