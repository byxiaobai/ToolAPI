package com.toolapi.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;


/** 
* @author byxiaobai
* 背包工具类
*/
public class InventoryUtil {
	private static final ItemStack AIR=new ItemStack(Material.AIR);
	public static Inventory createInventory(int size,String title) {
		Inventory inv=Bukkit.createInventory(null, size,title);
		return inv;
	}
	  public static void setInventoryItem(Inventory gui,ItemStack item,int... slot) {
		  if(item==null)item=AIR;
			for(int i:slot) {
				gui.setItem(i, item.clone());
			}
	  }
	  private static final ItemStack EMPTY_ITEM=new ItemStack(Material.AIR);
	  /**
	   * 将items放入背包的slots中
	   * @param inv
	   * @param items
	   * @param fillWithEmpty 如果slots比items大,是否将slot的其余内容设置为空气
	   * @param slots
	   * @throws Exception 
	   */
	public static void putInventoryItems(InventoryView invView,ItemStack[] items,boolean fillWithEmpty,int... slots) throws Exception {
		  if(items==null)return;
		  if(slots==null)return;
		  if(items.length>slots.length)throw new Exception("设置物品时出现错误！");
		  Inventory inv=invView.getTopInventory();
		  BukkitRunnable bukkitrunnable = new BukkitRunnable() {
              public void run() {
                  try {
                	  if(items.length<slots.length&&fillWithEmpty) {
            			  for(int i:slots) {
            				  inv.setItem(i, EMPTY_ITEM.clone());
            			  }
            		  }
            		  for(int i=0;i<items.length;i++) {
            			  inv.setItem(slots[i], items[i]);
            		  }
                  }
                  finally {
                      this.cancel();
                  }
              }
          };
          bukkitrunnable.runTaskTimerAsynchronously(PluginUtil.getPlugin(), 1L, 1L);
	  }

	/**
	   * 将items放入背包的slots中
	   * @param inv
	   * @param items
	   * @param fillWithEmpty 如果slots比items大,是否将slot的其余内容设置为空气
	   * @param slots
	   * @throws Exception 
	   */
	public static void putInventoryItems(Inventory inv,ItemStack[] items,boolean fillWithEmpty,int... slots) throws Exception {
		  if(items==null)return;
		  if(slots==null)return;
		  if(items.length>slots.length)throw new Exception("设置物品时出现错误！");
		  BukkitRunnable bukkitrunnable = new BukkitRunnable() {
            public void run() {
                try {
              	  if(items.length<slots.length&&fillWithEmpty) {
          			  for(int i:slots) {
          				  inv.setItem(i, EMPTY_ITEM.clone());
          			  }
          		  }
          		  for(int i=0;i<items.length;i++) {
          			  inv.setItem(slots[i], items[i]);
          		  }
                }
                finally {
                    this.cancel();
                }
            }
        };
        bukkitrunnable.runTaskTimerAsynchronously(PluginUtil.getPlugin(), 1L, 1L);
	  }
	
	public static void remoteItem(Inventory inv,int slot) {
		ItemStack item=inv.getItem(slot);
		item.setAmount(item.getAmount()-1);
		inv.setItem(slot, item);
	}
	public static int getItemAmount(Inventory inv,ItemStack item) {
		int amount=0;
		ItemStack[] contents=inv.getContents();
		for(ItemStack content:contents) {
			if(content!=null&&ItemUtil.isSame(content, item)) {
				amount+=item.getAmount();
			}
		}
		return amount;
	}
	/**
	 * 判断背包中的格子是否没物品
	 * @param inv
	 * @param slots
	 * @return
	 */
	public static boolean isNoItem(Inventory inv,int...slots) {
		for(int slot:slots) {
			ItemStack item=inv.getItem(slot);
			if(item==null) {
				continue;
			}
			if(item.getType()!=Material.AIR) {
				return false;
			}
		}
		return true;
	}
	public static boolean haveItem(Inventory inv,ItemStack item) {
		ItemStack[] contents=inv.getContents();
		for(ItemStack content:contents) {
			if(ItemUtil.isSame(content, item)) {
				return true;
			}
		}
		return false;
	}
	public static ItemStack getItem(Inventory inv,ItemStack item) {
		ItemStack[] contents=inv.getContents();
		for(ItemStack content:contents) {
			if(ItemUtil.isSame(content, item)) {
				return content;
			}
		}
		return null;
	}
	public static Inventory cloneInventory(Inventory oldInventory){
	    Inventory newInventory = Bukkit.createInventory(oldInventory.getHolder(), oldInventory.getSize(), oldInventory.getTitle());
	    for (int i = 0; i < oldInventory.getSize(); i++){
	      ItemStack Item = oldInventory.getItem(i);
	      newInventory.setItem(i, Item);
	    }
	    return newInventory;
	}
}
