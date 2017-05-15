package com.icday.database;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.icday.database.automatic.entitys.TbConfig;
import com.icday.database.automatic.entitys.TbConfigExample;
import com.icday.database.automatic.mappers.TbConfigMapper;

public class SqlManager {
	private static String VERSION = "3";
	private SqlSessionFactory sqlSessionFactory;
	
	private static SqlManager _instance = null;
	
	public static SqlManager getInstance(){
		if(_instance == null){
			_instance = new SqlManager();
		}
		return _instance;
	}
	public void init() throws Exception{
		Properties props = new Properties();//属性集合对象    
		FileInputStream fis = new FileInputStream("properties/init.properties");//属性文件流    
		props.load(fis);
		fis.close();
		String resource = "com/icday/datas/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		setSqlSessionFactory(new SqlSessionFactoryBuilder().build(inputStream,props));
		SqlSession session = getSqlSessionFactory().openSession();
		TbConfigMapper tbCfgMap = session.getMapper(TbConfigMapper.class);
		TbConfigExample example = new TbConfigExample();
		example.createCriteria();
		List<TbConfig> list = tbCfgMap.selectByExample(example);

		String version = null;
		for(TbConfig ele : list){
			if(ele.getName().equals("version")){
				version = ele.getValue();
				System.out.println(version);
			}
		}
		
		if(version == null ||version.equals(VERSION) == false){
			initTables();
			TbConfig verCfg = new TbConfig();
			verCfg.setName("version");
			verCfg.setValue(VERSION);
			tbCfgMap.insert(verCfg);
			session.commit();
		}
		session.close();
	}
	private void initTables() {
//		todo
	}
	/**
	 * @return the sqlSessionFactory
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	public SqlSession openSession(){
		return getSqlSessionFactory().openSession();
	}
	/**
	 * @param sqlSessionFactory the sqlSessionFactory to set
	 */
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
}
