package com.toolapi.time;
/**
 * 秒
 */
public class Second extends Time{
	public Second(int time) {
		super(time);
	}

	@Override
	public long toLong() {
		return this.getTime()*1000;
	}
}