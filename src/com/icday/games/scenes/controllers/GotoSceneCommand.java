package com.icday.games.scenes.controllers;

import com.icday.database.net.responses.EnterSceneResponse;
import com.icday.entities.RoleEntity;
import com.icday.net.sessions.GameSession;

public class GotoSceneCommand {
	public static void excute(GameSession gameSession) throws Exception{
		RoleEntity role = gameSession.getRole(RoleEntity.class);
		gameSession.sendMsg(new EnterSceneResponse(200, role.mapid, role.x, role.y));
	}
}
