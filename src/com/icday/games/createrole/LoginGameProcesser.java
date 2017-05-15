package com.icday.games.createrole;

import com.icday.database.automatic.entitys.TbUser;
import com.icday.database.net.requests.LoginGameRequest;
import com.icday.database.net.responses.LoginGameResponse;
import com.icday.entities.PackProxy;
import com.icday.entities.RoleEntity;
import com.icday.games.scenes.controllers.EnterGameCommand;
import com.icday.models.RoleModel;
import com.icday.models.UserModel;
import com.icday.net.message.ClientRequest;
import com.icday.net.message.INotAuthProcessor;
import com.icday.net.message.MsgProcessor;
import com.icday.net.sessions.GameSession;

public class LoginGameProcesser extends MsgProcessor implements INotAuthProcessor{

	@Override
	public void process(GameSession gameSession, ClientRequest request) throws Exception {
		// TODO Auto-generated method stub
		LoginGameRequest lgreq = new LoginGameRequest(request);
		TbUser user = UserModel.getInstance().getUserById(lgreq.getPassKey());
		if(user != null){
			gameSession.setLogin(true);
			gameSession.setUser(user);
			String roleList = user.getRolelist();
			
			if(roleList!= null){
				RoleEntity role = RoleModel.getInstance().getEntityByInsId(roleList.split(";")[0]);
				gameSession.setRole(role);
				PackProxy pack = new PackProxy();
				pack.setUserId(role.getInsid());
				gameSession.setPack(pack);
				gameSession.sendMsg(new LoginGameResponse(200,1));
				EnterGameCommand.excute(gameSession);
			}
			else{
				gameSession.sendMsg(new LoginGameResponse(200,0));
			}
		}
	}

}
