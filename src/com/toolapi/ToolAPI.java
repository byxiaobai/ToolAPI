package com.toolapi;

import org.bukkit.plugin.java.JavaPlugin;

public class ToolAPI extends JavaPlugin{
	private static ToolAPI plugin;
	
	@Override
	public void onEnable() {
		plugin=this;
	}

	public static ToolAPI getPlugin() {
		return plugin;
	}

	public static void setPlugin(ToolAPI plugin) {
		ToolAPI.plugin = plugin;
	}

}
