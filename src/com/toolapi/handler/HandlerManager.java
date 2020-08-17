package com.toolapi.handler;

import com.toolapi.utils.PluginUtil;

public class HandlerManager {
	public static final HandlerManager INSTANCE=new HandlerManager();
	private HandlerRunnable handlerRunnable=new HandlerRunnable();
	public void init() {
		handlerRunnable.runTaskTimer(PluginUtil.getPlugin(), 1, 1);
	}
	public void addHanlder(Handler handler) {
		this.handlerRunnable.addRunHandler(handler);
	}
}
