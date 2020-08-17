package com.toolapi.api.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.toolapi.api.command.listener.CommandPreprocessListener;


public abstract class CommandManager {
	private List<Command> commonCommands;
	private Command mainCommand;
	private CommandPreprocessListener commandListener;
	protected CommandManager(Plugin plugin) {
		commonCommands=getCommonCommands();
		mainCommand=getMainCommand();
		commandListener=new CommandPreprocessListener();
		commandListener.setCommandManager(this);
		Bukkit.getPluginManager().registerEvents(commandListener, plugin);
	}
	/**
	 * 给指定玩家发送所有指令的帮助
	 * @param player
	 */
	public void sendCommandHelps(Player player) {
		player.sendMessage("§c插件帮助:");
		player.sendMessage(mainCommand.getCommandHelp());
		for(Command command:commonCommands) {
			player.sendMessage(command.getCommandHelp());
		}
	}
	/**
	 * 执行指令
	 */
	public boolean doCommand(Player player,String[] args) {
		String commandString=getCommand(args);
		
		if(commandString.equalsIgnoreCase(mainCommand.getCommandName().trim())) {
			mainCommand.handleCommand(player, args);
			return true;
		}
		if(commonCommands!=null) {
			for(Command command:commonCommands) {
				if(commandString.startsWith(command.getCommandName())) {
					if(command.hasCommandPermission(player)) {
						if(command.handleCommand(player, args)) {
							command.sendOkMessage(player);
						}else {
							command.sendFailMessage(player);;
						}
					}else {
						player.sendMessage(command.getNoPermissionMessage());
					}
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 得到主指令下的所有指令的帮助
	 * @param mainCommand 如/cc
	 * @return
	 */
	public List<String> getCommandHelps(String mainCommand){
		List<String> commandHelps=new ArrayList<>();
		for(Command command:commonCommands) {
			if(isSameCommand(command,mainCommand)) {
				commandHelps.add(command.getCommandHelp());
			}
		}
		return commandHelps;
	}
	private String getCommand(String[] args) {
		StringBuilder command=new StringBuilder("/");
		for(String str:args) {
			command.append(str);
			command.append(" ");
		}
		return command.toString().trim();//忽略最后的空格
	}
	/**
	 * 得到所有普通指令(不包括主指令)
	 * @return
	 */
	protected abstract List<Command> getCommonCommands();
	/**
	 * 得到插件的主指令
	 * 如/a
	 * @return
	 */
	protected abstract Command getMainCommand();
	
	public boolean isSameCommand(Command command,String commandString) {
		String commandName=command.getCommandName();
		if(commandString.startsWith(commandName)&&(commandString.split(" ").length>=commandString.split(" ").length)) {
			return true;
		}
		return false;
	}
}
