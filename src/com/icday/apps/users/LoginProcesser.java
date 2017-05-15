package com.icday.apps.users;

import com.icday.database.automatic.entitys.TbUser;
import com.icday.database.net.requests.LoginRequest;
import com.icday.database.net.responses.LoginGateResponse;
import com.icday.models.UserModel;
import com.icday.net.message.ClientRequest;
import com.icday.net.message.INotAuthProcessor;
import com.icday.net.message.MsgProcessor;
import com.icday.net.sessions.GameSession;
import com.icday.services.GateRoutor;

public class LoginProcesser extends MsgProcessor implements INotAuthProcessor{

	@Override
	public void process(GameSession gameSession, ClientRequest request) throws Exception {
		LoginRequest loginRequest = new LoginRequest(request);
		TbUser user = UserModel.getInstance().login(loginRequest.getUsername(), loginRequest.getPassword());
		if(user != null){
			GateRoutor gateRoutor = GateRoutor.getInstance();
			gameSession.sendMsg(gateRoutor.initInfo(user));
		}else{
			gameSession.sendMsg(new LoginGateResponse(101,null,0,null));
		}
	}

}
