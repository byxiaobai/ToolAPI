package com.toolapi.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/** 
* @author byxiaobai
* 类说明 
*/
public class ItemUtil {
	public static ItemStack cloneItem(ItemStack item) {
		if(item==null)return null;
		return item.clone();
	}
	public static void setItemStackDisplayName(ItemStack item,String newName) {
		if(item==null)return;
		ItemMeta itemMeta=item.getItemMeta();
		if(itemMeta==null)return;
		itemMeta.setDisplayName(newName);
		item.setItemMeta(itemMeta);
		return;
	}
	public static void setItemStackProtectDisplayName(String protectSuffix,ItemStack... items) {
		for(ItemStack item:items) {
			if(item==null)continue;
			ItemMeta itemMeta=item.getItemMeta();
			if(itemMeta==null)continue;
			String displayName=itemMeta.getDisplayName();
			if(displayName==null)displayName="";
			itemMeta.setDisplayName(displayName+protectSuffix);
			item.setItemMeta(itemMeta);
		}
	}
	
	public static void setItemAmount(ItemStack item,int amount) {
		if(item==null)return;
		item.setAmount(amount);
	}
	/**
	 * 改变物品的lore中的某个字符串
	 * @param item
	 * @param oldString
	 * @param newString
	 */
	public static void changeItemLoreString(ItemStack item,String oldString,String newString) {
		if(item==null)return;
		ItemMeta itemMeta=item.getItemMeta();
		if(itemMeta==null)return;
		List<String> lore=itemMeta.getLore();
		if(lore==null)return;
		lore=LoreUtil.changeString(lore, oldString, newString);
		itemMeta.setLore(lore);
		item.setItemMeta(itemMeta);
		return;
	}
	/**
	 * 得到物品lore
	 * @param item
	 * @return
	 */
	public static List<String> getItemLore(ItemStack item){
		if(item==null)return null;
		ItemMeta itemMeta=item.getItemMeta();
		if(itemMeta==null)return null;
		List<String> lore=itemMeta.getLore();
		return lore;
	}
	/**
	 * 检测物品名是否为给定的值
	 * @param item
	 * @param displayName
	 * @return
	 */
	public static boolean checkItemName(ItemStack item,String displayName) {
		if(item==null)return false;
		ItemMeta itemMeta=item.getItemMeta();
		if(itemMeta==null) {
			if(displayName==null)return true;
			return false;
		}
		String itemName=itemMeta.getDisplayName();
		if(itemName==null) {
			if(displayName==null)return true;
			return false;
		}
		if(itemName.equals(displayName)) {
			return true;
		}
		if(displayName!=null) {
			if(itemName.equals(displayName.replaceAll("&", "§"))) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 检查物品数量是否大于等于给定的值
	 * @param item
	 * @param amount
	 * @return
	 */
	public static boolean checkItemAmount(ItemStack item,int amount) {
		if(item==null) {
			if(amount==0)return true;
			return false;
		}
		return (item.getAmount()>=amount);
	}
	public static void setItemLore(ItemStack item,String...lore) {
		if(item==null)return;
		ItemMeta itemMeta=item.getItemMeta();
		if(itemMeta==null)return;
		ArrayList<String> list=new ArrayList<>();
		for(String str:lore) {
			list.add(str.replaceAll("&", "§"));
		}
		itemMeta.setLore(list);
		item.setItemMeta(itemMeta);
	}
	public static void setItemLore(ItemStack item,List<String> lore) {
		if(item==null)return;
		ItemMeta itemMeta=item.getItemMeta();
		if(itemMeta==null)return;
		ArrayList<String> list=new ArrayList<>();
		for(String str:lore) {
			list.add(str);
		}
		itemMeta.setLore(list);
		item.setItemMeta(itemMeta);
	}
	/**
	 * 查看物品的介绍中是否有参数提供的字符串
	 * @param item
	 * @param str
	 * @return
	 */
	public static boolean checkItemLoreHasString(ItemStack item,String str) {
		if(str==null)return false;
		if(item==null)return false;
		ItemMeta itemMeta=item.getItemMeta();
		if(itemMeta==null)return false;
		List<String> lore=itemMeta.getLore();
		if(lore==null)return false;
		return LoreUtil.checkString(lore, str);
	}
	/**
	 * 得到物品的显示名
	 * @param item
	 * @return
	 */
	public static String getItemStackDisplayName(ItemStack item) {
		if(item==null)return "";
		ItemMeta itemMeta=item.getItemMeta();
		if(itemMeta==null)return "";
		return itemMeta.getDisplayName();
	}
	/**
	 * 比较名字和lore
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isSame(ItemStack a,ItemStack b) {
		if(a==null||b==null)return a==b;
		String name1=ItemUtil.getItemStackDisplayName(a);
		String name2=ItemUtil.getItemStackDisplayName(b);
		if((name1==null&&name2==null)||(name1!=null&&name1.equals(name2))) {
				return true;
		}
		return false;
	}
	public static class LoreUtil{
		/**
		 * 删除lore中的某个字符串
		 * @param lore
		 * @param str
		 * @return
		 */
		public static List<String> remoteString(List<String> lore,String str){
			lore.remove(str);
			return lore;
		}
		public static List<String> changeString(List<String> lore,String oldString,String newString){
			int size=lore.size();
			int number=-1;
			for(int i=0;i<size;i++) {
				if(lore.get(i).equalsIgnoreCase(oldString.replaceAll("&", "§"))) {
					number=i;
					break;
				}
			}
			if(number==-1) {
				return lore;
			}
			lore.set(number, newString);
			return lore;
		}
		/**
		 * 查看介绍中是否有该字符串
		 * @param lore
		 * @param str
		 * @return
		 */
		public static boolean checkString(List<String> lore,String str){
			if(lore==null)return false;
			return lore.contains(str);
		}
	}
	public static ItemStack createItem(Material material,String displayName,String...lore) {
		ItemStack item=new ItemStack(material);//回城符
		ItemUtil.setItemStackDisplayName(item, displayName);
		ItemUtil.setItemLore(item,lore);
		return item;
	}
}
