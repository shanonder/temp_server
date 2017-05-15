package com.icday.models;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.icday.database.SqlManager;
import com.icday.database.automatic.entitys.TbUser;
import com.icday.database.automatic.entitys.TbUserExample;
import com.icday.database.automatic.mappers.TbUserMapper;
import com.icday.entities.RoleEntity;
import com.icday.manages.utils.ProfileUtil;

public class UserModel {
	private static UserModel _instance;
	public static UserModel getInstance() throws Exception {
		// TODO Auto-generated method stub
		if(_instance == null){
			_instance = new UserModel();
		}
		return _instance;
	}
	
	public TbUser login(String userName,String password) throws Exception{
		TbUser tb = null;
		TbUserExample exam = new TbUserExample();
		SqlSession session = SqlManager.getInstance().openSession();
		TbUserMapper userMap = session.getMapper(TbUserMapper.class);
		exam.createCriteria().andUsernameEqualTo(userName);
		List<TbUser> list = userMap.selectByExampleWithBLOBs(exam);
		if(list.size() > 0){
			tb = list.get(0);
			if(tb.getPassword().equals(password)){
				return tb;
			}
			return null;
		}else{
			TbUser user = new TbUser();
			user.setInsid(ProfileUtil.getAtomicCounter());
			user.setUsername(userName);
			user.setPassword(password);
			userMap.insert(user);
			session.commit();
			session.close();
			return user;
		}
	}

	public RoleEntity createRole(TbUser user, String roleName, String cfgId) throws Exception {
		RoleModel roleModel = RoleModel.getInstance();
		if(roleModel.contains(roleName)){
			return null;
		}
		String roleId = ProfileUtil.getAtomicCounter();
		RoleEntity role = RoleModel.getInstance().init(roleId, cfgId, roleName);
		SqlSession session = SqlManager.getInstance().openSession();
		TbUserMapper userMap = session.getMapper(TbUserMapper.class);
		TbUserExample example = new TbUserExample();
		example.createCriteria().andInsidEqualTo(user.getInsid());
		String str = user.getRolelist();
		if(str == null){
			str = "";
		}
		user.setRolelist(str + role.getInsid()+";");
		userMap.updateByExampleSelective(user, example);
		session.commit();
		session.close();
		return role;
	}

	public TbUser getUserById(String insid) throws Exception {
		SqlSession session = SqlManager.getInstance().openSession();
		TbUserMapper userMap = session.getMapper(TbUserMapper.class);
		TbUser user = userMap.selectByPrimaryKey(insid);
		return user;
	}
	
}
