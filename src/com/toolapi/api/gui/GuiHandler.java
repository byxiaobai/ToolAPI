package com.toolapi.api.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

/** 
* @author byxiaobai
* GUI处理器，可以用它方便的注册简单按钮，如: 确定、关闭GUI 按钮
*/
public abstract class GuiHandler {
	private GuiHandlerType type;
	private int[] handleSlots;
	private Gui gui;
	/**
	 * 实例化
	 * @param type 处理器类型
	 * @param handleSlots 需要处理的GUI slot
	 */
	public GuiHandler(Gui gui,GuiHandlerType type,int...handleSlots) {
		this.gui=gui;
		this.type=type;
		this.handleSlots=handleSlots;
	}
	public GuiHandlerType getGuiHandlerType(){
		return type;
	}
	public int[] getHandleSlots() {
		return handleSlots;
	}
	public Gui getGui() {
		return gui;
	}
	public boolean isHandleSlot(int slot) {
		for(int handleSlot:handleSlots)
		if(handleSlot==slot)
			return true;
		return false;
	}
	/**
	 * 处理任务
	 * @param inv
	 * @throws Exception 
	 */
	public abstract void handle(InventoryClickEvent evt) throws Exception;
	
	/**
	 * GUI处理器类型
	 * @author byxiaobai
	 *
	 */
	public enum GuiHandlerType{
		/**
		 * 单击
		 */
		SINGLE_CLICK
	}
}
