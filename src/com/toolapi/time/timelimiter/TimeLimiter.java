package com.toolapi.time.timelimiter;

import com.toolapi.time.Time;

/** 
* @author byxiaobai
* 时间限制
*/
public class TimeLimiter {
	/**
	 * 是否限制
	 */
	private boolean usable=true;
	/**
	 * 终止时间
	 */
	private long terminationTime;
	/**
	 * @param usable
	 * @param terminationTime 用long表示的终止时间
	 */
	public TimeLimiter(boolean usable,long terminationTime) {
		this.usable=usable;
		this.terminationTime=terminationTime;
	}
	/**
	 * @param usable
	 * @param time 增加的时间
	 */
	public TimeLimiter(boolean usable,Time time) {
		this.usable=usable;
		this.terminationTime=getTerminationTime(time);
	}
	/**
	 * 通过现在的时间增加参数所表示的时间
	 * 得到终止时间
	 * @param time
	 * @return 终止时间
	 */
	public static long getTerminationTime(Time time) {
		return System.currentTimeMillis()+time.toLong();
	}
	/**
	 * @return 是否达到了终止时间
	 */
	public boolean isOk() {
		if(!usable)return true;
		return System.currentTimeMillis()>=terminationTime;
	}
}
