package com.icday.games.createrole;

import com.icday.database.automatic.entitys.TbUser;
import com.icday.database.net.requests.CreateRoleRequest;
import com.icday.database.net.responses.CreateRoleResponse;
import com.icday.entities.PackProxy;
import com.icday.entities.RoleEntity;
import com.icday.games.scenes.controllers.EnterGameCommand;
import com.icday.models.RoleModel;
import com.icday.models.UserModel;
import com.icday.net.message.ClientRequest;
import com.icday.net.message.MsgProcessor;
import com.icday.net.sessions.GameSession;

public class CreateRoleProcesser extends MsgProcessor {

	@Override
	public void process(GameSession gameSession, ClientRequest request) throws Exception {
		CreateRoleRequest crr = new CreateRoleRequest(request);
		if(RoleModel.getInstance().contains(crr.getName())){
			gameSession.sendMsg(new CreateRoleResponse(102));
		}
		else{
			RoleEntity role = UserModel.getInstance().createRole(gameSession.getUser(TbUser.class), crr.getName(), crr.getCfgId());
			gameSession.setRole(role);
			PackProxy pack = new PackProxy();
			pack.setUserId(role.getInsid());
			gameSession.setPack(pack);
			gameSession.sendMsg(new CreateRoleResponse(200));
			EnterGameCommand.excute(gameSession);
		}
	}

}
