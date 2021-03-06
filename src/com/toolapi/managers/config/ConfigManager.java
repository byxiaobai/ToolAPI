package com.toolapi.managers.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import com.toolapi.utils.PluginUtil;
import com.toolapi.utils.StringUtil;

/** 
* @author byxiaobai
* 配置文件管理器
*/
public class ConfigManager {
	private ConfigManager() {}
	private HashMap<String,Config> configMap=new HashMap<>();
	public static final ConfigManager INSTANCE=new ConfigManager();
	private static final Plugin PLUGIN=PluginUtil.getPlugin(); 
	/**
	 * 得到一个名为configName的yaml，这个yaml需要本身就在插件的jar中
	 * @param configName 需要加.yml
	 * @return
	 */
	public YamlConfiguration getConfigYaml(String configName) {
		Config config=configMap.get(configName);
		if(config==null) {
			config=ConfigUtil.loadConfig(PLUGIN, configName);
			
			configMap.put(configName, config);
		}
		return config.getConfigYaml();
	}
	
	/**
	 * 得到一个名为configName的Config包装类，这个config需要本身就在插件的jar中
	 * @param configName 需要加.yml
	 * @return
	 */
	public Config getConfig(String configName) {
		Config config=configMap.get(configName);
		if(config==null) {
			config=ConfigUtil.loadConfig(PLUGIN, configName);
			
			configMap.put(configName, config);
		}
		return config;
	}
	
	/**
	 * 重载已储存的配置文件
	 */
	//TODO
	
	/**
	 * 通过父键获取父键下的所有子键，如:
	 * 父键:numbers
	 * 子键:numbers.1
	 * @param yaml 配置文件
	 * @param fatherKey 父键
	 * @return
	 */
	public Set<String> getKeysByKey(YamlConfiguration yaml,String fatherKey){
		Set<String> keys=yaml.getKeys(false);
		Set<String> newKeys=new HashSet<>();
		for(String key:keys) {
			if(key.startsWith(fatherKey)) {
				newKeys.add(key);
			}
		}
		return newKeys;
	}
	/**
	 * 加载坐标，如:"null,1,1,1"
	 * @param config
	 * @param key
	 * @return
	 */
	public Location getLocationFromConfig(YamlConfiguration config,String key) {
		if(config==null)return null;
		String locString=config.getString(key);
		String[] locStrings=locString.split(",");
		if(locStrings==null||locStrings.length<3)return null;
		String firstString=locStrings[0];
		boolean isNum=StringUtil.isNumber(firstString);
		Location location=null;
		if(isNum) {
			location=new Location(null,Double.parseDouble(locStrings[0]),Double.parseDouble(locStrings[1]),Double.parseDouble(locStrings[2]));
		}else {
			location=new Location(Bukkit.getWorld(locStrings[0]),Double.parseDouble(locStrings[1]),Double.parseDouble(locStrings[2]),Double.parseDouble(locStrings[3]));
		}
		return location;
	}
	
	public void saveConfig(Config config) {
		YamlConfiguration yaml=config.getConfigYaml();
		try {
			yaml.save(config.getConfigFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static class ConfigUtil {
		/**
		 * 加载配置文件
		 * @param plugin 插件主类
		 * @param configName 配置文件名 带.yml
		 * @return 配置文件
		 */
		@SuppressWarnings("unused")
		public static YamlConfiguration loadYaml(Plugin plugin,String configName) {
			File pluginDataFolder=plugin.getDataFolder();
			File configFile = new File(pluginDataFolder, configName);
		    if (!pluginDataFolder.exists())pluginDataFolder.mkdir();
		    if (!configFile.exists())plugin.saveResource(configName, true);
		    
		    return YamlConfiguration.loadConfiguration(configFile);
		}
		
		/**
		 * 加载配置文件
		 * @param plugin 插件主类
		 * @param configName 配置文件名 带.yml
		 * @return 配置文件
		 */
		public static Config loadConfig(Plugin plugin,String configName) {
			File pluginDataFolder=plugin.getDataFolder();
			File configFile = new File(pluginDataFolder, configName);
		    if (!pluginDataFolder.exists())pluginDataFolder.mkdir();
		    if (!configFile.exists())plugin.saveResource(configName, true);
		    Config config=new Config(configFile,YamlConfiguration.loadConfiguration(configFile));
		    
		    return config;
		}
		
	}
}
