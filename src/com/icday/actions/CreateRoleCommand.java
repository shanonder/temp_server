package com.icday.actions;

import java.util.HashMap;

import com.icday.database.automatic.entitys.TbRole;
import com.icday.entities.ItemEntity;
import com.icday.entities.PackEntity;
import com.icday.manages.utils.ProfileUtil;
import com.icday.models.ItemModel;

public class CreateRoleCommand {

	public void execute() throws Exception{
		String id = ProfileUtil.getAtomicCounter();
		TbRole role = new TbRole();
		role.setInsid(id);
		role.setName("测试玩家5");
		PackEntity bag = new PackEntity();
		ItemEntity ivo = ItemModel.getInstance().initItem("3");
		bag.openLength = 50;
		bag.itemList = new HashMap<>();
		bag.itemList.put(2, ivo);
		bag.owner = role.getInsid();
	}
}
