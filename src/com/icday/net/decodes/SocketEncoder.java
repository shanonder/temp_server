package com.icday.net.decodes;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.icday.net.interfaces.ResponseMsg;

public class SocketEncoder extends ProtocolEncoderAdapter {

	@Override
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		ResponseMsg sp = (ResponseMsg)message;
//		API.log.info(String.valueOf(buf.remaining()));
		out.write(sp.entireMsg());
		sp.release();
		
	}

}
