package com.toolapi.utils;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/** 
* @author byxiaobai
* 类说明 
*/
public class PlayerUtil {
	/**
	 * 给玩家物品
	 * @param player
	 * @param items
	 */
	public static void givePlayerItem(Player player,ItemStack... items) {
		PlayerInventory inv=player.getInventory();
		World world=player.getWorld();
		Location location=player.getLocation();
		for(ItemStack item:items) {
			int firstEmpty=inv.firstEmpty();
			if(firstEmpty!=-1) {
				inv.setItem(firstEmpty, ItemUtil.cloneItem(item));
				continue;
			}
			if(item!=null&&location!=null)
			world.dropItem(location, ItemUtil.cloneItem(item));
		}
	}
	/**
	 * 给玩家物品
	 * @param player
	 * @param items
	 */
	public static void givePlayerItem(Player player,ItemStack item,int amount) {
		if(item==null)return;
		item.setAmount(amount);
		givePlayerItem(player,item);
	}
	/**
	 * 给玩家增加血量
	 * @param player
	 * @param addHealth
	 */
	@SuppressWarnings("deprecation")
	public static void addPlayerHealth(Player player,double addHealth) {
		if(player==null)return;
		double maxHealth=player.getMaxHealth();
		double nowHealth=player.getHealth();
		if(nowHealth+addHealth>maxHealth) {
			player.setHealth(maxHealth);
		}else {
			player.setHealth(nowHealth+addHealth);
		}
	}
	/**
	 * 获取装备物品
	 * @param player
	 * @return
	 */
	public static ItemStack[] getArmorItems(Player player){
		ItemStack[] items=new ItemStack[4];
		ItemStack[] armorContents=player.getInventory().getArmorContents();
		for(int i=0;i<armorContents.length;i++) {
			items[i]=armorContents[i];
		}
		return items;
	}
	/**
	 * 得到装备物品和手上物品
	 * @param player
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static ItemStack[] getArmorItemsAndItemInHand(Player player){
		ItemStack[] items=new ItemStack[5];
		ItemStack[] armorContents=player.getInventory().getArmorContents();
		for(int i=0;i<armorContents.length;i++) {
			items[i]=armorContents[i];
		}
		items[4]=player.getItemInHand();
		return items;
	}
	public static boolean hasItem(Player player,ItemStack item) {
		if(player==null)return false;
		PlayerInventory inv=player.getInventory();
		return InventoryUtil.haveItem(inv, item);
	}

	public static void takePlayerItem(Player player,ItemStack item) {
		if(item==null)return;
		PlayerInventory inv=player.getInventory();
		ItemStack[] contents=inv.getContents();
		int restAmount=item.getAmount();//还需要拿的物品量
		for(ItemStack content:contents) {
			if(content==null)continue;
			if(!ItemUtil.isSame(content, item))continue;
			if(content.getAmount()>=restAmount) {
				content.setAmount(content.getAmount()-restAmount);
				return;
			}else {
				restAmount-=content.getAmount();
				content.setAmount(0);
			}
		}
		player.updateInventory();
	}
	public static void sendMessage(World world,String message) {
		if(world==null)return;
		List<Player> players=world.getPlayers();
		if(players.isEmpty())return;
		String newMessage=message.replaceAll("&", "§");
		for(Player player:players) {
			player.sendMessage(newMessage);
		}
	}
}
