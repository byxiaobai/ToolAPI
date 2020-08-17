package com.toolapi.api.vault;

import org.bukkit.entity.Player;

import com.toolapi.utils.PluginUtil;
import com.toolapi.utils.VaultUtil;

import net.milkbowl.vault.economy.Economy;

/** 
* @author byxiaobai
* 类说明 
*/
public class VaultManager {
	public static final VaultManager INSTANCE=new VaultManager();
	private Economy economy;
	private VaultManager() {
		VaultUtil.initVault(PluginUtil.getPlugin());
		economy=VaultUtil.getEconomy();
	}
	public void givePlayerMoney(Player player,double money) {
		economy.depositPlayer(player, money);
	}
}
