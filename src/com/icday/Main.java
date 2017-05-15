package com.icday;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icday.database.SqlManager;
import com.icday.net.NetManager;
import com.icday.services.GameService;
import com.icday.services.LoginService;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception{
		
		SqlManager.getInstance().init();
		logger.info("sql start complete...");
		NetManager.getInstance().init();
		logger.info("sql start complete...");
		

		
		LoginService loginService = new LoginService(3004);
		GameService gameService = new GameService(3005);
		loginService.start();
		gameService.start();
		
	}
}
