package com.icday.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.icday.apps.users.UserMsgRegister;
import com.icday.database.net.datas.GridData;
import com.icday.database.net.datas.ItemData;
import com.icday.database.net.datas.PackData;
import com.icday.enums.PackEnum;
import com.icday.manages.dao.ItemDao;
import com.icday.models.PackModel;
import com.icday.net.message.MsgDispatcher;

public class PackProxy {
	private String userId;

	private HashMap<PackEnum, PackEntity> packMap;
	public PackProxy(){
		this.packMap = new HashMap<>();
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 * @throws Exception 
	 */
	public void setUserId(String userId) throws Exception {
		this.userId = userId;
		packMap = PackModel.getInstance().getMaps(userId);
	}
	
//	private void dbInit() throws Exception{
////		PackModel.getInstance()
//		PackEntity pack = PackModel.getInstance().init(userId, PackEnum.PACK_BAG , 72 );
//		packMap.put(PackEnum.PACK_BAG, pack);
//		pack = PackModel.getInstance().init(userId, PackEnum.PACK_DRESS , 12 );
//		packMap.put(PackEnum.PACK_DRESS, pack);
//		pack = PackModel.getInstance().init(userId, PackEnum.PACK_STORE , 60 );
//		
//	}
	
	
	public boolean move(String insId,PackEnum fromType,int fromIndex,PackEnum toType,int toIndx){
		return true;
	}
	
	public boolean add(String insId,PackEnum type,int index){
		return true;
	}
	
	public boolean remove(String insId,PackEnum type,int index){
		return true;
	}
	
	public ArrayList<PackData> toMsg(){
		ArrayList<PackData> packs = new ArrayList<>();
		for(PackEnum PackE :PackEnum.values()){
			PackEntity packEnti = packMap.get(PackE);
			packs.add(packEnti.toMsg());
		}
		return packs;
	}
}
