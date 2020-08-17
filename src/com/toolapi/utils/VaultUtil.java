package com.toolapi.utils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultUtil
{
  private static Economy economy = null;
  
  public static boolean initVault(Plugin plugin){
    boolean hasNull = false;
    RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
    if ((economyProvider != null) && ((economy = (Economy)economyProvider.getProvider()) == null)) {
      hasNull = true;
    }
    return !hasNull;
  }
  
  public static Economy getEconomy()
  {
    return economy;
  }
}
