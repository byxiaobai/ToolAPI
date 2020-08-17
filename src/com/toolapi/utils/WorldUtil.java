package com.toolapi.utils;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldUtil {
	public static boolean isEnd(String worldName) {
		if(worldName==null)return false;
		return worldName.toLowerCase().contains("end");
	}
	public static boolean isWorld(String worldName) {
		if(worldName==null)return false;
		return worldName.toLowerCase().contains("world");
	}
	public static boolean isEnd(World world) {
		if(world==null)return false;
		return isEnd(world.getName());
	}
	public static void dropItem(Location location,ItemStack item) {
		World world=location.getWorld();
		location.setY(location.getY());
		BukkitRunnable bukkitrunnable = new BukkitRunnable()
	    {
	      public void run()
	      {
	      	try {
	      		world.dropItemNaturally(location, item.clone());
	      	}finally {
	      		this.cancel();
	      	}
	      	
	      }
	    };
	    bukkitrunnable.runTaskTimer(PluginUtil.getPlugin(), 1L, 1L);
	}
	public static void dropItem(Location location,ItemStack... items) {
		World world=location.getWorld();
		location.setY(location.getY());
		BukkitRunnable bukkitrunnable = new BukkitRunnable()
	    {
	      public void run()
	      {
	      	try {
	      		for(ItemStack item:items)
	      		world.dropItemNaturally(location, item.clone());
	      	}finally {
	      		this.cancel();
	      	}
	      	
	      }
	    };
	    bukkitrunnable.runTaskTimer(PluginUtil.getPlugin(), 1L, 1L);
	}
	public static void dropItem(Location location,List<ItemStack> items) {
		World world=location.getWorld();
		location.setY(location.getY());
		BukkitRunnable bukkitrunnable = new BukkitRunnable()
	    {
	      public void run()
	      {
	      	try {
	      		for(ItemStack item:items)
	      		world.dropItemNaturally(location, item.clone());
	      	}finally {
	      		this.cancel();
	      	}
	      	
	      }
	    };
	    bukkitrunnable.runTaskTimer(PluginUtil.getPlugin(), 1L, 1L);
	}
	public static void clearAllEntity(World world) {
		List<Entity> allEntity=world.getEntities();
		for(Entity entity:allEntity) {
			entity.remove();
		}
	}
}
