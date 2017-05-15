package com.icday.models;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.icday.database.SqlManager;
import com.icday.database.automatic.entitys.TbRole;
import com.icday.database.automatic.entitys.TbRoleExample;
import com.icday.database.automatic.mappers.TbRoleMapper;
import com.icday.database.csvs.DtRole;
import com.icday.entities.PackProxy;
import com.icday.entities.RoleEntity;
import com.icday.manages.utils.TabelReader;

public class RoleModel {
	
	private static RoleModel _instance;
	public static RoleModel getInstance() throws Exception {
		// TODO Auto-generated method stub
		if(_instance == null){
			_instance = new RoleModel();
		}
		return _instance;
	}
	
	private HashMap<String, RoleEntity> roleMap;
	private TabelReader tableReader;
	private RoleModel(){
		roleMap = new HashMap<>();
		try {
			tableReader = new TabelReader().load(DtRole.class,"assets/csvs/role.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Object getCfgById(String cfgId){
		return tableReader.getData(cfgId);
	}
	
	public RoleEntity init(String roleId,String cfgId ,String name) throws Exception{
		RoleEntity vo = new RoleEntity();
		vo.setInsid(roleId);
		vo.cfg = (DtRole)tableReader.getData(cfgId);
		vo.name =  name;
		vo.mapid = 1;
		vo.x = 0;
		vo.y = 0;
		vo.createTime = System.currentTimeMillis();
		SqlManager sql = SqlManager.getInstance();
		SqlSession session = sql.getSqlSessionFactory().openSession();
		TbRoleMapper mapper = session.getMapper(TbRoleMapper.class);
		mapper.insert(RoleEntity.toTb(vo));
		session.commit(); 
		session.close();
		roleMap.put(vo.getInsid(), vo);
		return vo;
	}

	public boolean contains(String name) throws Exception {
		TbRoleExample tb = new TbRoleExample();
		tb.createCriteria().andNameEqualTo(name);
		SqlSession session =  SqlManager.getInstance().openSession();
		TbRoleMapper mapper = session.getMapper(TbRoleMapper.class);
		List<TbRole> list = mapper.selectByExample(tb);
		session.close();
		int size = list.size();
		return size > 0;
	}
	
	public RoleEntity getEntityByInsId(String insId) throws Exception{
		RoleEntity role = roleMap.get(insId);
		if(role != null){
			return role;
		}
		TbRoleExample tb = new TbRoleExample();
		tb.createCriteria().andInsidEqualTo(insId);
		SqlSession session =  SqlManager.getInstance().openSession();
		TbRoleMapper mapper = session.getMapper(TbRoleMapper.class);
		TbRole tbrole = mapper.selectByPrimaryKey(insId);
		session.close();
//		if(list.size() > 0){
//			role = RoleEntity.fromDb(list.get(0));
//			roleMap.put(role.getInsid(), role);
//		}
		role = RoleEntity.fromDb(tbrole);
		return role;
	}
}
