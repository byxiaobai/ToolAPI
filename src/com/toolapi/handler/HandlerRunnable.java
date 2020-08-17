package com.toolapi.handler;

import java.util.Stack;

import org.bukkit.scheduler.BukkitRunnable;

/** 
* @author byxiaobai
* 类说明 
*/
public class HandlerRunnable extends BukkitRunnable{
	private Stack<Handler> handlers=new Stack<>();
	@Override
	public void run() {
		if(!handlers.isEmpty()) {
			Handler handler=handlers.pop();
			try {
				handler.handle();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void addRunHandler(Handler handler) {
		this.handlers.add(handler);
	}
}
