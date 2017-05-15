package com.icday.games.scenes.controllers;

import java.util.ArrayList;

import com.icday.database.net.datas.PackData;
import com.icday.database.net.responses.EnterSceneResponse;
import com.icday.database.net.responses.EnterWorldResponse;
import com.icday.entities.PackProxy;
import com.icday.entities.RoleEntity;
import com.icday.net.sessions.GameSession;

public class EnterGameCommand {
	/** 玩家进入游戏*/
	public static void excute(GameSession gameSession) throws Exception{
		RoleEntity role = gameSession.getRole(RoleEntity.class);
		PackProxy pack = gameSession.getPack(PackProxy.class);
		ArrayList<PackData> packs = pack.toMsg();
		gameSession.sendMsg(new EnterWorldResponse(200, RoleEntity.toMsg(gameSession.getRole(RoleEntity.class)), packs));
		GotoSceneCommand.excute(gameSession);
		
	}
}
