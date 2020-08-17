package com.toolapi.api.gui;

import org.bukkit.inventory.Inventory;

/** 
* @author byxiaobai
* 可更新的GUI
*/
public abstract class UpdataGui extends Gui{
	public UpdataGui(Inventory inventory, int[] canPutItemSlots, int[] cannotPutItemSlots) {
		super(inventory, canPutItemSlots, cannotPutItemSlots);
	}
	/**
	 * 更新GUI数据,需要外部调用
	 */
	public abstract void updata();
}