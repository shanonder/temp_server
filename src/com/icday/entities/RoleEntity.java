package com.icday.entities;

import java.util.ArrayList;
import java.util.Date;

import com.icday.database.automatic.entitys.TbRole;
import com.icday.database.csvs.DtRole;
import com.icday.database.net.datas.AttributesData;
import com.icday.database.net.datas.RoleData;
import com.icday.models.RoleModel;

public class RoleEntity {
	private String insid;
	public DtRole cfg;
	public String name;
	public int mapid;
	public double x;
	public double y;
	public long createTime;
	
	public ArrayList<AttributesData> attributes;
	public int level;
	public int exp;
	
	public RoleEntity(){
		attributes = new ArrayList<>();
	}
	
	public static TbRole toTb(RoleEntity vo) {
		TbRole tb = new TbRole();
		tb.setInsid(vo.getInsid());
		tb.setCfgid(vo.cfg.id);
		tb.setMap(vo.mapid);
		tb.setPosx(vo.x);
		tb.setPosy(vo.y);
		tb.setName(vo.name);
		tb.setCreatetime(new Date(vo.createTime));
		tb.setLevel(vo.level);
		tb.setExp(vo.exp);
		return tb;
	}
	
	public static RoleData toMsg(RoleEntity vo){
		RoleData role = new RoleData();
		role.setName(vo.name);
		role.setAttributes(vo.attributes);
		role.setCfgId(vo.cfg.id);
		role.setExp(vo.exp);
		role.setInsId(vo.insid);
		role.setLevel(vo.level);
		return role;
	}

	public static RoleEntity fromDb(TbRole tbRole) throws Exception {
		RoleEntity role = new RoleEntity();
		role.setInsid(tbRole.getInsid());
		role.cfg = (DtRole)RoleModel.getInstance().getCfgById(tbRole.getCfgid());
		role.mapid = tbRole.getMap();
		role.x = tbRole.getPosx();
		role.y = tbRole.getPosy();
		role.level = tbRole.getLevel();
		role.exp = tbRole.getExp();
		role.name = tbRole.getName();
		role.createTime = tbRole.getCreatetime().getTime();
		return role;
	}

	/**
	 * @return the insid
	 */
	public String getInsid() {
		return insid;
	}


	/** 
	 * init all
	 * @param insid the insid to set
	 * @throws Exception 
	 */
	public void setInsid(String insid) throws Exception {
		this.insid = insid;
	}
}
