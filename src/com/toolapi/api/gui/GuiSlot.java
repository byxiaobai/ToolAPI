package com.toolapi.api.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/** 
* @author byxiaobai
* 类说明 
*/
public abstract class GuiSlot {
	private int inventorySlot=-1;
	private ItemStack slotItem=new ItemStack(Material.STONE);
	public GuiSlot(int inventorySlot) {
		this.inventorySlot=inventorySlot;
	}
	
	public int getInventorySlot() {
		return inventorySlot;
	}

	public ItemStack getSlotItem() {
		return slotItem;
	}

	public void setSlotItem(ItemStack slotItem) {
		this.slotItem = slotItem;
	}
	public abstract void updataItem();
}
