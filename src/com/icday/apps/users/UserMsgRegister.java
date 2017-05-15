package com.icday.apps.users;

import com.icday.database.net.consts.ProtocolConst;
import com.icday.net.message.MsgProcessor;

/**
 * 消息处理器注册类，所有的消息处理器，都在此注册实例化
 *
 */
public enum UserMsgRegister {
	/**用户登录*/
	login(ProtocolConst.LoginGate,new LoginProcesser())
	;
	
	
	private int msgCode;
	private MsgProcessor processor;
	private UserMsgRegister(int msgCode,MsgProcessor processor){
		this.msgCode = msgCode;
		this.processor = processor;
	}
	
	public int getMsgCode(){
		return this.msgCode;
	}
	
	public MsgProcessor getMsgProcessor(){
		return this.processor;
	}
}
