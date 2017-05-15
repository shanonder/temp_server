package com.icday.net.hanlders;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.icday.net.message.ClientRequest;
import com.icday.net.message.MsgDispatcher;
import com.icday.net.sessions.GameSession;

public class GameProtocolHandler  extends IoHandlerAdapter {
	private final Logger logger = Logger.getLogger(getClass());
	@Override
	public void sessionOpened(IoSession session) {
		GameSession gs = new GameSession(session);
		
	}
	@Override
	public void messageReceived(IoSession session, Object message) {
		ClientRequest clientRequest = (ClientRequest)message;
		GameSession gs = GameSession.getInstance(session);
		if (gs == null) {
			return;
		}
		MsgDispatcher.getInstance().dispatchMsg(gs,clientRequest);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		logger.info("Disconnecting the idle.");
		session.closeNow();
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		// close the connection on exceptional situation
		logger.warn(cause.getMessage(), cause);
		//		session.close(true);
		session.closeNow();
	}
}
