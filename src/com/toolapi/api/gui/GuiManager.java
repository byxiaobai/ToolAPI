package com.toolapi.api.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import com.toolapi.utils.MathUtil;


/** 
* @author byxiaobai
* 类说明 
*/
public class GuiManager {
	public static final GuiManager INSTANCE=new GuiManager();
	private GuiManager() {}
	private List<Gui> registeredGuis=new ArrayList<>();
	/**
	 * 注册GUI
	 * @param inv
	 * @param canPutItemSlots 可以放东西的位置
	 */
	@Deprecated
	public void registerGui(Inventory inv,int...canPutItemSlots) {
		registeredGuis.add(new Gui(inv,canPutItemSlots,MathUtil.deleteNumbers(MathUtil.getNumberToNumber(0, inv.getSize()-1), canPutItemSlots)));
	}
	/**
	 * 注册GUI
	 * @param inv
	 * @param canPutItemSlots 可以放东西的位置
	 * @param guihandlers Gui处理器
	 */
	@Deprecated
	public void registerGui(Inventory inv,List<GuiHandler> guiHandlers,int... canPutItemSlots) {
		registeredGuis.add(new Gui(inv,guiHandlers,canPutItemSlots));
	}
	/**
	 * 注册gui
	 * @param gui
	 */
	public void registerGui(Gui gui) {
		registeredGuis.add(gui);
	}
	
	/**
	 * 注册GUI管理器
	 * GUI管理器会注册gui监听器等
	 * @param plugin
	 */
	public void registerGuiManager(Plugin plugin) {
		Bukkit.getPluginManager().registerEvents(new GuiListener(), plugin);
	}
	/**
	 * 得到已注册的GUI
	 * @return 已注册的GUI
	 */
	public List<Gui> getRegisteredGuis(){
		return registeredGuis;
	}
	public Gui getGui(Inventory inv) {
		String title=inv.getTitle();
		for(Gui gui:registeredGuis) {
			String guiTitle=gui.getInventory().getTitle();
			if(guiTitle.equals(title)) {
				return gui;
			}
		}
		return null;
	}
}
