package com.toolapi.api.area.utils;

import org.bukkit.Location;
import org.bukkit.World;

import com.toolapi.api.area.Area;
import com.toolapi.utils.MathUtil;

/** 
* @author byxiaobai
* 类说明 
*/
public class AreaUtil {
	/**
	 * @param player
	 * @param area
	 */
	public static Location getRandomLocation(Area area) {
		Location maxLocation=area.getMaxLocation();
		Location minLocation=area.getMinLocation();
		double x1=minLocation.getX();
		double x2=maxLocation.getX();
		double randomX=MathUtil.getRandomNumber(MathUtil.getSmallestNumber(x1,x2), MathUtil.getBiggestNumber(x1,x2));
		
		double z1=minLocation.getZ();
		double z2=maxLocation.getZ();
		double randomZ=MathUtil.getRandomNumber(MathUtil.getSmallestNumber(z1,z2), MathUtil.getBiggestNumber(z1,z2));
		
		World world=area.getWorld();
		Location randomLocation=new Location(world,randomX,0,randomZ);
		randomLocation.setY(world.getHighestBlockYAt(randomLocation)+1);
		return randomLocation;
	}
	/**
	 * 得到左上角的坐标(y=0)
	 * @param area
	 * @return
	 */
	public static Location getTopLeftCornerLocation(Area area) {
		Location min=area.getMinLocation();
		Location max=area.getMaxLocation();
		double x1=min.getX();
		double x2=max.getX();
		double z1=min.getZ();
		double z2=max.getZ();
		double x=MathUtil.getBiggerNumber(x1, x2);
		double z=MathUtil.getBiggerNumber(z1, z2);
		Location loc=new Location(area.getWorld(),x,0,z);
		return loc;
		
	}
}
