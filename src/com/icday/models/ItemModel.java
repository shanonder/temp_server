package com.icday.models;

import java.sql.Timestamp;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.icday.database.SqlManager;
import com.icday.database.automatic.entitys.TbItem;
import com.icday.database.automatic.mappers.TbItemMapper;
import com.icday.database.csvs.DtItem;
import com.icday.entities.ItemEntity;
import com.icday.enums.ItemEnum;
import com.icday.factorys.ItemFactory;
import com.icday.manages.utils.ProfileUtil;
import com.icday.manages.utils.TabelReader;

public class ItemModel{

	private static ItemModel _instance;
	public static ItemModel getInstance(){
		// TODO Auto-generated method stub
		if(_instance == null){
			_instance = new ItemModel();
		}
		return _instance;
	}
	private TabelReader tableReader;
	private HashMap<String, ItemEntity> itemMap;

	public ItemModel(){
		itemMap = new HashMap<>();
		try {
			tableReader = new TabelReader().load(DtItem.class,"assets/csvs/item.dat");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ItemEntity initItem(String cfgId) throws Exception{
		DtItem dt = (DtItem)tableReader.getData(cfgId);
		Class<?> clazz = ItemEnum.getEnum(dt.type).getVoClazz();
		ItemEntity itemVO =  (ItemEntity)clazz.newInstance();
		itemVO.cfg = dt;
		itemVO.insid = ProfileUtil.getAtomicCounter();
		itemVO.createTime = new Timestamp(System.currentTimeMillis());
		createSql(itemVO);
		return itemVO;
	}

	public ItemEntity updateSql(ItemEntity itemVO) throws Exception{
		SqlManager sql = SqlManager.getInstance();
		SqlSession session = sql.getSqlSessionFactory().openSession();
		TbItemMapper itemmap = session.getMapper(TbItemMapper.class);
		itemmap.updateByPrimaryKey(ItemEntity.toTb(itemVO));
		session.commit();
		session.close();
		return itemVO;
	}

	public int deleteSql(ItemEntity itemVO) throws Exception{
		int re = 0;
		SqlManager sql = SqlManager.getInstance();
		SqlSession session = sql.getSqlSessionFactory().openSession();
		TbItemMapper itemmap = session.getMapper(TbItemMapper.class);
		re = itemmap.deleteByPrimaryKey(itemVO.insid);
		session.commit();
		session.close();
		return re;
	}

	private void createSql(ItemEntity itemVO) throws Exception {
		SqlManager sql = SqlManager.getInstance();
		SqlSession session = sql.getSqlSessionFactory().openSession();
		TbItemMapper itemmap = session.getMapper(TbItemMapper.class);
		itemmap.insert(ItemEntity.toTb(itemVO));
		session.commit();
		session.close();
	}

	public ItemEntity getItemByInsId(String insid){
		if(itemMap.get(insid) != null){
			return itemMap.get(insid);
		}else{
			SqlManager sql = SqlManager.getInstance();
			SqlSession session = sql.getSqlSessionFactory().openSession();
			TbItemMapper itemmap = session.getMapper(TbItemMapper.class);
			TbItem tb = itemmap.selectByPrimaryKey(insid);
			session.close();
			ItemEntity item = ItemFactory.fromTb(tb);
			itemMap.put(insid, item);
			return item;
		}
	}

	public DtItem getCfg(String cfgid) {
		return (DtItem) tableReader.getData(cfgid);
	}

}
