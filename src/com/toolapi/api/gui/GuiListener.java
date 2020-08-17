package com.toolapi.api.gui;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/** 
* @author byxiaobai
* 类说明 
*/
public class GuiListener implements Listener{
	private List<Gui> registeredGuis=GuiManager.INSTANCE.getRegisteredGuis();
	GuiListener(){}
	@EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryClicked(InventoryClickEvent evt) {
		//TestUtil.test("slot:"+evt.getSlot());
		Inventory inv=evt.getClickedInventory();
		if(inv==null)return;
		if(inv.getType()==InventoryType.PLAYER)return;
		for(Gui gui:registeredGuis) 
			if(isGui(gui,inv)) {
				int[] canPutItemSlots=gui.getCanPutItemSlots();
				int[] cannotPutItemSlots=gui.getCannotPutItemSlots();
				int clickSlot=evt.getSlot();
				Inventory guiInventory=gui.getInventory();
				for(int slot:cannotPutItemSlots) {//不能放物品的slot
					//TestUtil.test("slot:"+slot);
					ItemStack item=inv.getItem(slot);
					if(item==null)continue;
					if(!item.equals(guiInventory.getItem(slot))) {
						evt.setCancelled(true);
						return;
					}
					
				}
				for(int slot:canPutItemSlots) 
					if(clickSlot==slot)
						return;
				evt.setCancelled(true);
				gui.updataGuiSlots();
				return;
			}
	}

	@EventHandler
    public void onInventoryClick(InventoryClickEvent evt){
		int handleSlot=getHandleSlot(evt);
		Inventory clickInventory=evt.getClickedInventory();
		try {
			for(Gui gui:registeredGuis) {
				List<GuiHandler> handlers=gui.getGuiHandlers();
				if(handlers!=null) {
					for(GuiHandler handler:handlers) {
						if(handler.isHandleSlot(handleSlot)&&handler.getGui().isGuiInventory(clickInventory))
						handler.handle(evt);
					}	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static boolean isGui(Gui gui,Inventory inventory) {
		if(inventory.getTitle().equals(gui.getInventory().getTitle()))
			return true;
		return false;
	}
	private static int getHandleSlot(InventoryClickEvent evt) {
		return evt.getSlot();
	}
}
