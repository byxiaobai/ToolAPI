package com.toolapi.api.request;

import org.bukkit.entity.Player;

import com.toolapi.time.Time;
import com.toolapi.time.timelimiter.TimeLimiter;

/** 
* @author byxiaobai
* 玩家之间的请求
*/
public abstract class PlayerRequest {
	private Player sender;
	private Player receiver;
	private TimeLimiter timeLimiter;
	private boolean isAlive=true;
	/**
	 * @param sender 发送者
	 * @param receiver 接收者
	 * @param time 时间限制
	 */
	public PlayerRequest(Player sender,Player receiver,Time time) {
		this.setSender(sender);
		this.setReceiver(receiver);
		this.timeLimiter=new TimeLimiter(true,time);
	}
	public Player getSender() {
		return sender;
	}
	public void setSender(Player sender) {
		this.sender = sender;
	}
	public Player getReceiver() {
		return receiver;
	}
	public void setReceiver(Player receiver) {
		this.receiver = receiver;
	}
	/**
	 * 请求是否有效
	 * @return
	 */
	public boolean isAlive() {
		if(isAlive)
		return !timeLimiter.isOk();
		return false;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive=isAlive;
	}
	/**
	 * 得到请求内容
	 * 如:"§axxx请求您赠送100金币,使用/accept 答应他的请求"
	 * @return
	 */
	public abstract String getRequestContent();
}