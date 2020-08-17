package com.toolapi.utils;

import java.util.ArrayList;
import java.util.List;

/** 
* @author byxiaobai
* 类说明 
*/
public class ListUtil {
	/**
	 * 用指定的引用数组得到参数中list的子集
	 * @param list
	 * @param indexs
	 * @return
	 */
	public static <T> List<T> getListWithIndexs(List<T> list,int[] indexs){
		List<T> newList=new ArrayList<T>();
		for(int index:indexs) {
			newList.add(list.get(index));
		}
		return newList;
	}
	public static <T> List<T> cloneList(List<T> list){
		List<T> newList=new ArrayList<T>();
		for(T t:list) {
			newList.add(t);
		}
		return newList;
	}
}
