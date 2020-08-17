package com.toolapi.api.command.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.toolapi.api.command.CommandManager;

/** 
* @author byxiaobai
* 类说明 
*/

public class CommandPreprocessListener implements Listener{
	private CommandManager commandManager;
	@EventHandler
	public void onCommandPreprocess(PlayerCommandPreprocessEvent evt) {
		String[] args=evt.getMessage().replaceAll("/", "").split(" ");
		Player player=evt.getPlayer();
		if(commandManager.doCommand(player,args)) {
			evt.setCancelled(true);
		}
	}
	public void setCommandManager(CommandManager commandManager) {
		this.commandManager=commandManager;
	}
}