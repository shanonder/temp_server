package com.icday.manages.utils;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import com.opencsv.CSVReader;

public class TabelReader {

	public TabelReader(){

	}

	public HashMap<Object, Object> dataMap;

	public TabelReader load(Class<?> clazz,String url) throws Exception{
		dataMap = new HashMap<>();
		File file = new File(url); 
		FileReader fReader = new FileReader(file);  
		CSVReader csvReader = new CSVReader(fReader);  
		String[] keys = csvReader.readNext(); 
		String[] types = csvReader.readNext();

		List<String[]> list = csvReader.readAll();  
		for(String[] ss : list){ 
			Object data = clazz.newInstance();
			if(ss[0].replaceAll(" ", "").equals("")){
				continue;
			}
			
			int i = 0;
			for(String key : keys){
				if(key.equals("")){
					continue;
				}
				String type = types[i];
				String v = ss[i];
				@SuppressWarnings("unused")
				Field field = clazz.getField(key);
				Object val = null;
				if(type.equals("String")){
					val = v;
				}
				if(type.equals("int")){
					try{
						val = Integer.valueOf(v);
						setFieldValue(data, key, Integer.valueOf(v));
					}
					catch(Exception e){
						val = 0;
					}
				}
				setFieldValue(data, key, val);
				if(i == 0){
					dataMap.put(val, data);
				}
				i++;
			}
			
		}  
		csvReader.close();  
		return this;
	}
	
	 private Object setFieldValue(Object dObject, String fieldName, Object val) {
	        Object result = null;
	        try {
	          Field fu = dObject.getClass().getDeclaredField(fieldName); // 获取对象的属性域
	            try {
	                fu.setAccessible(true); // 设置对象属性域的访问属性
	                fu.set(dObject,val); // 设置对象属性域的属性值
	                result = fu.get(dObject); // 获取对象属性域的属性值
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        } catch (NoSuchFieldException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }

	public Object getData(String key) {
		// TODO Auto-generated method stub
		return dataMap.get(key);
	}
}
