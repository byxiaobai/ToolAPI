package com.toolapi.handler;
/** 
* @author byxiaobai
* 类说明 
*/
public abstract class ResultHandler extends Handler{
	public abstract Object getResult();
	public abstract boolean isDone();
}
