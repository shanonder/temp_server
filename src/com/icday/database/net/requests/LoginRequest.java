package com.icday.database.net.requests;

import com.icday.net.message.ClientRequest;
/**
 * 此类由protocol_generate_util自动生成
 */
public class LoginRequest{
	public LoginRequest(ClientRequest request) throws Exception{
		username = request.getString();
		password = request.getString();

	}

	private String username;//用户名
	private String password;//密码
	public String getUsername(){
		return this.username;
	}
	public void setUsername(String username){
		this.username=username;
	}
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String password){
		this.password=password;
	}

}