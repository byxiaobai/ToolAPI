package com.toolapi.api.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.toolapi.utils.InventoryUtil;

/** 
* @author byxiaobai
* 类说明 
*/
public class Gui {
	private Inventory inventory;
	private int[] canPutItemSlots;
	private int[] cannotPutItemSlots;
	private List<GuiHandler> guiHandlers;
	private HashMap<Integer,GuiSlot> guiSlotMap=new HashMap<>();
	public Gui(Inventory inventory,int[] canPutItemSlots,int[] cannotPutItemSlots){
		this.inventory=inventory;
		this.canPutItemSlots=canPutItemSlots;
		this.cannotPutItemSlots=cannotPutItemSlots;
		this.guiHandlers=new ArrayList<>();
	}
	public Gui(Inventory inventory,List<GuiHandler> guiHandlers,int[] canPutItemSlots){
		this.inventory=inventory;
		this.canPutItemSlots=canPutItemSlots;
		this.guiHandlers=guiHandlers;
	}
	/**
	 * GUI背包的范例
	 * @return 范例的引用
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
	public int[] getCanPutItemSlots() {
		return this.canPutItemSlots;
	}
	public List<GuiHandler> getGuiHandlers(){
		return guiHandlers;
	}
	public void setGuiHandlers(List<GuiHandler> guiHandlers){
		this.guiHandlers=guiHandlers;
	}
	/**
	 * 增加gui处理器
	 * @param guiHandlers
	 */
	public void addGuiHandlers(GuiHandler... guiHandlers) {
		for(GuiHandler guiHandler:guiHandlers) {
			this.guiHandlers.add(guiHandler);
		}
	}
	public int[] getCannotPutItemSlots() {
		return this.cannotPutItemSlots;
	}
	/**
	 * 是否是GUI的背包(该方法判断title)
	 * @return
	 */
	public boolean isGuiInventory(Inventory inv) {
		if(inv==null)return false;
		String title=inventory.getTitle();
		if(title==null)return false;
		if(title.equals(inv.getTitle()))
			return true;
		return false;
	}
	public void addGuiSlot(GuiSlot guiSlot) {
		this.guiSlotMap.put(guiSlot.getInventorySlot(), guiSlot);
	}
	public GuiSlot getGuiSlot(int inventorySlot) {
		return this.guiSlotMap.get(inventorySlot);
	}
	/**
	 * 更新slot的物品
	 */
	public void updataGuiSlots() {
		Collection<GuiSlot> guiSlots=guiSlotMap.values();
		try {
			for(GuiSlot guiSlot:guiSlots) {
				int slot=guiSlot.getInventorySlot();
				ItemStack item=guiSlot.getSlotItem();
				InventoryUtil.putInventoryItems(inventory, new ItemStack[] {item}, false, slot);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
