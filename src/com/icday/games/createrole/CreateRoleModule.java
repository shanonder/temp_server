package com.icday.games.createrole;

import com.icday.database.net.consts.ProtocolConst;
import com.icday.net.message.MsgDispatcher;

public class CreateRoleModule {
	public void startup(){
		
		MsgDispatcher.getInstance().registProcess(ProtocolConst.LoginGame,new LoginGameProcesser());
		MsgDispatcher.getInstance().registProcess(ProtocolConst.CreateRole,new CreateRoleProcesser());
		
	}
}
