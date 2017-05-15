package com.icday.database.net.requests;

import com.icday.net.message.ClientRequest;
/**
 * 此类由protocol_generate_util自动生成
 */
public class EnterSceneRequest{
	public EnterSceneRequest(ClientRequest request) throws Exception{
		sceneId = request.getInt();

	}

	private int sceneId;//场景ID
	public int getSceneId(){
		return this.sceneId;
	}
	public void setSceneId(int sceneId){
		this.sceneId=sceneId;
	}

}