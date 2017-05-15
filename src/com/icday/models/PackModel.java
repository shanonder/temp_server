package com.icday.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;

import com.icday.database.SqlManager;
import com.icday.database.automatic.entitys.TbPack;
import com.icday.database.automatic.entitys.TbPackExample;
import com.icday.database.automatic.mappers.TbPackMapper;
import com.icday.database.csvs.DtPack;
import com.icday.entities.PackEntity;
import com.icday.enums.PackEnum;
import com.icday.manages.utils.TabelReader;

public class PackModel {
	private static PackModel _instance;
	public static PackModel getInstance() throws Exception {
		// TODO Auto-generated method stub
		if(_instance == null){
			_instance = new PackModel();
		}
		return _instance;
	}

	private TabelReader tableReader = null;
	public PackModel() {
		try {
			tableReader = new TabelReader().load(DtPack.class,"assets/csvs/pack.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private PackEntity init(String roleId,PackEnum type ,int length) throws Exception{
		PackEntity bag = new PackEntity();
		bag.owner = roleId;
		bag.type = type;
		bag.openLength = length;
		bag.itemList = new HashMap<>();
		SqlManager sql = SqlManager.getInstance();
		SqlSession session = sql.getSqlSessionFactory().openSession();
		TbPackMapper mapper = session.getMapper(TbPackMapper.class);
		mapper.insert(PackEntity.toTb(bag));
		session.commit();
		session.close();
		return bag;
	}

	public HashMap<PackEnum, PackEntity> getMaps(String roleId) throws Exception{
		//		tableReader.dataMap;
		HashMap<PackEnum, PackEntity> packs = new HashMap<>();
		SqlManager sql = SqlManager.getInstance();
		SqlSession session = sql.getSqlSessionFactory().openSession();
		TbPackMapper mapper = session.getMapper(TbPackMapper.class);
		TbPackExample exam = new TbPackExample();
		exam.createCriteria().andUseridEqualTo(roleId);
		List<TbPack> list = mapper.selectByExampleWithBLOBs(exam);
		if(list.isEmpty()){
			for (Entry<Object, Object> entry : tableReader.dataMap.entrySet()) {
				int type = (int)entry.getKey();
				DtPack dt = (DtPack)entry.getValue();
				PackEnum en = PackEnum.getEnum(type);
				packs.put(en,init(roleId,en,dt.open));
			}  
		}else{
			  for(TbPack tb : list){
				  PackEnum en = PackEnum.getEnum(tb.getType());
				  packs.put(en,PackEntity.fromTb(tb));
			  }
		}
		return packs;
	}

}
