package com.toolapi.time;
/**
 * 分钟
 */
public class Minute extends Time{
	public Minute(int time) {
		super(time);
	}

	@Override
	public long toLong() {
		return this.getTime()*1000*60;
	}
}