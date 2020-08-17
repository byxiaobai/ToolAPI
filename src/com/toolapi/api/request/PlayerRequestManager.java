package com.toolapi.api.request;

import java.util.HashMap;

import org.bukkit.entity.Player;

/** 
* @author byxiaobai
* 类说明 
*/
public abstract class PlayerRequestManager {
	/**
	 * 玩家请求Map
	 * 第一个Player:接收者
	 * 第二个Player:发送者
	 * 第一个PlayerRequest:发送者的请求
	 */
	private HashMap<Player,HashMap<Player,PlayerRequest>> playerRequestMap=new HashMap<>();
	public PlayerRequestManager() {
		
	}
	public void sendPlayerRequest(PlayerRequest playerRequest) {
		Player receiver=playerRequest.getReceiver();
		HashMap<Player,PlayerRequest> hashMap=playerRequestMap.get(receiver);
		if(hashMap!=null) {
			hashMap.put(playerRequest.getSender(), playerRequest);
		}else {
			hashMap=new HashMap<>();
		}
		playerRequestMap.put(receiver, hashMap);
		playerRequest.getReceiver().sendMessage(playerRequest.getRequestContent());
	}
	public PlayerRequest getPlayerRequest(Player sender,Player receiver) {
		HashMap<Player,PlayerRequest> hashMap=playerRequestMap.get(receiver);
		if(hashMap==null)return null;
		return hashMap.get(sender);
	}
	
}