package com.toolapi.api.command;

import org.bukkit.entity.Player;

/** 
* @author byxiaobai
* 类说明 
*/
public abstract class Command {
	private final String mainCommandName;
	public Command(String mainCommandName) {
		this.mainCommandName=mainCommandName;
	}
	/**
	 * 执行指令
	 * @param player 玩家
	 * @param args 指令 比如/cc open,args就是: cc open
	 */
	public abstract boolean handleCommand(Player player,String[] args);
	
	public void sendFailMessage(Player player) {
		player.sendMessage(getFailMessage());
	}
	/**
	 * 得到指令名.比如/cc open就返回/cc open
	 * @return
	 */
	public abstract String getCommandName();
	/**
	 * 得到指令执行失败时发送的信息
	 * 默认为 "§f请检查您输入的指令！"
	 * @return
	 */
	public String getFailMessage() {
		return "§f请检查您输入的指令！";
	}
	public void sendOkMessage(Player player) {
		player.sendMessage("§a指令执行完毕!");
	}
	public boolean hasCommandPermission(Player player) {
		if(player.isOp())return true;
		String commandName=getCommandName();
		if(commandName.equalsIgnoreCase(mainCommandName))return true;//基础指令没权限
		
		commandName=commandName.replace("/", "");
		commandName=commandName.replaceAll(" ", ".");
		return player.hasPermission(commandName);
	}
	/**
	 * 得到指令使用者没有权限时提示的信息
	 * 默认为"§c您没有相应的权限!"
	 * @return
	 */
	public String getNoPermissionMessage() {
		return "§c您没有相应的权限!";
	}
	/**
	 * 得到指令介绍
	 * 例如:"§a/ba info  §b查看自己的属性"
	 * 默认实现为返回指令名
	 * @return
	 */
	public String getCommandHelp() {
		return getCommandName();
	}
}
