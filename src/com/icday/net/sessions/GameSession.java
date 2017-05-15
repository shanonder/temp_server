package com.icday.net.sessions;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.AttributeKey;
import org.apache.mina.core.session.IoSession;

import com.icday.net.interfaces.ResponseMsg;
/**
 * 游戏中的session回话，封装了mina的session
 *
 */
public class GameSession {

	private IoSession session;
	private String address;
	
	private Object user;
	
	private Object role;
	
	private Object pack;
	
	private boolean isLogin=false;
	
	private static final AttributeKey KEY_PLAYER_SESSION = new AttributeKey(GameSession.class, "user.session");
	
	public GameSession(IoSession session){
		this.session = session;
		this.session.setAttribute(KEY_PLAYER_SESSION, this);
		SocketAddress socketaddress = session.getRemoteAddress();
		InetSocketAddress s = (InetSocketAddress) socketaddress;
		address = s.getAddress().getHostAddress();
	}
	
	public static GameSession getInstance(IoSession session) {
		Object playerObj = session.getAttribute(KEY_PLAYER_SESSION);
		return (GameSession) playerObj;
	}
	
	/**
	 * 发送消息给客户端
	 * @param msg
	 * @return
	 */
	public WriteFuture sendMsg(ResponseMsg msg) {
		if (session == null || !session.isConnected() || session.isClosing()) {
			return null;
		}
		return session.write(msg);
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public  void setLogin(boolean isLogin){
		this.isLogin=isLogin;
	}
	public boolean isLogin(){
		return this.isLogin;
	}
	
	public void setUser(Object obj){
		this.user = obj;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getUser(Class<T> t){
		return (T)this.user;
	}
	
	public void setRole(Object obj){
		this.role = obj;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getRole(Class<T> t){
		return (T)this.role;
	}
	public void close(){
		session.closeOnFlush();
	}

	/**
	 * @return the pack
	 */
	@SuppressWarnings("unchecked")
	public <T> T getPack(Class<T> t) {
		return (T)this.pack;
	}

	/**
	 * @param pack the pack to set
	 */
	public void setPack(Object value) {
		this.pack = value;
	}
	
}
