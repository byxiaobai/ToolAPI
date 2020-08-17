package com.toolapi.time;
/** 
* @author byxiaobai
* 用于表示时间
*/
public abstract class Time {
	private int time;
	public Time(int time) {
		this.setTime(time);
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	/**
	 * 用long表示自己的时间
	 * 1000为一秒
	 * @return
	 */
	public abstract long toLong();
}