package com.toolapi.utils;

import org.bukkit.entity.Player;

/** 
* @author byxiaobai
* 类说明 
*/
public class PlayerMessageUtil {
	public static void sendNormalMessage(Player player,String message) {
		player.sendMessage(message);
	}
	public static void sendColorMessage(Player player,String message) {
		player.sendMessage(message.replaceAll("&", "§"));
	}
}
