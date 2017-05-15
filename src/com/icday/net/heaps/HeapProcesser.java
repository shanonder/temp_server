package com.icday.net.heaps;

import com.icday.database.net.responses.HeapResponse;
import com.icday.net.message.ClientRequest;
import com.icday.net.message.MsgProcessor;
import com.icday.net.sessions.GameSession;

public class HeapProcesser extends MsgProcessor{

	@Override
	public void process(GameSession gameSession, ClientRequest request) throws Exception {
//		HEAPRequest hr = new HEAPRequest(request);
		gameSession.sendMsg(new HeapResponse(200, System.currentTimeMillis()));
	}

}
