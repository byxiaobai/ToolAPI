package com.toolapi.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockUtil {
	public static boolean isMineral(Block block) {
		if(block==null)return false;
		Material material=block.getType();
		if(material.toString().contains("_ORE")) {
			return true;
		}
		return false;
	}
	public static boolean isChest(Block block) {
		if(block==null)return false;
		Material material=block.getType();
		if(material.toString().contains("CHEST")) {
			return true;
		}
		return false;
	}
	
	public static Block getBlockByLocationWithDeltaY(Location loc,int deltaY) {
		if(loc==null)return null;
		Location tmp=loc.clone();
		tmp.setY(tmp.getY()+deltaY);
		return tmp.getBlock();
	}
}
